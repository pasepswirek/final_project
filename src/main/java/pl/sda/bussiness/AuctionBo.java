package pl.sda.bussiness;

import org.springframework.web.multipart.MultipartFile;
import pl.sda.dto.AuctionDto;

import java.io.IOException;
import java.util.List;

public interface AuctionBo {

    void saveAuction(AuctionDto auctionDto, MultipartFile file) throws IOException;

    List<AuctionDto> findByUsername(String username);

    List<AuctionDto> findAll();
}
