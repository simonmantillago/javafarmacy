package com.javafarmacy.farmacy.application;

import com.javafarmacy.farmacy.domain.entity.Farmacy;
import com.javafarmacy.farmacy.domain.service.FarmacyService;

public class UpdateFarmacyUseCase {
    private final FarmacyService farmacyService;

    public UpdateFarmacyUseCase(FarmacyService farmacyService) {
        this.farmacyService = farmacyService;
    }

    public void execute(Farmacy farmacy){
        farmacyService.updateFarmacy(farmacy);
    }
}
