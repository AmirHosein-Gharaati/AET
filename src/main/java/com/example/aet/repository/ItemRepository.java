package com.example.aet.repository;

import com.example.aet.model.item.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {
    void deleteAllByAssetIdAndUserId(String assetId, String userId);
    void deleteByIdAndAssetId(String id, String assetId);
}
