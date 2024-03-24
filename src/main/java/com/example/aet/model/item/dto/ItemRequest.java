package com.example.aet.model.item.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ItemRequest(
        @NotNull(message = "amount may not be null")
        Double amount,
        @NotNull(message = "value may not be null")
        @Min(value = 0, message = "value should be greater than 0")
        Double value,
        LocalDateTime date
) {
}
