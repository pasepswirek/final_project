package pl.sda.bussiness;


import org.apache.commons.io.IOUtils;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.dto.UserDto;
import pl.sda.model.AccountStatus;
import pl.sda.model.AccountType;
import pl.sda.model.Role;
import pl.sda.model.User;
import pl.sda.repository.RoleRepository;
import pl.sda.repository.UserRepository;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserBoImp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
//    byte[] image = new byte[] { 1, 2, 3 };

    public void saveUser(UserDto userDto){

        User user = new User();



        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setCreateDate(new Date());
        user.setStatus(AccountStatus.ACTIVE);
        user.setType(AccountType.NORMAL);
        user.setCity(userDto.getCity());
        user.setAddress(userDto.getAddress());
//        user.setAvatar(userDto.getAvatar());
        user.setAvatar(getImageByByte(userDto));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("USER"));
        user.setRoles(roles);

        userRepository.save(user);
        System.out.println(userDto);
//        System.out.println(getImageByByte(userDto));
    }


    public UserDto getUser(String username) {
        User user = userRepository.findByUsername(username).get();
        return new UserDto(user);
    }


    public UserDto getId(long id) {
        User user = userRepository.findById(id).get();
        return new UserDto(user);
    }

    public byte[] getImageByByte(UserDto userDto){

       if(userDto.getAvatar() != null){
        byte[] image = new byte[userDto.getAvatar().length] ;

        return image;
       }
       return null ;
    }
//
//    public void getByteToImage(long id,  HttpServletResponse response) throws IOException {
//        User user = userRepository.findById(id).get();
//
//        response.setContentType("image/jpeg");
//        byte[] bytes = user.getAvatar();
//        IOUtils.copy(new ByteArrayInputStream(bytes), response.getOutputStream());
//
////        InputStream is = new ByteArrayInputStream(bytes);
////        IOUtils.copy(is, response.getOutputStream());
//
//    }

}
