package pl.edu.wat.portal_gloszeniowy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wat.portal_gloszeniowy.models.ERole;
import pl.edu.wat.portal_gloszeniowy.models.Role;
import pl.edu.wat.portal_gloszeniowy.models.User;
import pl.edu.wat.portal_gloszeniowy.repositories.RoleRepository;
import pl.edu.wat.portal_gloszeniowy.repositories.UserRepository;
import pl.edu.wat.portal_gloszeniowy.services.OfferServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class PortalGloszeniowyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalGloszeniowyApplication.class, args);
    }


//    @Bean
//    CommandLineRunner run(UserRepository userRepository, RoleRepository roleRepository, OfferServiceImpl offerService) {
//        return args -> {
////            Role role = new Role();
////            role.setName(ERole.ROLE_ADMIN);
////            roleRepository.save(role);
////            Set<Role> roles = new HashSet<>();
////            roles.add(role);
//            User user = new User("admin", "admin@gmail.com", "adminadmin");
////            user.setRoles(roles);
//            userRepository.save(user);
//
//            offerService.addOffer(null, "title", 321, "to jest oferta", null, "admin");
////            roleService.addRole(new
////            RoleRequestDto("", RoleEnum.ROLE_ADMIN));
////            roleService.addRole(new RoleRequestDto("", RoleEnum.ROLE_USER));
////            userService.addAccount(new UserRequestDto("Admin", "Admin", "admin", "admin@cex.com", "secretpassword"));
////            roleRepository.save(new Role(RoleEnum.ROLE_USER));
////            roleRepository.save(new Role(RoleEnum.ROLE_ADMIN));
////            userService.addRoleToUser(new RoleRequestDto("johnie", RoleEnum.ROLE_USER));
////            userService.addRoleToUser(new RoleRequestDto("johnie", RoleEnum.ROLE_ADMIN));
////            userService.addRoleToUser(new RoleRequestDto("nowaczek", RoleEnum.ROLE_USER));
//        };
//    }
}
