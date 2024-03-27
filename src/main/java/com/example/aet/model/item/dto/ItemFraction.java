package com.example.aet.model.item.dto;

import com.example.aet.model.item.Item;

import java.time.LocalDateTime;

public record ItemFraction(
        String id,
        Double amount,
        Double value,
        LocalDateTime date
) {
    public ItemFraction(Item item) {
        this(
                item.getId(),
                item.getAmount(),
                item.getValue(),
                item.getDate()
        );
    }
}
