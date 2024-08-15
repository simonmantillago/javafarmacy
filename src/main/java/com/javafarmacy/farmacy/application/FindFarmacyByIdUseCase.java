package com.javafarmacy.farmacy.application;

import java.util.Optional;

import com.javafarmacy.farmacy.domain.entity.Farmacy;
import com.javafarmacy.farmacy.domain.service.FarmacyService;

public class FindFarmacyByIdUseCase {
    private final FarmacyService farmacyService;

    public FindFarmacyByIdUseCase(FarmacyService farmacyService) {
        this.farmacyService = farmacyService;
    }

    public Optional<Farmacy> execute(String codeFarmacy) {
        return farmacyService.findFarmacyById(codeFarmacy);
    }
}
