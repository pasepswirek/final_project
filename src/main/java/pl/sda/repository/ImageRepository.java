package pl.sda.repository;

import org.springframework.web.multipart.MultipartFile;

public interface ImageRepository {

    void saveImageFile(MultipartFile file);
}
