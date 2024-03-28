package com.example.aet.repository.image;

import com.example.aet.exception.model.FileUploadException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Repository
@RequiredArgsConstructor
public class CustomImageRepositoryImpl implements CustomImageRepository {
    private final GridFsTemplate gridFsTemplate;

    @Override
    public String save(MultipartFile file, String userId) {

        DBObject metadata = new BasicDBObject();
        metadata.put("fileSize", file.getSize());
        metadata.put("userId", userId);

        Object fileID;
        try {
            fileID = gridFsTemplate.store(
                    file.getInputStream(),
                    file.getOriginalFilename(),
                    file.getContentType(),
                    metadata
            );
        } catch (IOException e) {
            throw new FileUploadException(e.getMessage());
        }

        return fileID.toString();
    }
}
