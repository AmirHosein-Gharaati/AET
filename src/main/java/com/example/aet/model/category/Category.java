package com.example.aet.model.category;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document("category")
public class Category {
    @Id
    private String id;
    private String name;
    private Pair pair;
    private List<Item> items;
}
