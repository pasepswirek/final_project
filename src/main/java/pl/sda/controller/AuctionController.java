package pl.sda.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.sda.bussiness.impl.AuctionBoImp;
import pl.sda.bussiness.CategoryBo;
import pl.sda.dto.AuctionDto;

import javax.validation.Valid;

@Controller
public class AuctionController {

    private final String AUCTION_ADDED_SUCCESSFULLY = "Auction added successfully";


    private final CategoryBo categoryBo;
    private final AuctionBoImp auctionBoImp;

    public AuctionController(CategoryBo categoryBo, AuctionBoImp auctionBoImp) {
        this.categoryBo = categoryBo;
        this.auctionBoImp = auctionBoImp;
    }

    @GetMapping("/auction")
    public String auction(Model model) {
        model.addAttribute("auction", new AuctionDto());
        model.addAttribute("categories", categoryBo.findAll());
        return "auction";
    }

    @PostMapping("/saveAuction")
    public String addAuction(@Valid @RequestBody @ModelAttribute(name = "auction") AuctionDto auctionDto, BindingResult bindingResult,
                             Model model) {

        if (bindingResult.hasErrors()) {
            return "auction";
        }
        auctionBoImp.saveAuction(auctionDto);
        model.addAttribute("successfully", AUCTION_ADDED_SUCCESSFULLY);
//        model.addAttribute("auction", new AuctionDto());
        model.addAttribute("categories", categoryBo.findAll());
        return "auction";
    }


}


