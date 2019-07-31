package pl.sda.dto;


import lombok.Getter;
import lombok.Setter;
import pl.sda.model.Category;

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
}
