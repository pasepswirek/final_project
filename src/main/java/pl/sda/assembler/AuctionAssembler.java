package pl.sda.assembler;
import org.springframework.stereotype.Component;
import pl.sda.bussiness.UserBo;
import pl.sda.dto.AuctionDto;
import pl.sda.model.Auction;
import pl.sda.repository.CategoryRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AuctionAssembler {

    private final CategoryRepository categoryRepository;
    private final UserBo userBo;

    public AuctionAssembler(CategoryRepository categoryRepository, UserBo userBo) {
        this.categoryRepository = categoryRepository;
        this.userBo = userBo;
    }

    public AuctionDto auctionToAuctionDto(Auction auction){
        AuctionDto auctionDto = new AuctionDto();
        auctionDto.setId(auction.getId());
        auctionDto.setTitle(auction.getTitle());
        auctionDto.setBuyNowAmount(auction.getBuyNowAmount());
        auctionDto.setDescription(auction.getDescription());
        auctionDto.setCreationDate(auction.getCreationDate());
        auctionDto.setEndDate(auction.getEndDate());
        auctionDto.setMinimumAmount(auction.getMinimumAmount());
        auctionDto.setLocation(auction.getLocation());
        auctionDto.setPromoted(auction.isPromoted());
        auctionDto.setCategoryId(auction.getCategory()== null ? null : auction.getCategory().getId());
        auctionDto.setPicture(auction.getPicture());

        return auctionDto;
    }

    public List<AuctionDto> auctionToAuctionDtoList(List<Auction> auctionList){
        List<AuctionDto> auctionsList = new ArrayList<>();
        for (Auction auction : auctionList) {
            auctionsList.add(auctionToAuctionDto(auction));
        }
        return  auctionsList;
    }

    public Auction auctionDtoToAuction(AuctionDto auctionDto) throws IOException {
        Auction auction = new Auction();
        auction.setId(auctionDto.getId());
        auction.setTitle(auctionDto.getTitle());
        auction.setDescription(auctionDto.getDescription());
        auction.setMinimumAmount(auctionDto.getMinimumAmount());
        auction.setBuyNowAmount(auctionDto.getBuyNowAmount());
        auction.setCategory(categoryRepository.getOne(auctionDto.getCategoryId()));
        auction.setCreationDate(new Date());
        auction.setEndDate(auctionDto.getEndDate());
        auction.setLocation(auctionDto.getLocation());
        auction.setUser(userBo.getCurrentUser());
        auction.setPicture(auctionDto.getPictureFile().isEmpty() || auctionDto.getPictureFile()==null ?
                null : auctionDto.getPictureFile().getBytes());


        return auction;
    }
}
