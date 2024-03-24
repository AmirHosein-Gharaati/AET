package com.example.aet.model.user.dto;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        @NotEmpty(message = "username may not be empty")
        String username,
        @NotEmpty(message = "password may not be empty")
        String password
) {
}
