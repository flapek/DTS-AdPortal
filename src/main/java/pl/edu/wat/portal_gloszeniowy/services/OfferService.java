package pl.edu.wat.portal_gloszeniowy.services;

import org.springframework.web.multipart.MultipartFile;
import pl.edu.wat.portal_gloszeniowy.dtos.OfferRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.OfferResponseDto;
import pl.edu.wat.portal_gloszeniowy.dtos.TagRequestDto;
import pl.edu.wat.portal_gloszeniowy.entities.Offer;

import java.util.List;

public interface OfferService {

    List<OfferResponseDto> getAllOffers();
    List<OfferResponseDto> getTagOffers(TagRequestDto tagRequestDto);
    OfferResponseDto getOfferDto(Long offerId);
    Offer getOffer(Long offerId);

    void addOffer(MultipartFile file, String title, float price, String details, List<String> tags);

    void updateOffer(OfferRequestDto offerRequestDto, Long offerId);

    void deleteOffer(Long offerId);

    void addImage(MultipartFile multipartFile);

}
