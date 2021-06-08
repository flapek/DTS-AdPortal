package pl.edu.wat.portal_gloszeniowy.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.portal_gloszeniowy.entities.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
}
