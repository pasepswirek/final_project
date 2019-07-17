package pl.sda.dto;


import lombok.Getter;
import lombok.Setter;
import pl.sda.model.Category;

import java.math.BigDecimal;
import java.util.Date;

@Getter @Setter
public class AuctionDto {


    private Long id;
    private String title;
    private String description;
    private byte[] picture;
    private BigDecimal minimumAmount;
    private BigDecimal buyNowAmount;
    private boolean isPromoted;
    private String location;
    private Date creationDate;
    private Date endDate;
    private Long categoryId;
}
