package com.example.aet.model.asset.dto;

import com.example.aet.model.asset.Asset;

public record AssetFraction(
        String id,
        String name,
        String imageId,
        String currencyFrom,
        String currencyTo,
        Double currentPrice,
        Double totalAmount,
        Double totalCost
) {
    public AssetFraction(Asset asset) {
        this(
                asset.getId(),
                asset.getName(),
                asset.getImageId(),
                asset.getPair().from(),
                asset.getPair().to(),
                asset.getCurrentPrice(),
                asset.getTotalAmount(),
                asset.getTotalCost()
        );
    }
}
