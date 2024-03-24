package com.example.aet.repository;

import com.example.aet.model.asset.Asset;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AssetRepository extends MongoRepository<Asset, String> {
    List<Asset> findByUserId(String userId);
    Optional<Asset> findByIdAndUserId(String id, String userId);
    boolean existsByIdAndUserId(String id, String userId);
}
