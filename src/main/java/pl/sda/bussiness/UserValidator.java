package pl.sda.bussiness;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.sda.dto.UserDto;
import pl.sda.repository.UserRepository;

@Component
public class UserValidator {
    private static final String USER_EXISTS_MSG = "Użytkownik o takim loginie istnieje w systemie";
    private static final String WRONG_PASS_MSG = "Hasła nie są takie same";

    @Autowired
    private UserRepository userRepository;

    public String notValid(UserDto dto) {
        if (checkUserAlreadyExists(dto)) {
            return USER_EXISTS_MSG;
        }
        if (checkPasswordsNotEquals(dto)) {
            return WRONG_PASS_MSG;
        }
        return null;
    }

    public String notValidChangeData(UserDto dto) {
        if (checkPasswordsNotEquals(dto)) {
            return WRONG_PASS_MSG;
        }
        return null;
    }

    private boolean checkUserAlreadyExists(UserDto dto) {
        return userRepository.findUserByUsername(dto.getUsername()).isPresent();
    }

    private boolean checkPasswordsNotEquals(UserDto dto) {
        return !dto.getPassword().equals(dto.getConfirmPassword());
    }
}
