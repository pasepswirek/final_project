package pl.sda.controller;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.bussiness.AuctionBo;
import pl.sda.bussiness.CategoryBo;
import pl.sda.bussiness.ImageUtil;
import pl.sda.dto.AuctionDto;
import pl.sda.dto.UserDto;

import java.util.List;

@Controller
public class SearchAuctionController {

    private static final String RESULT_OF_SEARCH = "Result search for: ";
    private final AuctionBo auctionBo;
    private final CategoryBo categoryBo;

    public SearchAuctionController(AuctionBo auctionBo, CategoryBo categoryBo) {
        this.auctionBo = auctionBo;
        this.categoryBo = categoryBo;
    }


    @GetMapping(value = "/searchAuction", produces = MediaType.IMAGE_PNG_VALUE)
    private String getAllAuction(Model model){
        model.addAttribute("allAuction", auctionBo.findAll());
        model.addAttribute("user", new UserDto());
        model.addAttribute("categories", categoryBo.findAll());
        model.addAttribute("imgUtil", new ImageUtil());
        return "searchAuction";
    }

    @PostMapping(value = "/searchUserAuction", produces = MediaType.IMAGE_PNG_VALUE)
    public String searchUserAuction(@ModelAttribute(name = "user") UserDto userDto, Model model){
        String username;
        List<AuctionDto> auctions;
        username=userDto.getUsername();
        auctions =auctionBo.findByUsername(username) ;
        model.addAttribute("categories", categoryBo.findAll());
        model.addAttribute("allAuction",auctions);
        model.addAttribute("resultOfSearch", RESULT_OF_SEARCH + username);
        model.addAttribute("imgUtil", new ImageUtil());
        return "searchAuction";
    }
}
