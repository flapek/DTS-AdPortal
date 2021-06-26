package pl.edu.wat.portal_gloszeniowy.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wat.portal_gloszeniowy.dtos.OfferRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.OfferResponseDto;
import pl.edu.wat.portal_gloszeniowy.services.OfferService;

import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
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
        return new ResponseEntity<OfferResponseDto>(offerService.getOfferDto(id), HttpStatus.OK);
    }

    @PostMapping(path = "/addOffer")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity addOffer(@RequestParam("file") MultipartFile multipartFile,
                                   @RequestParam("title") String title,
                                   @RequestParam("price") float price,
                                   @RequestParam("details") String details,
                                   @RequestParam("tags") String[] tags)
    {
        offerService.addOffer(multipartFile, title, price, details, Arrays.asList(tags.clone()));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(path = "/updateOffer/{id}")
    public ResponseEntity updateOffer(@RequestBody OfferRequestDto offerRequestDto,
                                      @PathVariable("id") Long id)
    {
        offerService.updateOffer(offerRequestDto, id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete-offer/{id}")
    public ResponseEntity deleteOffer(@PathVariable("id") Long id)
    {
        offerService.deleteOffer(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(path = "/addFile")
    public ResponseEntity addImage(@RequestParam("file") MultipartFile file)
    {
        offerService.addImage(file);
        return new ResponseEntity(HttpStatus.OK);
    }

}
