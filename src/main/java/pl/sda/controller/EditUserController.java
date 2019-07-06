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

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserBoImp userBoImp;

    @Autowired
    private UserValidator validator;


//    @GetMapping(value = "/editUser{id}")
//    public String editUser(@PathVariable(name = "id") long id,  Model model){
//        User user =  userRepository.findUserById(id);
//        model.addAttribute("user", user);
//        return "editUser";
//    }


    @GetMapping("/editUser{id}")
    public String user(@Valid @PathVariable(name = "id") long id, @ModelAttribute(name = "user") UserDto user, BindingResult bindingResult, Model model) {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        model.addAttribute("user", userBoImp.getUser(username));
        return "editUser";
    }

    @RequestMapping(value = "/saveChange", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("user") UserDto user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "/editUser";
        }
        userBoImp.updateUserByAdmin(user);
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
