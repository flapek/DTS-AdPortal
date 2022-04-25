package pl.edu.wat.portal_gloszeniowy.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wat.portal_gloszeniowy.dtos.*;
import pl.edu.wat.portal_gloszeniowy.dtos.enums.SortType;
import pl.edu.wat.portal_gloszeniowy.entities.Offer;
import pl.edu.wat.portal_gloszeniowy.entities.Tag;
import pl.edu.wat.portal_gloszeniowy.models.User;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;

public interface OfferService {

//    OffersWithPagesCountResponseDto getAllOffers(Integer pageNumber);
//    OffersWithPagesCountResponseDto getUserOffers(String userName, Integer pageNumber);
    OffersWithPagesCountResponseDto getFilteredOffers(FilterOptionsRequestDto filterOptionsRequestDto);
    OffersWithPagesCountResponseDto getUserFilteredOffers(FilterOptionsRequestDto filterOptionsRequestDto, String userName);
    List<OfferResponseDto> getAllOffersSorted(Integer pageNumber, SortType sortType);
    List<OfferResponseDto> getFilteredOffersSorted(Collection<List<Tag>> tags, Integer pageNumber, SortType sortType);
    List<OfferResponseDto> getFilteredOffersSorted(String[] tags, Integer pageNumber, SortType sortType);
    List<OfferResponseDto> getUserOffersSorted(Integer pageNumber, SortType sortType, User user);
    List<OfferResponseDto> getUserFilteredOffersSorted(String[] tags, Integer pageNumber, SortType sortType, User user);
    List<OfferResponseDto> getUserFilteredOffersSorted(Collection<List<Tag>> tags, Integer pageNumber, SortType sortType, User user);
    OfferResponseDto getOfferDto(Long offerId);
    Offer getOffer(Long offerId);

    void addOffer(MultipartFile file, String title, float price, String details, List<String> tags, String userName);

    void updateOffer(MultipartFile file, String title, float price, String details, List<String> tags, Long offerId);

    void deleteOffer(Long offerId, String userName);

    void addImage(MultipartFile multipartFile);

    PageRequest createPageable(int pageNumber, SortType sortType);

}
