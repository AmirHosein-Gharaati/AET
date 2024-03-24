package com.example.aet.model.user.dto;

import jakarta.validation.constraints.NotEmpty;

public record SignUpRequest(
        @NotEmpty(message = "username may not be empty")
        String username,
        @NotEmpty(message = "password may not be empty")
        String password,
        @NotEmpty(message = "confirm password may not be empty")
        String confirmPassword
) {
}
