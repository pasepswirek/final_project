package pl.sda.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.bussiness.impl.AuctionBoImp;
import pl.sda.bussiness.impl.CategoryBoImp;
import pl.sda.dto.AuctionDto;
import pl.sda.dto.UserDto;

import java.util.List;

@Controller
public class SearchAuctionController {

    private static final String RESULT_OF_SEARCH = "User listing search result: ";
    @Autowired
    private  AuctionBoImp auctionBoImp;
    @Autowired
    private CategoryBoImp categoryBoImp;


    @GetMapping("/searchAuction")
    private String getAllAuction(Model model){
        model.addAttribute("allAuction", auctionBoImp.findAll());
        model.addAttribute("user", new UserDto());
        model.addAttribute("categories", categoryBoImp.findAll());
        return "searchAuction";
    }

    @PostMapping("/searchUserAuction")
    public String searchUserAuction(@ModelAttribute(name = "user") UserDto userDto, Model model){
        String username;
        List<AuctionDto> auctions;
        username=userDto.getUsername();
        auctions =auctionBoImp.findByUsername(username) ;
        model.addAttribute("categories", categoryBoImp.findAll());
        model.addAttribute("allAuction",auctions);
        model.addAttribute("resultOfSearch", RESULT_OF_SEARCH + username);
        return "searchAuction";
    }
}
