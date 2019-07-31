package pl.sda.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.assembler.AuctionAssembler;
import pl.sda.dto.AuctionDto;
import pl.sda.dto.UserDto;
import pl.sda.model.Auction;
import pl.sda.repository.AuctionRepository;
import pl.sda.repository.UserRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchAuctionController {

    private static final String RESULT_OF_SEARCH = "User listing search result: ";
    @Autowired
    private AuctionRepository auctionRepository;
    @Autowired
    private AuctionAssembler auctionAssembler;


    @GetMapping("/searchAuction")
    private String getAllAuction(Model model){
        model.addAttribute("allAuction", auctionRepository.findAll());
        model.addAttribute("user", new UserDto());
        return "searchAuction";
    }

    @PostMapping("/searchUserAuction")
    public String searchUserAuction(@ModelAttribute(name = "user") UserDto userDto, Model model){
        String username;
        List<Auction> auctions;
        System.out.println(userDto.getUsername());
//        if(bindingResult.hasErrors()){
//            return "searchAuction";
//        }
        username=userDto.getUsername();
        auctions =auctionRepository.findByUsername(username);
        for (Auction auction : auctions) {
            System.out.println(auction);
        }
        model.addAttribute("allAuction",auctionAssembler.auctionDtoList(auctions));
        model.addAttribute("resultOfSearch", RESULT_OF_SEARCH + username);

        return "searchAuction";
    }
}
