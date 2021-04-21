package pl.sda.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.sda.bussiness.impl.UserBoImp;
import pl.sda.bussiness.UserValidator;
import pl.sda.dto.UserDto;


import javax.validation.Valid;

@Controller
public class UserPanelController  {

    private static final String USER_CHANGE_DATA_CORRECTLY = "Dane poprawnie zmienione. Zaloguj się ponownie";

    private final UserBoImp userBoImp;
    private final UserValidator validator;

    public UserPanelController(UserBoImp userBoImp, UserValidator validator) {
        this.userBoImp = userBoImp;
        this.validator = validator;
    }


    @GetMapping("/userPanel")
    public String user(@Valid @ModelAttribute(name = "user") UserDto user, BindingResult bindingResult, Model model) {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        model.addAttribute("user", userBoImp.getUserByUserName(username));
        return "userPanel";
    }


    @PostMapping(value = "/saveUserPanel", consumes = { "multipart/form-data" })
    public String updateUser(@Valid @RequestBody @ModelAttribute(name = "user") UserDto user, BindingResult bindingResult,
                           Model model, @RequestParam("avatarImage") MultipartFile avatarImage ) {
        if (bindingResult.hasErrors() || validate(user, model)) {
            return "userPanel";
        }
        userBoImp.updateUser(user, avatarImage);
        model.addAttribute("userChangeDateCorrectly", USER_CHANGE_DATA_CORRECTLY);
        return "login";
    }


    private boolean validate(UserDto user, Model model) {
        String result = validator.notValidChangeData(user);
        if (result != null) {
            model.addAttribute("commonError", result);
        }
        return result != null;
    }

}
