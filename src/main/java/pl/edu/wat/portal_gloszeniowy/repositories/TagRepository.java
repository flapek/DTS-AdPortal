package pl.edu.wat.portal_gloszeniowy.repositories;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.portal_gloszeniowy.entities.Tag;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
    @NotNull
    List<Tag> findAll();
}
