package pl.edu.wat.portal_gloszeniowy.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.portal_gloszeniowy.dtos.CommentRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.CommentResponseDto;

import java.util.List;

public interface CommentService {

    List<CommentResponseDto> getOfferComments(Long offerId);

    void addComment(CommentRequestDto commentRequestDto);
    void deleteComment(Long commentId);
}
