package pl.sda.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.bussiness.impl.UserBoImp;
import pl.sda.dto.UserDto;
import pl.sda.repository.UserRepository;

import java.io.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
//@Import(UserRepository.class)
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserBoImp userBoImp;



//    @GetMapping(value = "/avatar", produces = MediaType.IMAGE_JPEG_VALUE)
//    public ResponseEntity<byte[]> showuserImage(@RequestParam(value = "id", required = false) long id,
//                                                HttpServletResponse response, Model model) throws IOException {
////        response.setContentType("image/jpeg"); // Or whatever format you wanna use
//        UserDto user = userBo.getId(id);
//
//
//        InputStream is = new ByteArrayInputStream(user.getAvatar());
//        IOUtils.copy(is, response.getOutputStream());
////       response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
//        byte[] imageContent = user.getAvatar();//get image from DAO based on id
//        final HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_JPEG);
//        headers.setContentType(MediaType.IMAGE_PNG);
//        headers.setContentType(MediaType.IMAGE_GIF);
//
//        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
//    }

    @GetMapping(value = "/avatar", produces = MediaType.IMAGE_JPEG_VALUE)
    public void showUserImage(@RequestParam("id") long id,
                              HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {

        UserDto user = userBoImp.getById(id);
//        byte[] imageContent = Base64.encodeBase64(user.getAvatar());
//        byte[] encode = java.util.Base64.getEncoder().encode(user.getAvatar());
//        model.addAttribute("image", new String(encode, "UTF-8"));

//        byte[] imageContent = user.getAvatar();
//        String catImage =  Base64.encodeBase64String(imageContent);
//        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
//        FileOutputStream fos = new FileOutputStream("D:\\test"+id+".jpeg");
//        fos.write(encode);
//        fos.close();
//        response.getOutputStream().write(imageContent);
//        response.getOutputStream().close();
//        System.out.println("image "+id+" :"+ encode);

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

//    @Secured("ROLE_ADMIN")
//    @GetMapping(value = "/index")
//    public String getUser(Model model) {
//        model.addAttribute("users", userRepository.findAll());
//        return "index";
//    }


}
