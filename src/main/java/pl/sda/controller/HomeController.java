package pl.sda.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.repository.UserRepository;


@Controller
//@Import(UserRepository.class)
public class HomeController {

//    @Autowired
//    private final UserRepository userRepository;
//
//
//    public HomeController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @GetMapping("/index")
    public String home(Model model) {
//        Iterable<User> users = userRepository.findAll();
//        model.addAttribute("user", users.iterator().toString());

        return "index";
    }

//    @RequestMapping(value = "index", method = RequestMethod.GET)
//    public ModelAndView cliked() {
//        ModelAndView modelAndView;
//        modelAndView = new ModelAndView();
//        modelAndView.addObject("user", userRepository.findAll());
//        modelAndView.setViewName("index");
//        return modelAndView;
//    }

}
