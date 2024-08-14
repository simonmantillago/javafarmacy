package com.javafarmacy.region.application;

import com.javafarmacy.region.domain.entity.Region;
import com.javafarmacy.region.domain.service.RegionService;

public class UpdateRegionUseCase {
    private final RegionService regionService;

    public UpdateRegionUseCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public void execute(Region region){
        regionService.updateRegion(region);
    }
}
