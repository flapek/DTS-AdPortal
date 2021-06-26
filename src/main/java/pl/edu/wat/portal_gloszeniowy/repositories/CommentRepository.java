package pl.edu.wat.portal_gloszeniowy.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import pl.edu.wat.portal_gloszeniowy.entities.Comment;
import pl.edu.wat.portal_gloszeniowy.entities.Offer;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

}
