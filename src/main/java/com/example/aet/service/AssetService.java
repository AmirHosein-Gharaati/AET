package com.example.aet.service;

import com.example.aet.model.asset.Asset;
import com.example.aet.model.asset.dto.AssetRequest;
import com.example.aet.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetService {

    private final AssetRepository assetRepository;

    public Asset create(AssetRequest request) {
        Asset asset = new Asset(request);
        return assetRepository.insert(asset);
    }

    public List<Asset> getAll() {
        return assetRepository.findAll();
    }

    public void remove(String id) {
        assetRepository.deleteById(id);
    }
}
