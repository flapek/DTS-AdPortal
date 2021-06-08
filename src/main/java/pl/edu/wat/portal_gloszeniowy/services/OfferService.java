package pl.edu.wat.portal_gloszeniowy.services;

import pl.edu.wat.portal_gloszeniowy.dtos.OfferRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.OfferResponseDto;
import pl.edu.wat.portal_gloszeniowy.dtos.TagRequestDto;

import java.util.List;

public interface OfferService {

    List<OfferResponseDto> getAllOffers();
    List<OfferResponseDto> getTagOffers(TagRequestDto tagRequestDto);
    OfferResponseDto getOffer(Long offerId);

    void addOffer(OfferRequestDto offerRequestDto);

    void deleteOffer(Long offerId);

}
