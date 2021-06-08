package pl.edu.wat.portal_gloszeniowy.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.portal_gloszeniowy.dtos.OfferRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.OfferResponseDto;
import pl.edu.wat.portal_gloszeniowy.services.OfferService;

import java.util.List;

@Controller
@AllArgsConstructor
public class OfferController {

    private final OfferService offerService;


    @GetMapping(path = "/offers")
    public ResponseEntity<List<OfferResponseDto>> getAllOffers()
    {
        return new ResponseEntity<List<OfferResponseDto>>(offerService.getAllOffers(), HttpStatus.OK);
    }

    @GetMapping(path = "/offer/{id}")
    public ResponseEntity<OfferResponseDto> getOffer(@PathVariable("id") Long id)
    {
        return new ResponseEntity<OfferResponseDto>(HttpStatus.OK);
    }

    @PostMapping(path = "/addOffer")
    public ResponseEntity addOffer(@RequestParam OfferRequestDto offerRequestDto)
    {
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete-offer/{id}")
    public ResponseEntity deleteOffer(@PathVariable("id") Long id)
    {
        return new ResponseEntity(HttpStatus.OK);
    }
}
