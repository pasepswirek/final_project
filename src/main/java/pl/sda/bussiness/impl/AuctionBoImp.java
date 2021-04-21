package pl.sda.bussiness.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.assembler.AuctionAssembler;
import pl.sda.dto.AuctionDto;
import pl.sda.model.Auction;
import pl.sda.repository.AuctionRepository;
import pl.sda.repository.CategoryRepository;

import java.util.Date;
import java.util.List;

@Service
public class AuctionBoImp {

    @Autowired
    private AuctionRepository auctionRepository;
    @Autowired
    private UserBoImp userBoImp;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryBoImp categoryBoImp;
    @Autowired
    AuctionAssembler auctionAssembler;

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
        System.out.println(auction.toString());
    }

    public List<AuctionDto> findByUsername(String username){
        return auctionAssembler.auctionDtoList(auctionRepository.findByUsername(username));
    }
    public List<AuctionDto> findAll(){
        return auctionAssembler.auctionDtoList(auctionRepository.findAll());
    }


}
