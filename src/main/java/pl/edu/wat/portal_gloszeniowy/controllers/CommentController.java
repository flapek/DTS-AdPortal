package pl.edu.wat.portal_gloszeniowy.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.portal_gloszeniowy.dtos.CommentRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.CommentResponseDto;
import pl.edu.wat.portal_gloszeniowy.services.CommentService;
import pl.edu.wat.portal_gloszeniowy.services.OfferService;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class CommentController {
    private CommentService commentService;
    private OfferService offerService;

    @Autowired
    public CommentController(CommentService commentService, OfferService offerService) {
        this.commentService = commentService;
        this.offerService = offerService;
    }

    @GetMapping(path = "/offer/{offerId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getOfferComments(@PathVariable("offerId") Long offerId)
    {
        List<CommentResponseDto> comments = commentService.getOfferComments(offerId);
        if(comments.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(comments, HttpStatus.OK);
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

    @DeleteMapping(path = "/deleteComment/{offerId}/{commentId}")
    public ResponseEntity deleteComment(@PathVariable("commentId") Long commentId,
                                        @PathVariable("offerId") Long offerId, Principal principal)
    {
        offerService.deleteComment(offerId, commentId);
        commentService.deleteComment(commentId);

        List<CommentResponseDto> comments = commentService.getOfferComments(offerId);
        if(comments.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
