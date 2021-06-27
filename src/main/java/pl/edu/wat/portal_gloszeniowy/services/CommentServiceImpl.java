package pl.edu.wat.portal_gloszeniowy.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.portal_gloszeniowy.dtos.CommentRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.CommentResponseDto;
import pl.edu.wat.portal_gloszeniowy.entities.Comment;
import pl.edu.wat.portal_gloszeniowy.entities.Offer;
import pl.edu.wat.portal_gloszeniowy.repositories.CommentRepository;
import pl.edu.wat.portal_gloszeniowy.repositories.OfferRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{


    private final CommentRepository commentRepository;
    private final OfferRepository offerRepository;
    private final OfferService offerService;

    @Override
    public List<CommentResponseDto> getOfferComments(Long offerId) {
        return offerService.getOffer(offerId).getComments()
                .stream().map(comment -> new CommentResponseDto(comment.getId(), comment.getUser(), comment.getContent()))
                .collect(Collectors.toList());
    }

    @Override
    public void addComment(CommentRequestDto commentRequestDto) {
        Comment comment = new Comment();
        Offer offer = offerService.getOffer(commentRequestDto.getOfferId());
        List<Comment> commentList = offer.getComments();
        comment.setUser(null);
        comment.setOfeer(offer);
        comment.setContent(commentRequestDto.getContent());
        commentRepository.save(comment);
        commentList.add(comment);
        offer.setComments(commentList);
        offerRepository.save(offer);
    }

    @Override
    public void deleteComment(Long commentId) {
        Optional<Comment> commentDB = commentRepository.findById(commentId);
        if(commentDB.isPresent())
        {
            commentRepository.delete(commentDB.get());
        }
        else throw new IllegalArgumentException("Bad id");
    }

}
