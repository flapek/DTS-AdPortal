package pl.edu.wat.portal_gloszeniowy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.edu.wat.portal_gloszeniowy.dtos.CarOfferRequestDto;
import pl.edu.wat.portal_gloszeniowy.services.CarOfferService;

@Controller
public class CarOfferController {

    private final CarOfferService carOfferService;

    public CarOfferController(CarOfferService carOfferService) {
        this.carOfferService = carOfferService;
    }

    @PostMapping(path = "/add-car-offer")
    public ResponseEntity addCarOffer(@RequestBody CarOfferRequestDto carOfferRequestDto)
    {
        carOfferService.addCarOffer(carOfferRequestDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
