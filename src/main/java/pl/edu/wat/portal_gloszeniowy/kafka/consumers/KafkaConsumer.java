package pl.edu.wat.portal_gloszeniowy.kafka.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.edu.wat.portal_gloszeniowy.dtos.OfferResponseDto;
import pl.edu.wat.portal_gloszeniowy.models.User;
import pl.edu.wat.portal_gloszeniowy.repositories.UserRepository;
import pl.edu.wat.portal_gloszeniowy.services.EmailService;
import pl.edu.wat.portal_gloszeniowy.services.OfferService;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

@Component
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
    private final CountDownLatch latch = new CountDownLatch(1);
    private final ObjectMapper objectMapper;
    private final OfferService offerService;
    private final EmailService emailService;
    private UserRepository userRepository;

    @Autowired
    public KafkaConsumer(ObjectMapper objectMapper, OfferService offerService, EmailService emailService,
                         UserRepository userRepository) {
        this.objectMapper = objectMapper;
        this.offerService = offerService;
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    @KafkaListener(topics = "shippingStatus")
    public void receive(ConsumerRecord<?, ?> consumerRecord) throws IOException {
        LOGGER.info("received payload='{}'", consumerRecord.toString());
        latch.countDown();
        String s = consumerRecord.value().toString();
        OfferResponseDto offerShipping = objectMapper.readValue(s, OfferResponseDto.class);
        offerService.updateShippingStatus(offerShipping.getId(), offerShipping.getStatus());
        Optional<User> user = userRepository.findByUsername(offerShipping.getUserLogin());
        user.ifPresent(value -> emailService.sendEmail(value.getEmail(), offerShipping.getTitle(),
                offerShipping.getStatus()));
    }
}
