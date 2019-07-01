package pl.sda.dto;

import pl.sda.model.AccountStatus;
import pl.sda.model.AccountType;
import pl.sda.model.User;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Arrays;
import java.util.Date;

public class UserDto {


    @Email(message = "Zły format emaila")
    @NotEmpty
    private String username;

    @NotEmpty
    @Pattern(regexp = "^.{5,}$", message = "Hasło jest za krótkie")
    private String password;


    @NotEmpty
    private String confirmPassword;

    @NotBlank(message = "Pole nie może być puste")
    private String city;

    @NotBlank(message = "Pole nie może być puste")
    private String address;


    private Date createDate;

    private AccountStatus status;

    private byte[] avatar;

    private AccountType type;




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

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
                '}';
    }

    public UserDto() {
    }

    public UserDto(User user) {
        this.username = user.getUsername();
        this.city = user.getCity();
        this.address = user.getAddress();
        this.avatar = user.getAvatar();
        this.setType(user.getType());
        this.setStatus(user.getStatus());
    }
}
