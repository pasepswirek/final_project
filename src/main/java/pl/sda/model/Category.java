package pl.sda.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CATEGORY")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Long id;

    @OneToMany(mappedBy="category")
    private Set<Auction> auctions = new HashSet<>();

    @Column(length = 100)
    private String name;

    @Column(length = 300)
    private String description;

    @Lob
    @Column
    private byte[] picture;
}
