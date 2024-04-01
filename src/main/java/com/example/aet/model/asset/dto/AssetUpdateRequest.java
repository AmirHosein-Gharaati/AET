package com.example.aet.model.asset.dto;

public record AssetUpdateRequest(
        String name,
        String imageId,
        Double currentPrice
) {
}
