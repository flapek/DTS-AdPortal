package pl.edu.wat.portal_gloszeniowy.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.portal_gloszeniowy.dtos.CommentRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.CommentResponseDto;
import pl.edu.wat.portal_gloszeniowy.entities.Offer;

import java.util.List;

public interface CommentService {

    List<CommentResponseDto> getOfferComments(Long offerId);

    List<CommentResponseDto> addComment(CommentRequestDto commentRequestDto, String userName);
    void deleteComment(Long commentId);
}
