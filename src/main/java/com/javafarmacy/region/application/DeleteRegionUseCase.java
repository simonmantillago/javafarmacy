package com.javafarmacy.region.application;

import com.javafarmacy.region.domain.entity.Region;
import com.javafarmacy.region.domain.service.RegionService;

public class DeleteRegionUseCase {
    private final RegionService regionService;

    public DeleteRegionUseCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public Region execute(String codereg){
        return regionService.deleteRegion(codereg);
    }
}
