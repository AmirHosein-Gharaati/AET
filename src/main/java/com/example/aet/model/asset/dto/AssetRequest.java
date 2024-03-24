package com.example.aet.model.asset.dto;

import jakarta.validation.constraints.NotEmpty;

public record AssetRequest(
        @NotEmpty(message = "name may not be empty")
        String name,
        @NotEmpty(message = "currency may not be empty")
        String currency
) {
}
