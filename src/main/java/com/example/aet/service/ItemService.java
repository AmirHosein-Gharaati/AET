package com.example.aet.service;

import com.example.aet.exception.model.NotFoundException;
import com.example.aet.model.asset.Asset;
import com.example.aet.model.item.Item;
import com.example.aet.model.item.dto.ItemRequest;
import com.example.aet.repository.AssetRepository;
import com.example.aet.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final AssetRepository assetRepository;

    public Item create(ItemRequest request, String userId, String assetId) {
        Asset asset = assetRepository.findByIdAndUserId(assetId, userId)
                .orElseThrow(() -> {
                    log.error("asset with id {} for user {} not found", assetId, userId);
                    return new NotFoundException("asset with id %s not found for the user".formatted(assetId));
                });

        Item item = itemRepository.insert(new Item(request, userId, assetId));

        addItemDataToAsset(item, asset);
        assetRepository.save(asset);

        return item;
    }

    public void delete(String id, String assetId) {
        itemRepository.deleteByIdAndAssetId(id, assetId);
    }

    private void addItemDataToAsset(Item item, Asset asset) {
         asset.setTotalAmount(asset.getTotalAmount() + item.getAmount());

         double cost = item.getAmount() * item.getValue();
         asset.setTotalCost(asset.getTotalCost() + cost);
    }
}
