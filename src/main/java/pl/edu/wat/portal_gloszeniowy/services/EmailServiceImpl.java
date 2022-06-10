package pl.edu.wat.portal_gloszeniowy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(String email, String offerTitle, int s) {
        String status = "";
        switch (s){
            case 0:
                status = "\"Kompletowanie zamówienia\"";
                break;
            case 1:
                status = "\"Przygotowane do wysyłki\"";
                break;
            case 2:
                status = "\"Wysłano\"";
                break;
        }

        String title = "";
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText("Status oferty " + offerTitle + " został zmieniony na: " + status);
            helper.setTo(email);
            helper.setSubject(title);
            helper.setFrom("aplikacjatreningowa@gmail.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new IllegalStateException("failed to send email");
        }
    }
}
