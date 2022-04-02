package pl.edu.wat.portal_gloszeniowy.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wat.portal_gloszeniowy.dtos.FilterOptionsRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.OfferRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.OfferResponseDto;
import pl.edu.wat.portal_gloszeniowy.dtos.OffersWithPagesCountResponseDto;
import pl.edu.wat.portal_gloszeniowy.entities.Offer;
import pl.edu.wat.portal_gloszeniowy.services.OfferService;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class OfferController {

    private final OfferService offerService;


    @GetMapping(path = "/offers/{pageNumber}")
    public ResponseEntity<OffersWithPagesCountResponseDto> getAllOffers(@PathVariable Integer pageNumber)
    {
        return new ResponseEntity<OffersWithPagesCountResponseDto>(offerService.getAllOffers(pageNumber), HttpStatus.OK);
    }

    @GetMapping(path = "/myOffers")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<OffersWithPagesCountResponseDto> getUserOffers(Principal principal)
    {
        return new ResponseEntity<OffersWithPagesCountResponseDto>(offerService.getUserOffers(principal.getName()), HttpStatus.OK);
    }

    @GetMapping(path = "/filters/")
    public ResponseEntity<List<OfferResponseDto>> getAllOfferss()
    {
        return new ResponseEntity<List<OfferResponseDto>>(offerService.getAllOffers(), HttpStatus.OK);
    }

    @GetMapping(path = "/myFilters/")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<OffersWithPagesCountResponseDto> getUserOfferss(Principal principal)
    {
        return new ResponseEntity<OffersWithPagesCountResponseDto>(offerService.getUserOffers(principal.getName()), HttpStatus.OK);
    }

    @RequestMapping(value="/myFilters/{tags}", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<OfferResponseDto>> getMyFilteredOffers(@PathVariable String[] tags, Principal principal)
    {
        return new ResponseEntity<>(offerService.getFilteredOffers(new FilterOptionsRequestDto(tags, null), principal.getName()), HttpStatus.OK);
    }

    @RequestMapping(value="/filters/{tags}", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<OfferResponseDto>> getFilteredOffers(@PathVariable String[] tags)
    {
        return new ResponseEntity<>(offerService.getFilteredOffers(new FilterOptionsRequestDto(tags, null), ""), HttpStatus.OK);
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
                                   @RequestParam("tags") String[] tags,
                                   Principal principal)
    {
        offerService.addOffer(multipartFile, title, price, details, Arrays.asList(tags.clone()), principal.getName());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(path = "/updateOffer")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity updateOffer(@RequestParam("file") MultipartFile multipartFile,
                                      @RequestParam("title") String title,
                                      @RequestParam("price") float price,
                                      @RequestParam("details") String details,
                                      @RequestParam("tags") String[] tags,
                                      @RequestParam("id") Long id)
    {
        offerService.updateOffer(multipartFile, title, price, details, Arrays.asList(tags.clone()), id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete-offer/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity deleteOffer(@PathVariable("id") Long id, Principal principal)
    {
        offerService.deleteOffer(id, principal.getName());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(path = "/addFile")
    public ResponseEntity addImage(@RequestParam("file") MultipartFile file)
    {
        offerService.addImage(file);
        return new ResponseEntity(HttpStatus.OK);
    }

}
