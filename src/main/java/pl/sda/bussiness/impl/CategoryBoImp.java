package pl.sda.bussiness.impl;


import org.springframework.stereotype.Service;
import pl.sda.assembler.CategoryAssembler;
import pl.sda.bussiness.CategoryBo;
import pl.sda.dto.AuctionDto;
import pl.sda.dto.CategoryDto;
import pl.sda.model.Category;
import pl.sda.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryBoImp implements CategoryBo {


    private final CategoryRepository categoryRepository;
    private final CategoryAssembler categoryAssembler;

    public CategoryBoImp(CategoryRepository categoryRepository, CategoryAssembler categoryAssembler) {
        this.categoryRepository = categoryRepository;
        this.categoryAssembler = categoryAssembler;
    }

    @Override
    public List<CategoryDto> findAll() {
        List<CategoryDto> categories = new ArrayList<>();
        for (Category category : categoryRepository.findAll()) {
            categories.add(categoryAssembler.toDto(category));
        }
        return categories;
    }


    @Override
    public CategoryDto getById(AuctionDto auctionDto) {
        Category category = categoryRepository.getOne(auctionDto.getCategoryId());
        return new CategoryDto(category);
    }

}
