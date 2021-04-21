package pl.sda.bussiness.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.sda.bussiness.ImageBo;

import java.io.IOException;
import java.util.Base64;

@Slf4j
@Service
public class ImageBoImpl implements ImageBo {

    @Override
    public byte[] saveImageFile(MultipartFile file) {
        byte[] images = new byte[0];
        try{
             images = new byte[file.getBytes().length];

            int i=0;
            for (byte image : file.getBytes()) {
                images[i++] = image;

            }
            return images;

        }
        catch (IOException e){
            log.error("Error occurred", e);
            e.printStackTrace();
        }
        return images;
    }

    @Override
    public String getImageFromDB(byte[] bytes) {
        return Base64.getMimeEncoder().encodeToString(bytes);
    }

}
