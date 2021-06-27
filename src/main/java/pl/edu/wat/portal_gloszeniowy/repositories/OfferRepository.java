package pl.edu.wat.portal_gloszeniowy.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.portal_gloszeniowy.entities.Offer;
import pl.edu.wat.portal_gloszeniowy.entities.Tag;

import java.util.List;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {

}
