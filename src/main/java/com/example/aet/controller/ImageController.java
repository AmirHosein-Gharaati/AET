package com.example.aet.controller;

import com.example.aet.model.auth.AetPrincipal;
import com.example.aet.model.image.dto.LoadFile;
import com.example.aet.model.image.dto.UploadResponse;
import com.example.aet.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/images")
@RequiredArgsConstructor
@Slf4j
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<UploadResponse> upload(
            @AuthenticationPrincipal AetPrincipal principal,
            @RequestParam("file") MultipartFile file
    ) {
        log.info("upload file for user {}", principal.getId());

        UploadResponse imageResponse = imageService.save(file, principal.getId());

        return new ResponseEntity<>(imageResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> download(
            @AuthenticationPrincipal AetPrincipal principal,
            @PathVariable("id") String id
    ) {
        log.info("download image {} for user {}", id, principal.getId());
        LoadFile loadFile = imageService.downloadFile(id, principal.getId());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(loadFile.fileType()))
                .contentLength(loadFile.bytes().length)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"%s\"".formatted(loadFile.filename()))
                .body(loadFile.bytes());
    }
}
