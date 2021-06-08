package pl.edu.wat.portal_gloszeniowy.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.portal_gloszeniowy.dtos.OfferRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.OfferResponseDto;
import pl.edu.wat.portal_gloszeniowy.dtos.TagRequestDto;
import pl.edu.wat.portal_gloszeniowy.repositories.OfferRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class OfferServiceImpl implements OfferService{

    private final OfferRepository offerRepository;

    @Override
    public List<OfferResponseDto> getAllOffers() {
        return StreamSupport.stream(offerRepository.findAll().spliterator(), false)
                .map(offer -> new OfferResponseDto(offer.getId(), offer.getTitle(), offer.getPrice(),
                        null))
                .collect(Collectors.toList());
    }

    @Override
    public List<OfferResponseDto> getTagOffers(TagRequestDto tagRequestDto) {
        return null;
    }

    @Override
    public OfferResponseDto getOffer(Long offerId) {
        return null;
    }

    @Override
    public void addOffer(OfferRequestDto offerRequestDto) {

    }

    @Override
    public void deleteOffer(Long offerId) {

    }
}
