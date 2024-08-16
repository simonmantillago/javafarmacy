package com.javafarmacy.farmacymedicine.application;

import java.util.Optional;

import com.javafarmacy.farmacymedicine.domain.entity.FarmacyMedicine;
import com.javafarmacy.farmacymedicine.domain.service.FarmacyMedicineService;

public class FindFarmacyMedicineByIdUseCase {
    private final FarmacyMedicineService farmacyMedicineService;

    public FindFarmacyMedicineByIdUseCase(FarmacyMedicineService farmacyMedicineService) {
        this.farmacyMedicineService = farmacyMedicineService;
    }

    public Optional<FarmacyMedicine> execute(String codeFarmacy , String codeMedicine) {
        return farmacyMedicineService.findFarmacyMedicineById(codeFarmacy,codeMedicine);
    }
}
