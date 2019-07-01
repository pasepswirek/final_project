package pl.sda.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "USER")
@Getter @Setter
public class User {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    @Column
    private String username;
    @Column
    private String password;

    @Column
    private String city;
    @Column
    private String address;

    @Column(updatable = false)
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date createDate;

    @Column
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Column
    @Lob
    private byte[] avatar;

    @Column
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "USER_ID") },
            inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") }
    )
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }


    public User() {
    }
}
