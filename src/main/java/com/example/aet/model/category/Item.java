package com.example.aet.model.category;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
public class Item {
    private Double amount;
    private Double value;

    @CreatedDate
    private LocalDateTime createdAt;
}
