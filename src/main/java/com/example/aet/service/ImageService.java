package com.example.aet.service;

import com.example.aet.exception.model.FileDownloadException;
import com.example.aet.exception.model.NotFoundException;
import com.example.aet.model.image.dto.LoadFile;
import com.example.aet.repository.image.CustomImageRepository;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {
    private final GridFsTemplate gridFsTemplate;
    private final GridFsOperations gridFsOperations;
    private final CustomImageRepository imageRepository;

    public String save(MultipartFile file, String userId) {
        return imageRepository.save(file, userId);
    }

    public LoadFile downloadFile(String id) {
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));

        if (gridFSFile == null || gridFSFile.getMetadata() == null) {
            log.error("could not find image with id {}", id);
            throw new NotFoundException("could not find image with id %s".formatted(id));
        }

        String filename = gridFSFile.getFilename();
        String fileType = gridFSFile.getMetadata().get("_contentType").toString();
        String fileSize = gridFSFile.getMetadata().get("fileSize").toString();
        byte[] bytes;

        try {
            bytes = IOUtils.toByteArray(gridFsOperations.getResource(gridFSFile).getInputStream());
        } catch (IOException e) {
            log.error("error while transforming the file to byte array: {}", e.getMessage());
            throw new FileDownloadException("error while transforming the file to byte array: %s".formatted(e.getMessage()));
        }

        return new LoadFile(filename, fileType, fileSize, bytes);
    }
}
