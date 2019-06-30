package pl.sda.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.sda.dto.UserRegistrationDto;
import pl.sda.model.User;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);


}
