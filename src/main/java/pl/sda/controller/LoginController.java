package pl.sda.controller;


import com.sun.webkit.Timer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.bussiness.UserBo;
import pl.sda.dto.UserDto;
import pl.sda.model.User;
import pl.sda.repository.UserRepository;

import java.util.Optional;

@Controller
public class LoginController {

    private static final String BLOCKED_ACCOUNT = "Account blocking. Lack of access";

    private final UserBo userBo;
    private final UserRepository userRepository;

    public LoginController(UserBo userBo, UserRepository userRepository) {
        this.userBo = userBo;
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/appLogin")
    public String loginValidation(@RequestParam("username") String username, @RequestParam("userDto") UserDto dto,
                                  Model model)
    {
//        User user1 = userRepository.findUserByUsername(username);
        User user = userRepository.findUserByUsername(dto.getUsername());

        if(user.getStatus().equals("BLOCKED")){
        model.addAttribute("accountBlocking", BLOCKED_ACCOUNT);
        }
        return null;
    }

}
