package com.example.aet.repository;

import com.example.aet.model.asset.Asset;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AssetRepository extends MongoRepository<Asset, String> {
    List<Asset> findByUserId(String userId);
    boolean existsByIdAndUserId(String id, String userId);
}
