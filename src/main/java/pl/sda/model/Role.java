package pl.sda.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ROLE")
@Getter @Setter
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "ROLE_ID")
    private Long id;

    private String name;

}
