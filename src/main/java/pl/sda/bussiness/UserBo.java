package pl.sda.bussiness;

import com.sun.xml.internal.ws.encoding.xml.XMLMessage;
import org.springframework.web.multipart.MultipartFile;
import pl.sda.dto.UserDto;
import pl.sda.model.User;

import java.io.IOException;
import java.util.List;

public interface UserBo {

    void saveUser(UserDto userDto, MultipartFile file) throws IOException;

    void deleteUser(Long id);

    UserDto getUserByUserName(String username);

    List<UserDto> findUserByUsername(String username);

    UserDto getById(long id);

    void updateUser(UserDto dto, MultipartFile file);

    void updateUserByAdmin(UserDto dto);

    User getCurrentUser();


}
