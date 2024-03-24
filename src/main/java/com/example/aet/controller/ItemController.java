package com.example.aet.controller;

import com.example.aet.model.auth.AetPrincipal;
import com.example.aet.model.item.Item;
import com.example.aet.model.item.dto.ItemRequest;
import com.example.aet.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assets/{assetId}/items")
@Slf4j
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public Item create(
            @AuthenticationPrincipal AetPrincipal principal,
            @RequestBody @Valid ItemRequest request,
            @PathVariable("assetId") String assetId
    ) {
        log.info("create item user: {}", principal.getUsername());
        return itemService.create(request, principal.getId(), assetId);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @AuthenticationPrincipal AetPrincipal principal,
            @PathVariable("id") String itemId,
            @PathVariable("assetId") String assetId
    ) {
        log.info("delete item user: {}", principal.getUsername());
        itemService.delete(itemId, assetId);
    }
}
