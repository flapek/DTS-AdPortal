package pl.edu.wat.portal_gloszeniowy.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.portal_gloszeniowy.entities.Car;

public interface CarRepository extends CrudRepository<Car, Long> {
}
