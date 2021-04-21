package pl.sda.assembler;

import org.springframework.stereotype.Component;
import pl.sda.dto.CategoryDto;
import pl.sda.model.Category;

@Component
public class CategoryAssembler {

    public CategoryDto toDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setPicture(category.getPicture());
        return dto;
    }
}
