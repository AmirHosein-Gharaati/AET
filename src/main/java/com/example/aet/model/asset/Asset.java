package com.example.aet.model.asset;

import com.example.aet.model.asset.dto.AssetRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document("assets")
@NoArgsConstructor
public class Asset {
    @Id
    private String id;
    private String userId;
    private String name;
    private String currency;
    @CreatedDate
    private LocalDateTime createdAt;

    public Asset(AssetRequest request) {
        this.name = request.name();
        this.currency = request.currency();
    }
}
