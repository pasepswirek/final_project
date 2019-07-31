package pl.sda.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
//@RequestMapping("/")
public class EditUserController {

    private static final String USER_CHANGE_CORRECTLY = "Zmiany zapisane poprawnie";
    private static final String USER_DELETE_CORRECTLY = "Użytkownik usunięty pomyslnie";

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserBoImp userBoImp;

    @Autowired
    private UserValidator validator;


    @GetMapping(value = "/editUsers")
    public String editUser(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "editUsers";
    }


//    @GetMapping("/editSimpleUser{id}")
//    public String user(@Valid @PathVariable(name = "id") long id, @ModelAttribute(name = "user") UserDto user,
//                       BindingResult bindingResult, Model model) {
//        String username;
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println(principal);
//        if (principal instanceof UserDetails) {
//            username = ((UserDetails) principal).getUsername();
//        } else {
//            username = principal.toString();
//        }
//        model.addAttribute("user", userBoImp.getUser(username));
//        return "editSimpleUser";
//    }
    @GetMapping("/editSimpleUser{id}")
    public String user(@Valid @PathVariable(name = "id") long id, @ModelAttribute(name = "user") UserDto userDto,
                       Model model) {
        User user= userRepository.findUserById(id);
        String username;
        if (user instanceof User) {
            username =  user.getUsername();
        } else {
            username = user.toString();
        }
        model.addAttribute("user", userBoImp.getUser(username));
        return "editSimpleUser";
    }

    @RequestMapping(value = "/saveChange", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") UserDto user, BindingResult bindingResult,  Model model) {

        if (bindingResult.hasErrors()) {
            return "editSimpleUser";
        }
        userBoImp.updateUserByAdmin(user);
        model.addAttribute("userChangeCorrectly", USER_CHANGE_CORRECTLY);
        model.addAttribute("users", userRepository.findAll());
        return "editUsers";
    }

    @GetMapping("/delete/{id}")
    public String deleteUeer(@PathVariable(name = "id") long id, @ModelAttribute(name = "user") UserDto userDto,  Model model) {
        userDto = userBoImp.getId(id);
        System.out.println(userDto.toString());
        System.out.println(id);
        userBoImp.deleteUser(id);
        model.addAttribute("userDeleteCorrectly", USER_DELETE_CORRECTLY);
        model.addAttribute("users", userRepository.findAll());
        return "editUsers";
    }


    private boolean validate(UserDto user, Model model) {
        String result = validator.notValidChangeData(user);
        if (result != null) {
            model.addAttribute("commonError", result);
        }
        return result != null;
    }


}
