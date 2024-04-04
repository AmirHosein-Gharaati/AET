package com.example.aet.model.asset.dto;

import com.example.aet.model.asset.Asset;
import com.example.aet.model.item.Item;
import com.example.aet.model.item.dto.ItemFraction;

import java.util.List;

public record AssetSummary(
        String id,
        String name,
        String imageId,
        Double currentPrice,
        Double totalCost,
        Double totalAmount,
        String currencyFrom,
        String currencyTo,
        List<ItemFraction> items
) {
    public AssetSummary(Asset asset, List<Item> items) {
        this(
                asset.getId(),
                asset.getName(),
                asset.getImageId(),
                asset.getCurrentPrice(),
                asset.getTotalCost(),
                asset.getTotalAmount(),
                asset.getPair().from(),
                asset.getPair().to(),
                items.stream().map(ItemFraction::new).toList()
        );
    }
}
