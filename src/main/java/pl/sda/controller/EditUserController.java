package pl.sda.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.bussiness.UserBoImp;
import pl.sda.bussiness.UserValidator;
import pl.sda.dto.UserDto;
import pl.sda.model.User;
import pl.sda.repository.UserRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
//@RequestMapping("/")
public class EditUserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserBoImp userBoImp;

    @Autowired
    private UserValidator validator;


    @GetMapping(value = "/editUser{id}")
    public String editProductView(@PathVariable(name = "id") long id,  Model model){
        User user =  userRepository.findUserById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@Valid @ModelAttribute("user") UserDto user, BindingResult bindingResult, Model model ) {
        if(bindingResult.hasErrors() || validate(user, model)){
            return "/editUser";
        }
        userBoImp.saveUser(user);
        return "index";
    }


    private boolean validate(UserDto user, Model model) {
        String result = validator.notValidChangeData(user);
        if (result != null) {
            model.addAttribute("commonError", result);
        }
        return result != null;
    }


}
