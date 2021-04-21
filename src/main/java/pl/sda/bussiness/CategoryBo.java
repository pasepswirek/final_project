package pl.sda.bussiness;

import pl.sda.assembler.CategoryAssembler;
import pl.sda.dto.AuctionDto;
import pl.sda.dto.CategoryDto;
import pl.sda.repository.CategoryRepository;

import java.util.List;

public interface CategoryBo {


    List<CategoryDto> findAll();

    CategoryDto getById(AuctionDto auctionDto);
}
