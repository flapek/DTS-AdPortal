package pl.edu.wat.portal_gloszeniowy.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.portal_gloszeniowy.entities.Offer;
import pl.edu.wat.portal_gloszeniowy.entities.Tag;
import pl.edu.wat.portal_gloszeniowy.models.User;

import java.util.List;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {
    Iterable<Offer> findByUser(User user);
    Iterable<Offer> findAll(Pageable pageable);
}
