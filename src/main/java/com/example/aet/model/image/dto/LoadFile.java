package com.example.aet.model.image.dto;

public record LoadFile(
        String filename,
        String fileType,
        String fileSize,
        byte[] bytes
) {
}
