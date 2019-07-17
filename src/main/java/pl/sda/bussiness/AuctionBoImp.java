package pl.sda.bussiness;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.dto.AuctionDto;
import pl.sda.model.Auction;
import pl.sda.repository.AuctionRepository;
import pl.sda.repository.CategoryRepository;

import java.util.Date;

@Service
public class AuctionBoImp {

    @Autowired
    AuctionRepository auctionRepository;
    @Autowired
    UserBoImp userBoImp;
    @Autowired
    CategoryRepository categoryRepository;

    public void saveAuction(AuctionDto auctionDto) {

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
        auction.setPicture(auctionDto.getPicture());
        auction.setUser(userBoImp.getCurrentUser());



        auctionRepository.save(auction);
        System.out.println(auction);

    }
}
