package pl.sda.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
import pl.sda.model.Category;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

@Getter @Setter
@NoArgsConstructor
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
    private MultipartFile pictureFile;
    private String base64EncodedImage;

    @Override
    public String toString() {
        return "AuctionDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", picture=" + Arrays.toString(picture) +
                ", minimumAmount=" + minimumAmount +
                ", buyNowAmount=" + buyNowAmount +
                ", isPromoted=" + isPromoted +
                ", location='" + location + '\'' +
                ", creationDate=" + creationDate +
                ", endDate=" + endDate +
                ", categoryId=" + categoryId +
                '}';
    }
}

