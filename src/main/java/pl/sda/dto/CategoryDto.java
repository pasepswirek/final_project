package pl.sda.dto;


import lombok.Getter;
import lombok.Setter;
import pl.sda.model.Category;

import java.util.Arrays;

@Getter @Setter
public class CategoryDto {

    private Long id;
    private String name;
    private String description;
    private byte[] picture;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description =category.getDescription() ;
        this.picture = category.getPicture();
    }

    public CategoryDto() {
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", picture=" + Arrays.toString(picture) +
                '}';
    }
}
