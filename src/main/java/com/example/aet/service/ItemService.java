package com.example.aet.service;

import com.example.aet.exception.model.NotFoundException;
import com.example.aet.model.item.Item;
import com.example.aet.model.item.dto.ItemRequest;
import com.example.aet.repository.AssetRepository;
import com.example.aet.repository.ItemRepository;
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
        if (!assetRepository.existsByIdAndUserId(assetId, userId)) {
            log.error("asset with id {} for user {} not found", assetId, userId);
            throw new NotFoundException("asset with id %s not found for the user".formatted(assetId));
        }

        Item item = new Item(request, userId, assetId);
        return itemRepository.insert(item);
    }
}
