package com.example.aet.model.asset;

import com.example.aet.model.asset.dto.AssetRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private CurrencyPair pair;
    private double currentPrice;
    private double totalAmount;
    private double totalCost;
    private LocalDateTime createdAt;

    public Asset(AssetRequest request, String userId) {
        this.name = request.name();
        this.userId = userId;
        this.pair = new CurrencyPair(request.currencyFrom(), request.currencyTo());
        this.currentPrice = request.currentPrice();
        this.totalAmount = 0;
        this.totalCost = 0;
        this.createdAt = LocalDateTime.now();
    }
}
