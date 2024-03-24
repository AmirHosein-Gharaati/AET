package com.example.aet.controller;

import com.example.aet.model.asset.Asset;
import com.example.aet.model.asset.dto.AssetRequest;
import com.example.aet.model.auth.AetPrincipal;
import com.example.aet.service.AssetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asset")
@Slf4j
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @PostMapping
    public Asset create(
            @AuthenticationPrincipal AetPrincipal principal,
            @RequestBody @Valid AssetRequest request
    ) {
        log.info("create asset");
        return assetService.create(request, principal.getId());
    }

    @GetMapping
    public List<Asset> getAll(@AuthenticationPrincipal AetPrincipal principal) {
        log.info("get all assets for user: {}", principal.getUsername());
        return assetService.getAll(principal.getId());
    }

    @DeleteMapping("/{id}")
    public void remove(
            @AuthenticationPrincipal AetPrincipal principal,
            @PathVariable("id") String id
    ) {
        log.info("remove asset {}", id);
        assetService.remove(id, principal.getId());
    }
}
