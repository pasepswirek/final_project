package pl.sda.bussiness;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.dto.CategoryDto;
import pl.sda.model.Category;
import pl.sda.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryBoImp {


    @Autowired
    CategoryRepository categoryRepository;

    public List<CategoryDto> findAll() {
        List<CategoryDto> categories = new ArrayList<>();
        for (Category category : categoryRepository.findAll()) {
            categories.add(toDto(category));
        }
        return categories;
    }


    private CategoryDto toDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setPicture(category.getPicture());
        return dto;
    }
}
