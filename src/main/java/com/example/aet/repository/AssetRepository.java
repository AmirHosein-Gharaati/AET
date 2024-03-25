package com.example.aet.repository;

import com.example.aet.model.asset.Asset;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AssetRepository extends MongoRepository<Asset, String> {

    @Query(fields = "{id: 1, name: 1, currency: 1, totalAmount: 1}")
    List<Asset> findByUserId(String userid);

    Optional<Asset> findByIdAndUserId(String id, String userId);
    boolean existsByIdAndUserId(String id, String userId);
}
