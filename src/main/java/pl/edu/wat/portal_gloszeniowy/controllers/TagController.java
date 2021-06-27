package pl.edu.wat.portal_gloszeniowy.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import pl.edu.wat.portal_gloszeniowy.dtos.OfferResponseDto;
import pl.edu.wat.portal_gloszeniowy.dtos.TagResponseDto;
import pl.edu.wat.portal_gloszeniowy.services.TagService;

import java.util.List;

@Controller
@CrossOrigin(origins = "https://portal-ogloszeniowy-f.herokuapp.com/")
@AllArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping(path = "/tags")
    public ResponseEntity<List<String>> getAllTags()
    {
        return new ResponseEntity<List<String>>(tagService.getAllTags(), HttpStatus.OK);
    }
}
