package pl.sda.assembler;
import org.springframework.stereotype.Component;
import pl.sda.dto.AuctionDto;
import pl.sda.model.Auction;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuctionAssembler {

    public AuctionDto toDto(Auction auction){
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
        auctionDto.setPicture(auction.getPicture()==null? null : auction.getPicture());

        return auctionDto;
    }

    public List<AuctionDto> auctionDtoList(List<Auction> auctionList){
        List<AuctionDto> auctionsList = new ArrayList<>();
        for (Auction auction : auctionList) {
            auctionsList.add(toDto(auction));
        }
        return  auctionsList;
    }
}
