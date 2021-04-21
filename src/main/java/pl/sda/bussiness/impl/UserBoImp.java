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

    //    @Autowired
//    private MultipartFile file;

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
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setCreateDate(new Date());
        user.setStatus(AccountStatus.ACTIVE);
        user.setType(AccountType.NORMAL);
        user.setCity(userDto.getCity());
        user.setAddress(userDto.getAddress());
//        user.setAvatar(userDto.getAvatarImage().getBytes());
        user.setAvatar(imageBo.saveImageFile(file));
//        user.setAvatar("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".getBytes());
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("USER"));
        user.setRoles(roles);

        userRepository.save(user);
        System.out.println(userDto);

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(userRepository.findUserById(id));
    }

    @Override
    public UserDto getUserByUserName(String username) {
        User user = userRepository.findUserByUsername(username).get();
        return new UserDto(user);
    }

    @Override
    public List<UserDto> findUserByUsername(String username) {
        return userAssembler.userDtoList(userRepository.findByUsername(username));
    }

    @Override
    public UserDto getById(long id) {
        User user = userRepository.findById(id).get();
        return new UserDto(user);
    }



    @Override
    public void updateUser(UserDto dto, MultipartFile file) {
        User user = userRepository.findUserByUsername(dto.getUsername()).get();
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setCity(dto.getCity());
        user.setAddress(dto.getAddress());
        user.setUsername(dto.getUsername());
        user.setAvatar(imageBo.saveImageFile(file));
        userRepository.save(user);
    }

    @Override
    public void updateUserByAdmin(UserDto dto) {
        User user = userRepository.findUserByUsername(dto.getUsername()).get();
//        user.setAddress(dto.getAddress());
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
