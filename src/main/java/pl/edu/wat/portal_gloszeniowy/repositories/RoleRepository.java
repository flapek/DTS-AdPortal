package pl.edu.wat.portal_gloszeniowy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.portal_gloszeniowy.models.ERole;
import pl.edu.wat.portal_gloszeniowy.models.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{
    Optional<Role> findByName(ERole name);
}
