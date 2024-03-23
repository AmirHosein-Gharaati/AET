package com.example.aet.model.expense;

import com.example.aet.model.CurrencyPair;
import com.example.aet.model.Item;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Document("expense")
public class Expense {
    @Id
    private String id;
    private String name;
    private CurrencyPair pair;
    private List<Item> items;

    @CreatedDate
    private LocalDateTime createdAt;
}
