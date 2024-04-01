package com.example.aet.repository.image;

import com.example.aet.exception.model.FileUploadException;
import com.example.aet.exception.model.NotFoundException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
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
            log.error("error while saving file to database: {}", e.getMessage());
            throw new FileUploadException("error while saving file to database: %s".formatted(e.getMessage()));
        }

        return fileID.toString();
    }

    @Override
    public Optional<GridFSFile> find(String id) {
        GridFSFile gridFSFile = gridFsTemplate.findOne(
                new Query(Criteria.where("_id").is(id))
        );

        if (gridFSFile == null) {
            return Optional.empty();
        }

        return Optional.of(gridFSFile);
    }
}
