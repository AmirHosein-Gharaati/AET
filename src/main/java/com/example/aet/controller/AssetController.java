package com.example.aet.controller;

import com.example.aet.model.asset.Asset;
import com.example.aet.model.asset.dto.AssetRequest;
import com.example.aet.service.AssetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asset")
@Slf4j
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @PostMapping
    public Asset create(@RequestBody @Valid AssetRequest request) {
        log.info("create asset");
        return assetService.create(request);
    }

    // FIXME: this should be refactored to use JWT token
    @GetMapping
    public List<Asset> getAll() {
        log.info("get all assets");
        return assetService.getAll();
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") String id) {
        log.info("remove asset {}", id);
        assetService.remove(id);
    }
}
