package com.example.aet.repository.image;

import org.springframework.web.multipart.MultipartFile;

public interface CustomImageRepository {
    String save(MultipartFile file, String userId);
}
