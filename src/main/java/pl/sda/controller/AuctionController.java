package pl.sda.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.bussiness.AuctionBoImp;
import pl.sda.bussiness.CategoryBoImp;
import pl.sda.dto.AuctionDto;
import pl.sda.dto.CategoryDto;

import javax.validation.Valid;

@Controller
public class AuctionController {

    private final String AUCTION_ADDED_SUCCESSFULLY = "Auction added successfully";

    @Autowired
    CategoryBoImp categoryBoImp;
    @Autowired
    AuctionBoImp auctionBoImp;

    @GetMapping("/auction")
    public String auction(Model model) {
        model.addAttribute("auction", new AuctionDto());
        model.addAttribute("categories", categoryBoImp.findAll());
        return "auction";
    }

    @PostMapping("/saveAuction")
    public String addAuction(@Valid @ModelAttribute(name = "auction") AuctionDto auctionDto, BindingResult bindingResult,
                             Model model) {

        if (bindingResult.hasErrors()) {
            return "auction";
        }
        auctionBoImp.saveAuction(auctionDto);
        model.addAttribute("successfully", AUCTION_ADDED_SUCCESSFULLY);
        model.addAttribute("auction", new AuctionDto());
        return "auction";
    }

}


