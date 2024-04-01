package com.example.aet.repository.image;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface CustomImageRepository {
    String save(MultipartFile file, String userId);
    Optional<GridFSFile> find(String id);
}
