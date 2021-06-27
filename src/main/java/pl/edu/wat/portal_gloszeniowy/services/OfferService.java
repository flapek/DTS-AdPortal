package pl.edu.wat.portal_gloszeniowy.services;

import org.springframework.web.multipart.MultipartFile;
import pl.edu.wat.portal_gloszeniowy.dtos.FilterOptionsRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.OfferRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.OfferResponseDto;
import pl.edu.wat.portal_gloszeniowy.dtos.TagRequestDto;
import pl.edu.wat.portal_gloszeniowy.entities.Offer;

import java.util.List;

public interface OfferService {

    List<OfferResponseDto> getAllOffers();
    List<OfferResponseDto> getUserOffers(String userName);
    List<OfferResponseDto> getFilteredOffers(FilterOptionsRequestDto filterOptionsRequestDto, String userName);
    List<OfferResponseDto> getTagOffers(TagRequestDto tagRequestDto);
    OfferResponseDto getOfferDto(Long offerId);
    Offer getOffer(Long offerId);

    void addOffer(MultipartFile file, String title, float price, String details, List<String> tags, String userName);

    void updateOffer(MultipartFile file, String title, float price, String details, List<String> tags, Long offerId);

    void deleteOffer(Long offerId, String userName);

    void addImage(MultipartFile multipartFile);

}
