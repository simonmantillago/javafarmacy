package com.javafarmacy.farmacy.application;

import com.javafarmacy.farmacy.domain.entity.Farmacy;
import com.javafarmacy.farmacy.domain.service.FarmacyService;

public class CreateFarmacyUseCase {
    private final FarmacyService farmacyService;

    public CreateFarmacyUseCase(FarmacyService farmacyService) {
        this.farmacyService = farmacyService;
    }

    public void execute(Farmacy farmacy) {
        farmacyService.createFarmacy(farmacy);
    }
}
