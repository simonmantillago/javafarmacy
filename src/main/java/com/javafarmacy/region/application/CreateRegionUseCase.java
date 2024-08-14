package com.javafarmacy.region.application;


import com.javafarmacy.region.domain.entity.Region;
import com.javafarmacy.region.domain.service.RegionService;

public class CreateRegionUseCase {
    private final RegionService regionService;

    public CreateRegionUseCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public void execute(Region region){
        regionService.createRegion(region);
    }
}
