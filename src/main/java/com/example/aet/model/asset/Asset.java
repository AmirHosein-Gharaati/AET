package com.example.aet.model.asset;

import com.example.aet.model.Item;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document("asset")
public class Asset {
    @Id
    private String id;
    private String name;
    private List<Item> items;

    @CreatedDate
    private LocalDateTime createdAt;
}
