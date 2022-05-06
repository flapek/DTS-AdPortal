package pl.edu.wat.portal_gloszeniowy.controllers;

import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wat.portal_gloszeniowy.dtos.FilterOptionsRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.OfferResponseDto;
import pl.edu.wat.portal_gloszeniowy.dtos.OffersWithPagesCountResponseDto;
import pl.edu.wat.portal_gloszeniowy.services.OfferService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;

@Controller
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class OfferController {

    private final OfferService offerService;

    @PostMapping(value = "/filtered_offers")
    public ResponseEntity<OffersWithPagesCountResponseDto> getFilteredOffers(@RequestBody FilterOptionsRequestDto filterOptionsRequestDto) {
        return new ResponseEntity<OffersWithPagesCountResponseDto>(offerService.getFilteredOffers(filterOptionsRequestDto), HttpStatus.OK);
    }

    @PostMapping(value = "/filtered_my_offers")
    public ResponseEntity<OffersWithPagesCountResponseDto> getUserFilteredOffers(@RequestBody FilterOptionsRequestDto filterOptionsRequestDto, Principal principal) {
        return new ResponseEntity<OffersWithPagesCountResponseDto>(offerService.getUserFilteredOffers(filterOptionsRequestDto, principal.getName()), HttpStatus.OK);
    }

    @GetMapping(path = "/offer/{id}")
    public ResponseEntity<OfferResponseDto> getOffer(@PathVariable("id") Long id) {
        return new ResponseEntity<>(offerService.getOfferDto(id), HttpStatus.OK);
    }

    @PostMapping(path = "/addOffer")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity addOffer(@RequestParam(name = "file", required = false) MultipartFile multipartFile,
                                   @RequestParam("title") String title,
                                   @RequestParam("price") float price,
                                   @RequestParam("details") String details,
                                   @RequestParam("tags") String[] tags,
                                   Principal principal) throws IOException {
        if (multipartFile == null) {
            File file = new File("C:\\Users\\adamm\\IdeaProjects\\portal_gloszeniowy\\src\\main\\" +
                    "resources\\static\\Default_photo.png");
            FileInputStream input = new FileInputStream(file);
            MultipartFile mFile = new MockMultipartFile("file",
                    file.getName(), "text/plain", IOUtils.toByteArray(input));
            offerService.addOffer(mFile, title, price, details, Arrays.asList(tags.clone()), principal.getName());
        } else
            offerService.addOffer(multipartFile, title, price, details, Arrays.asList(tags.clone()), principal.getName());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(path = "/updateOfferWithPhoto")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity updateOffer(@RequestParam("file") MultipartFile multipartFile,
                                      @RequestParam("title") String title,
                                      @RequestParam("price") float price,
                                      @RequestParam("details") String details,
                                      @RequestParam("tags") String[] tags,
                                      @RequestParam("id") Long id,
                                      @RequestParam("newPhoto") boolean newPhoto) {
        offerService.updateOffer(multipartFile, title, price, details, Arrays.asList(tags.clone()), id, newPhoto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(path = "/updateOffer")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity updateOfferWithPhoto(@RequestParam("title") String title,
                                               @RequestParam("price") float price,
                                               @RequestParam("details") String details,
                                               @RequestParam("tags") String[] tags,
                                               @RequestParam("id") Long id,
                                               @RequestParam("newPhoto") boolean newPhoto) {
        offerService.updateOffer(title, price, details, Arrays.asList(tags.clone()), id, newPhoto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete-offer/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity deleteOffer(@PathVariable("id") Long id, Principal principal) {
        offerService.deleteOffer(id, principal.getName());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(path = "/addFile")
    public ResponseEntity addImage(@RequestParam("file") MultipartFile file) {
        offerService.addImage(file);
        return new ResponseEntity(HttpStatus.OK);
    }

}
