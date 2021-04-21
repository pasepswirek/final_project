package pl.sda.assembler;

import org.springframework.stereotype.Component;
import pl.sda.bussiness.ImageBo;
import pl.sda.dto.UserDto;
import pl.sda.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAssembler {

//    private final ImageBo imageBo;
//
//    public UserAssembler(ImageBo imageBo) {
//        this.imageBo = imageBo;
//    }
    private final ImageBo imageBo;

    public UserAssembler(ImageBo imageBo) {
        this.imageBo = imageBo;
    }

    public UserDto userToDto(User user){

        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setId(user.getId());
        userDto.setCity(user.getCity());
        userDto.setStatus(user.getStatus());
        userDto.setType(user.getType());
        userDto.setCreateDate(user.getCreateDate());
        userDto.setAddress(user.getAddress());
        userDto.setAvatar(user.getAvatar());
//        userDto.setAvatarImage();
        userDto.setBase64EncodedImage(imageBo.getImageFromDB(user.getAvatar()));
        return userDto;
    }

    public List<UserDto> userDtoList(List<User> userList){
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : userList) {
            userDtos.add(userToDto(user));
        }
        return userDtos;
    }
}
