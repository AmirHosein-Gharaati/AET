package com.example.aet.model.asset.dto;

import com.example.aet.model.asset.Asset;

public record AssetFraction(
        String id,
        String name,
        String currency,
        Double totalAmount
) {
    public AssetFraction(Asset asset) {
        this(
                asset.getId(),
                asset.getName(),
                asset.getCurrency(),
                asset.getTotalAmount()
        );
    }
}
