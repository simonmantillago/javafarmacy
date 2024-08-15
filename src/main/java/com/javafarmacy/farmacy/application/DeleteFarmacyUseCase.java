package com.javafarmacy.farmacy.application;

import com.javafarmacy.farmacy.domain.entity.Farmacy;
import com.javafarmacy.farmacy.domain.service.FarmacyService;

public class DeleteFarmacyUseCase {
    private final FarmacyService farmacyService;

    public DeleteFarmacyUseCase(FarmacyService farmacyService) {
        this.farmacyService = farmacyService;
    }

    public Farmacy execute(String codefarmacy) {
        return farmacyService.deleteFarmacy(codefarmacy);
    }
}
