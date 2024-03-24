package com.example.aet.service;

import com.example.aet.exception.model.NotFoundException;
import com.example.aet.model.asset.Asset;
import com.example.aet.model.asset.dto.AssetRequest;
import com.example.aet.model.asset.dto.AssetUpdateRequest;
import com.example.aet.repository.AssetRepository;
import com.example.aet.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssetService {

    private final AssetRepository assetRepository;
    private final ItemRepository itemRepository;

    public Asset create(AssetRequest request, String userId) {
        Asset asset = new Asset(request, userId);
        return assetRepository.insert(asset);
    }

    public List<Asset> getAll(String userId) {
        return assetRepository.findByUserId(userId);
    }

    public void remove(String id, String userId) {
        if (!assetRepository.existsByIdAndUserId(id, userId)) {
            log.error("asset does not exist with id {} for user {}", id, userId);
            throw new NotFoundException("asset does not exist with id %s for the user".formatted(id));
        }

        itemRepository.deleteAllByAssetIdAndUserId(id, userId);
        assetRepository.deleteById(id);
    }

    public Asset update(AssetUpdateRequest request, String id, String userId) {
        Asset asset = assetRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> {
                    log.error("asset with id {} for user {} not found", id, userId);
                    return new NotFoundException("asset with id %s for the user not found".formatted(id));
                });

        asset.setName(request.name());

        return assetRepository.save(asset);
    }
}
