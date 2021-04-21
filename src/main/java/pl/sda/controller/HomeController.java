package pl.sda.controller;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.bussiness.UserBo;
import pl.sda.dto.UserDto;
import pl.sda.repository.UserRepository;

import java.io.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class HomeController {

    private final UserRepository userRepository;
    private final UserBo userBo;

    public HomeController(UserRepository userRepository, UserBo userBo) {
        this.userRepository = userRepository;
        this.userBo = userBo;
    }


    @GetMapping(value = "/avatar", produces = MediaType.IMAGE_JPEG_VALUE)
    public void showUserImage(@RequestParam("id") long id,
                              HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {

        UserDto user = userBo.getById(id);
    }

}
