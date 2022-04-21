package pl.edu.wat.portal_gloszeniowy.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.portal_gloszeniowy.dtos.CommentRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.CommentResponseDto;
import pl.edu.wat.portal_gloszeniowy.services.CommentService;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class CommentController {

    private final CommentService commentService;

    @GetMapping(path = "/offer/{offerId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getOfferComments(@PathVariable("offerId") Long offerId)
    {
        return new ResponseEntity<>(commentService.getOfferComments(offerId), HttpStatus.OK);
    }

    @PostMapping(path = "/offer/addComment")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity addCommentToOffer(@RequestBody CommentRequestDto commentRequestDto,
                                            Principal principal)
    {
        System.out.println(commentRequestDto.getContent());
        commentService.addComment(commentRequestDto, principal.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deleteComment/{commentId}")
    public ResponseEntity deleteComment(@PathVariable("commentId") Long commentId)
    {
        commentService.deleteComment(commentId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
