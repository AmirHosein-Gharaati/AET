package com.example.aet.service;

import com.example.aet.exception.model.NotFoundException;
import com.example.aet.model.asset.Asset;
import com.example.aet.model.asset.dto.AssetFraction;
import com.example.aet.model.asset.dto.AssetRequest;
import com.example.aet.model.asset.dto.AssetSummary;
import com.example.aet.model.asset.dto.AssetUpdateRequest;
import com.example.aet.model.item.Item;
import com.example.aet.repository.AssetRepository;
import com.example.aet.repository.item.ItemRepository;
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

    public List<AssetFraction> getAll(String userId) {
        return assetRepository.findByUserId(userId).stream()
                .map(AssetFraction::new).toList();
    }

    public Asset get(String id, String userId) {
        return assetRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> {
                    log.error("asset does not exist with id {} for user {}", id, userId);
                    return new NotFoundException("asset does not exist with id %s for the user".formatted(id));
                });
    }

    public AssetSummary getSummary(String id, String userId) {
        Asset asset = get(id, userId);
        List<Item> latestItems = itemRepository.findLatest(id, userId);
        return new AssetSummary(asset, latestItems);
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
        Asset asset = get(id, userId);

        updateAsset(request, asset);

        return assetRepository.save(asset);
    }

    private void updateAsset(AssetUpdateRequest request, Asset asset) {
        if (request.name() != null) asset.setName(request.name());
        if (request.currentPrice() != null) asset.setCurrentPrice(request.currentPrice());
    }
}
