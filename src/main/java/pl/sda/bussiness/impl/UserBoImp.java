package pl.sda.bussiness.impl;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.sda.assembler.UserAssembler;
import pl.sda.bussiness.Authorization;
import pl.sda.bussiness.ImageBo;
import pl.sda.bussiness.UserBo;
import pl.sda.dto.UserDto;
import pl.sda.model.AccountStatus;
import pl.sda.model.AccountType;
import pl.sda.model.Role;
import pl.sda.model.User;
import pl.sda.repository.RoleRepository;
import pl.sda.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserBoImp implements UserBo {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Authorization authorization;
    private final UserAssembler userAssembler;
    private final ImageBo imageBo;


    public UserBoImp(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, Authorization authorization, UserAssembler userAssembler, ImageBo imageBo) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorization = authorization;
        this.userAssembler = userAssembler;
        this.imageBo = imageBo;
    }

    @Override
    public void saveUser(UserDto userDto, MultipartFile file) throws IOException {
        User user = userAssembler.userDtoToUser(userDto);
        userRepository.save(user);
        System.out.println(userDto.toString());

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(userRepository.findUserById(id));
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username).get();
        return new UserDto(user);
    }

    @Override
    public List<UserDto> getUsersByUsername(String username) {
        return userAssembler.useToUserDtoList(userRepository.findByUsername(username));
    }

    @Override
    public UserDto getById(long id) {
        User user = userRepository.findById(id).get();
        return new UserDto(user);
    }

    @Override
    public void updateUser(UserDto dto, MultipartFile file) {
        User user = userRepository.findUserByUsername(dto.getUsername()).get();
        if (!file.isEmpty()) {
            user.setAvatar(imageBo.saveImageFile(file));
        }
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setCity(dto.getCity());
        user.setAddress(dto.getAddress());
        user.setUsername(dto.getUsername());
        userRepository.save(user);
    }

    @Override
    public void updateUserByAdmin(UserDto dto) {
        User user = userRepository.findUserByUsername(dto.getUsername()).get();
        user.setStatus(dto.getStatus());
        user.setType(dto.getType());
        userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {
        return userRepository.findUserByUsername(authorization.getUsername()).get();
    }


//    public Role getRole(String username){
//        Role role = roleRepository.findByUserName(username);
//        return   role;
//    }
}
