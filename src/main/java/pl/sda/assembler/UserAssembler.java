package pl.sda.assembler;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.bussiness.ImageBo;
import pl.sda.dto.UserDto;
import pl.sda.model.AccountStatus;
import pl.sda.model.AccountType;
import pl.sda.model.Role;
import pl.sda.model.User;
import pl.sda.repository.RoleRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserAssembler {

    private final ImageBo imageBo;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserAssembler(ImageBo imageBo, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.imageBo = imageBo;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto userToUserDto(User user){

        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setId(user.getId());
        userDto.setCity(user.getCity());
        userDto.setStatus(user.getStatus());
        userDto.setType(user.getType());
        userDto.setCreateDate(user.getCreateDate());
        userDto.setAddress(user.getAddress());
        userDto.setAvatar(user.getAvatar());
        userDto.setBase64EncodedImage(imageBo.getImageFromDB(user.getAvatar()));
        return userDto;
    }

    public List<UserDto> useToUserDtoList(List<User> userList){
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : userList) {
            userDtos.add(userToUserDto(user));
        }
        return userDtos;
    }

    public User userDtoToUser(UserDto userDto) throws IOException {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setCreateDate(new Date());
        user.setStatus(AccountStatus.ACTIVE);
        user.setType(AccountType.NORMAL);
        user.setCity(userDto.getCity());
        user.setAddress(userDto.getAddress());
        user.setAvatar(userDto.getAvatarImage().isEmpty() || userDto.getAvatarImage()==null ? null :
                userDto.getAvatarImage().getBytes());
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("USER"));
        user.setRoles(roles);
        return user;
    }
}
