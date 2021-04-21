package pl.sda.controller;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.bussiness.ImageUtil;
import pl.sda.bussiness.UserBo;
import pl.sda.bussiness.impl.UserBoImp;
import pl.sda.bussiness.UserValidator;
import pl.sda.dto.UserDto;
import pl.sda.model.User;
import pl.sda.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;


@Controller
public class EditUserController {

    private static final String USER_CHANGE_CORRECTLY = "Zmiany zapisane poprawnie";
    private static final String USER_DELETE_CORRECTLY = "Użytkownik usunięty pomyslnie";

    private final UserRepository userRepository;
    private final UserBo userBo;
    private final UserValidator validator;

    public EditUserController(UserRepository userRepository, UserBoImp userBoImp, UserBo userBo, UserValidator validator) {
        this.userRepository = userRepository;
        this.userBo = userBo;
        this.validator = validator;
    }


    @GetMapping(value = "/editUsers", produces = MediaType.IMAGE_PNG_VALUE)
    public String editUser(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user", new UserDto());
        model.addAttribute("imgUtil", new ImageUtil());
        for (User user : userRepository.findAll()) {
            System.out.println(user.toString());
        }
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
    @GetMapping(value = "/editSimpleUser{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public String getUser(@Valid @PathVariable(name = "id") long id,
                       Model model) {
        User user = userRepository.findUserById(id);
        String username;
        if (user instanceof User) {
            username = user.getUsername();
        } else {
            username = user.toString();
        }
        model.addAttribute("user", userBo.getUserByUsername(username));
        return "editSimpleUser";
    }

    @PostMapping(value = "/saveChange", produces = MediaType.IMAGE_PNG_VALUE)
    public String saveUser(@ModelAttribute("user") UserDto user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "editSimpleUser";
        }
        userBo.updateUserByAdmin(user);
        model.addAttribute("userChangeCorrectly", USER_CHANGE_CORRECTLY);
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("imgUtil", new ImageUtil());
        return "editUsers";
    }

    @GetMapping(value = "/delete/{id}",  produces = MediaType.IMAGE_PNG_VALUE)
    public String deleteUser(@PathVariable(name = "id") long id, @ModelAttribute(name = "user") UserDto userDto, Model model) {
        userDto = userBo.getById(id);
        System.out.println(userDto.toString());
        System.out.println(id);
        userBo.deleteUser(id);
        model.addAttribute("userDeleteCorrectly", USER_DELETE_CORRECTLY);
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("imgUtil", new ImageUtil());
        return "editUsers";
    }

    @PostMapping("/findUserByName")
    public String findUserByName(@ModelAttribute(name = "user") UserDto userDto, Model model) {
//        model.addAttribute("user", new UserDto());
        String username = userDto.getUsername();
        List<UserDto> userList = userBo.getUsersByUsername(username);
        for (UserDto dto : userList) {
            System.out.println(dto.toString());
        }
        model.addAttribute("users", userBo.getUsersByUsername(username));
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
