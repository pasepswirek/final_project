package pl.sda.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import pl.sda.model.AccountStatus;
import pl.sda.model.AccountType;
import pl.sda.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Arrays;
import java.util.Date;

@Getter @Setter
public class UserDto {


    @Email(message = "Zły format emaila")
    @NotBlank(message = "Pole nie może być puste")
    private String username;

    @NotBlank(message = "Pole nie może być puste")
    @Pattern(regexp = "^.{5,}$", message = "Hasło jest za krótkie")
    private String password;


    @NotBlank(message = "Pole nie może być puste")
    private String confirmPassword;

    @NotBlank(message = "Pole nie może być puste")
    private String city;

    @NotBlank(message = "Pole nie może być puste")
    private String address;


    private Date createDate;
    private AccountStatus status;
    private byte[] avatar;
    private MultipartFile avatarImage;
    private AccountType type;
    private Long id;
    private String base64EncodedImage;

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", createDate=" + createDate +
                ", status=" + status +
                ", avatar=" + Arrays.toString(avatar) +
                ", type=" + type +
                ", id=" + id +
                '}';
    }

    public UserDto() {
    }


    public UserDto(User user) {
        this.username = user.getUsername();
        this.city = user.getCity();
        this.address = user.getAddress();
        this.avatar =user.getAvatar();
        this.setType(user.getType());
        this.setStatus(user.getStatus());
    }
}
