package com.example.aet.model.asset.dto;

import jakarta.validation.constraints.NotEmpty;

public record AssetRequest(
        @NotEmpty(message = "name may not be empty")
        String name,
        @NotEmpty(message = "currencyFrom may not be empty")
        String currencyFrom,
        @NotEmpty(message = "currencyTo may not be empty")
        String currencyTo
) {
}
