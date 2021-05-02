package pl.sda.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.sda.bussiness.AuctionBo;
import pl.sda.bussiness.impl.AuctionBoImp;
import pl.sda.bussiness.CategoryBo;
import pl.sda.dto.AuctionDto;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class AuctionController {

    private final String AUCTION_ADDED_SUCCESSFULLY = "Auction added successfully";


    private final CategoryBo categoryBo;
    private final AuctionBo auctionBo;

    public AuctionController(CategoryBo categoryBo, AuctionBo auctionBo) {
        this.categoryBo = categoryBo;
        this.auctionBo = auctionBo;
    }

    @GetMapping("/auction")
    public String getAuctionForm(Model model) {
        model.addAttribute("auction", new AuctionDto());
        model.addAttribute("categories", categoryBo.findAll());
        return "auction";
    }

    @PostMapping(value = "/saveAuction", consumes = {"multipart/form-data"})
    public String addAuction(@Valid @RequestBody @ModelAttribute(name = "auction") AuctionDto auctionDto, BindingResult bindingResult,
                             Model model, @RequestParam("pictureFile") MultipartFile pictureFile) throws IOException {

        if (bindingResult.hasErrors()) {
            return "auction";
        }
        auctionBo.saveAuction(auctionDto, pictureFile);
        model.addAttribute("successfully", AUCTION_ADDED_SUCCESSFULLY);
//        model.addAttribute("auction", new AuctionDto());
        model.addAttribute("categories", categoryBo.findAll());
        return "auction";
    }


}


