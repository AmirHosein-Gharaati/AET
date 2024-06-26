package com.example.aet.service;

import com.example.aet.exception.model.FileDownloadException;
import com.example.aet.exception.model.NotFoundException;
import com.example.aet.model.image.dto.LoadFile;
import com.example.aet.model.image.dto.UploadResponse;
import com.example.aet.repository.image.CustomImageRepository;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {
    private final CustomImageRepository imageRepository;
    private final GridFsOperations gridFsOperations;

    public UploadResponse save(MultipartFile file, String userId) {
        String imageId = imageRepository.save(file, userId);
        return new UploadResponse(imageId);
    }

    public LoadFile downloadFile(String id) {
        GridFSFile image = imageRepository.find(id)
                .orElseThrow(() -> {
                    log.error("could not find image with id {}", id);
                    return new NotFoundException("could not find image with id %s".formatted(id));
                });

        return transform(image);
    }

    private LoadFile transform(GridFSFile image) {
        String filename = image.getFilename();

        assert image.getMetadata() != null;
        String fileType = image.getMetadata().get("_contentType").toString();
        String fileSize = image.getMetadata().get("fileSize").toString();
        byte[] bytes;

        try {
            bytes = IOUtils.toByteArray(gridFsOperations.getResource(image).getInputStream());
        } catch (IOException e) {
            log.error("error while transforming the file to byte array: {}", e.getMessage());
            throw new FileDownloadException("error while transforming the file to byte array: %s".formatted(e.getMessage()));
        }

        return new LoadFile(filename, fileType, fileSize, bytes);
    }
}
