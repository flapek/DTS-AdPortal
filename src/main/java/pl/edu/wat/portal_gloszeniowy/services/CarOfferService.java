package pl.edu.wat.portal_gloszeniowy.services;

import pl.edu.wat.portal_gloszeniowy.dtos.CarOfferRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.CarOfferResponseDto;

import java.util.List;

public interface CarOfferService {

    CarOfferResponseDto getCarOffer(Long carOfferId);
    List<CarOfferResponseDto> getAllCarOffers();

    void addCarOffer(CarOfferRequestDto carOfferRequestDto);

    void updateCarOffer(CarOfferRequestDto carOfferRequestDto, Long carId);
}
