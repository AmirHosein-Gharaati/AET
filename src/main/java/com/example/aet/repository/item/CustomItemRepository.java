package com.example.aet.repository.item;

import com.example.aet.model.item.Item;

import java.util.List;

public interface CustomItemRepository {
    List<Item> findLatest(String assetId, String userId);
}
