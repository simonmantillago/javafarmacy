package com.javafarmacy.region.application;

import java.util.Optional;

import com.javafarmacy.region.domain.entity.Region;
import com.javafarmacy.region.domain.service.RegionService;

public class FindRegionByIdUseCase {
    private final RegionService regionService;

    public FindRegionByIdUseCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public Optional<Region> execute(String codeRegion) {
        return regionService.findRegionById(codeRegion);
    }
}
