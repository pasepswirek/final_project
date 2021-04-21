package pl.sda.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.sda.bussiness.ImageBo;
import pl.sda.bussiness.impl.UserBoImp;
import pl.sda.bussiness.UserValidator;
import pl.sda.dto.UserDto;
import pl.sda.repository.UserRepository;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class RegisterController{

    private static final String USER_REGISTRED_CORRECTLY = "Użytkownik zarejestrowany poprawnie";

    private final UserBoImp userBo;
    private final UserRepository userRepository;
    private final UserValidator validator;
    private final ImageBo imageBo;

    public RegisterController(UserBoImp userBo, UserRepository userRepository, UserValidator validator, ImageBo imageBo) {
        this.userBo = userBo;
        this.userRepository = userRepository;
        this.validator = validator;
        this.imageBo = imageBo;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }


    @PostMapping(value = "/registerUser", consumes = { "multipart/form-data" })
    public String saveUser(@Valid @ModelAttribute(name = "user") UserDto user, BindingResult bindingResult,
                           Model model, @RequestParam("avatarImage") MultipartFile avatarImage) throws IOException {
        if (bindingResult.hasErrors() || validate(user, model)) {
            return "register";
        }
        userBo.saveUser(user, avatarImage);
        model.addAttribute("userRegisteredCorrectly", USER_REGISTRED_CORRECTLY);
        return "login";
    }

    private boolean validate(UserDto user, Model model) {
        String result = validator.notValid(user);
        if (result != null) {
            model.addAttribute("commonError", result);
        }
        return result != null;
    }

}
