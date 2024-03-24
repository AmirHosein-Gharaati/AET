package com.example.aet.model.item;

import com.example.aet.model.item.dto.ItemRequest;
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

    public Item(ItemRequest request, String userId, String assetId) {
        this.assetId = assetId;
        this.userId = userId;
        this.amount = request.amount();
        this.value = request.value();
        this.date = request.date();
    }
}
