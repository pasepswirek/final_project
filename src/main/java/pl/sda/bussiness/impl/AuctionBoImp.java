package pl.sda.bussiness.impl;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.sda.assembler.AuctionAssembler;
import pl.sda.bussiness.AuctionBo;
import pl.sda.bussiness.CategoryBo;
import pl.sda.bussiness.UserBo;
import pl.sda.dto.AuctionDto;
import pl.sda.model.Auction;
import pl.sda.repository.AuctionRepository;
import pl.sda.repository.CategoryRepository;

import java.io.IOException;
import java.util.List;

@Service
public class AuctionBoImp implements AuctionBo {

    private final AuctionRepository auctionRepository;
    private final UserBo userBo;
    private final CategoryRepository categoryRepository;
    private final CategoryBo categoryBo;
    private final AuctionAssembler auctionAssembler;

    public AuctionBoImp(AuctionRepository auctionRepository, UserBo userBo,
                        CategoryRepository categoryRepository, CategoryBoImp categoryBoImp,
                        CategoryBo categoryBo, AuctionAssembler auctionAssembler) {
        this.auctionRepository = auctionRepository;
        this.userBo = userBo;
        this.categoryRepository = categoryRepository;
        this.categoryBo = categoryBo;
        this.auctionAssembler = auctionAssembler;
    }

    @Override
    public void saveAuction(AuctionDto auctionDto, MultipartFile file) throws IOException {
        Auction  auction = auctionAssembler.auctionDtoToAuction(auctionDto);

        auctionRepository.save(auction);
        System.out.println(auction.toString());
    }
    @Override
    public List<AuctionDto> findByUsername(String username) {
        return auctionAssembler.auctionToAuctionDtoList(auctionRepository.findByUsername(username));
    }

    @Override
    public List<AuctionDto> findAll() {
        return auctionAssembler.auctionToAuctionDtoList(auctionRepository.findAll());
    }


}
