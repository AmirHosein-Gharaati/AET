package com.example.aet.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document("items")
public class Item {
    @Id
    private String id;
    private String assetId;
    private String userId;
    private long amount;
    private long value;
    private LocalDateTime date;
}
