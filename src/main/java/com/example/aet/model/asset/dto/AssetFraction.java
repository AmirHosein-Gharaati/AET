package com.example.aet.model.asset.dto;

import com.example.aet.model.asset.Asset;

public record AssetFraction(
        String id,
        String name,
        String currencyFrom,
        String currencyTo,
        Double totalAmount,
        Double totalCost
) {
    public AssetFraction(Asset asset) {
        this(
                asset.getId(),
                asset.getName(),
                asset.getCurrencyFrom(),
                asset.getCurrencyTo(),
                asset.getTotalAmount(),
                asset.getTotalCost()
        );
    }
}
