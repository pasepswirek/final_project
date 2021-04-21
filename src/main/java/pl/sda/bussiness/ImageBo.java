package pl.sda.bussiness;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface ImageBo {

    byte[] saveImageFile(MultipartFile file);

    String getImageFromDB(byte[] bytes);


}
