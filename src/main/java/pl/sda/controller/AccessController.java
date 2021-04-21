//package pl.sda.controller;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//import pl.sda.bussiness.impl.UserBoImp;
//import pl.sda.dto.UserDto;
//import pl.sda.model.Role;
//import pl.sda.model.User;
//import pl.sda.repository.UserRepository;
//import javax.servlet.http.HttpServletRequest;
//
//@Controller
////@RequestMapping("/")
//public class AccessController {
//
//    @Autowired
//    private UserBoImp userBoImp;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping(value = "/default")
//    public String defaultAfterLogin(HttpServletRequest request) {
//        if (request.isUserInRole("ROLE_ADMIN")) {
//            return "adminHome";
//        }
//        if (request.isUserInRole("ROLE_USER")) {
//            return "userHome";
//        }
//        return "login";
//    }
//
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @GetMapping(value = "/default")
//    public ModelAndView defaultAfterLogin(HttpServletRequest request) {
//        ModelAndView model = new ModelAndView();
//        if (request.isUserInRole("ROLE_ADMIN")) {
//            model.setViewName("redirect:/adminHome");
//            return model;
//        }
//        if (request.isUserInRole("ROLE_USER")) {
//            model.setViewName("redirect:/userHome");
//            return model;
//        }
//        model.setViewName("login");
//        return model;
//    }
//
//
//
//    @GetMapping(value="/adminHome")
//    public ModelAndView adminHome(){
//        ModelAndView modelAndView = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        UserDto user =  userBoImp.getUser(auth.getName());
//
//        modelAndView.addObject("userName", "Welcome " + user.getUsername());
//        modelAndView.addObject("adminMessage","This Page is available to Users with Admin Role");
//        modelAndView.setViewName("adminHome");
//        return modelAndView;
//    }
//
//    @GetMapping(value={ "/","/userHome"})
//    public ModelAndView userHome(){
//        ModelAndView modelAndView = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        UserDto user =  userBoImp.getUser(auth.getName());
//
//        modelAndView.addObject("userName", "Welcome " + user.getUsername());
//        modelAndView.addObject("userMessage","This Page is available to Users with User Role");
//        modelAndView.setViewName("userHome");
//        return modelAndView;
//    }
//}
