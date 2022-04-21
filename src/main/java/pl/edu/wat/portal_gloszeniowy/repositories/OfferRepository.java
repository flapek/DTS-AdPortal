package pl.edu.wat.portal_gloszeniowy.repositories;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.portal_gloszeniowy.entities.Offer;
import pl.edu.wat.portal_gloszeniowy.entities.Tag;
import pl.edu.wat.portal_gloszeniowy.models.User;

import java.util.Collection;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByTagListIn(Collection<List<Tag>> tagList, Pageable pageable);

    List<Offer> findByUser(User user, Pageable pageable);

    List<Offer> findByUserAndTagListIn(User user, Collection<List<Tag>> tagList, Pageable pageable);

    @NotNull
    Page<Offer> findAll(Pageable pageable);

    // TODO https://www.baeldung.com/spring-data-jpa-query
}
