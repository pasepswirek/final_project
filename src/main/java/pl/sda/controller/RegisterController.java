package pl.sda.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.bussiness.UserBoImp;
import pl.sda.bussiness.UserValidator;
import pl.sda.dto.UserDto;
import pl.sda.model.User;
import pl.sda.repository.UserRepository;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private static final String USER_REGISTRED_CORRECTLY = "Użytkownik zarejestrowany poprawnie";

    @Autowired
    private UserBoImp userBo;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserValidator validator;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }


    @PostMapping("/registerUser")
    public String saveUser(@Valid @ModelAttribute(name = "user") UserDto user, BindingResult bindingResult, Model model ) {
        if (bindingResult.hasErrors() || validate(user, model)) {
            return "register";
        }

        userBo.saveUser(user);
        model.addAttribute("userRegisteredCorrectly", USER_REGISTRED_CORRECTLY);
        return "login";
    }

    private boolean validate(UserDto user, Model model) {
        String result = validator.notValid(user);
        if (result !=null) {
            model.addAttribute("commonError", result);
        }
        return result !=null;
    }

}
