package com.example.aet.model.asset.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AssetRequest(
        @NotEmpty(message = "name may not be empty")
        String name,
        @NotEmpty(message = "currencyFrom may not be empty")
        String currencyFrom,
        @NotEmpty(message = "currencyTo may not be empty")
        String currencyTo,
        @NotNull(message = "currentPrice may not be null")
        Double currentPrice
) {
}
