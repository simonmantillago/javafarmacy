package com.javafarmacy.region.domain.service;

import java.util.Optional;

import com.javafarmacy.region.domain.entity.Region;

public interface RegionService {
    void createRegion (Region region);
    void updateRegion (Region region);
    Region deleteRegion (String codeRegion);
    Optional<Region> findRegionById(String codeRegion);
}
