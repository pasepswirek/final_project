package pl.sda.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "USER_ID") },
            inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") }
    )
    private List<Role> roles;

    @OneToMany(mappedBy="user")
    private Set<Auction> auctions = new HashSet<>();


    public User() {
    }
}
