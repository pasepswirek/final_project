package pl.sda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
//@RequestMapping("/userPanel")
public class UserPanelController {

    private static final String USER_CHANGE_DATA_CORRECTLY = "Dane poprawnie zmienione. Zaloguj się ponownie";

    @Autowired
    private UserBoImp userBo;

    @Autowired
    private UserValidator validator;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/userPanel")
    public String user(@Valid   @ModelAttribute(name = "user") UserDto user, BindingResult bindingResult, Model model) {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        model.addAttribute("user", userBo.getUser(username));


        return "userPanel";
    }



        @PostMapping("saveUserPanel")
    public   String saveUser(@Valid  @RequestBody @ModelAttribute(name = "user") UserDto user, BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors() || validate(user, model)) {
            return "userPanel";
        }

//        userBo.updateUser(user);
        userBo.saveUser(user);
        model.addAttribute("userChangeDateCorrectly", USER_CHANGE_DATA_CORRECTLY);
        return "login";
    }
//    @PostMapping("saveUserPanel/{id}")
//    public String updateStudent(@PathVariable("id") long id,
//                                @Valid @ModelAttribute(name = "user") User user, BindingResult result,
//                                Model model) {
//        if (result.hasErrors()) {
//            user.setId(id);
//            return "userPanel";
//        }
//        userRepository.save(user);
////        userBo.saveUser(user);
//        model.addAttribute("userChangeDateCorrectly", USER_CHANGE_DATA_CORRECTLY);
//        return "login";
//    }

//    @PutMapping(value="/{id}") // Validates the request body as a Student type
//    public ResponseEntity<User> updateStudent(@Valid @RequestBody User user, @PathVariable long id){
//        // Finds student by id, maps it's content, updates new values and save. Throws an exception if not found.
//        return userRepository.findById(id).map((toUpdate) -> {
//            toUpdate.setCity((user.getCity()));
//            User update = userRepository.save(toUpdate);
//            return ResponseEntity.ok().body(update);
//        }).orElse(ResponseEntity.notFound().build());
//    }


    private boolean validate(UserDto user, Model model) {
        String result = validator.notValidChangeData(user);
        if (result != null) {
            model.addAttribute("commonError", result);
        }
        return result != null;
    }

}
