package pl.sda.controller;


import com.sun.xml.internal.ws.wsdl.writer.document.ParamType;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.bussiness.UserBoImp;
import pl.sda.dto.UserDto;
import pl.sda.model.User;
import pl.sda.repository.UserRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.HttpServletResponse;


@Controller
//@Import(UserRepository.class)
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserBoImp userBo;

    @GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public void showuserImage(@RequestBody @PathVariable("id") long id, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg"); // Or whatever format you wanna use
        UserDto user = userBo.getId(id);
//        byte[] imageContent = user.getAvatar();

        InputStream is = new ByteArrayInputStream(user.getAvatar());
        IOUtils.copy(is, response.getOutputStream());
//        Blob ph = user.getAvatar();
//        byte[] imageContent = user.getAvatar();//get image from DAO based on id
//        final HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_PNG);
//        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
    }

//    @RequestMapping(value = "/{id}")
//    public ModelAndView home(@RequestBody @PathVariable("id") long id)  throws IOException {
//        ModelAndView view = new ModelAndView("index");
//        view.addObject("id", id);
//        return view;
//    }
//
//    @RequestMapping(value = "/image/{image_id}", produces = MediaType.IMAGE_PNG_VALUE, method = RequestMethod.GET)
//    public ResponseEntity<byte[]> getImage(@PathVariable("image_id") Long imageId) throws IOException {
//        UserDto user = userBo.getId(imageId);
//        byte[] imageContent = user.getAvatar();
//        /* byte[] imageContent = //get image from DAO based on id */
//        final HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_PNG);
//        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
//    }


    @GetMapping("/index")
    public String getUser(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }


}
