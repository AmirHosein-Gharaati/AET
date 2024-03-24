package com.example.aet.model.asset.dto;

import jakarta.validation.constraints.NotEmpty;

public record AssetUpdateRequest(
        @NotEmpty(message = "name may not be empty")
        String name
) {
}
