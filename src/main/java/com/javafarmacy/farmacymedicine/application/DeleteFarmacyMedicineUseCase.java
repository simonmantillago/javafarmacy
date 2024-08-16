package com.javafarmacy.farmacymedicine.application;

import com.javafarmacy.farmacymedicine.domain.entity.FarmacyMedicine;
import com.javafarmacy.farmacymedicine.domain.service.FarmacyMedicineService;

public class DeleteFarmacyMedicineUseCase {
    private final FarmacyMedicineService farmacyMedicineService;

    public DeleteFarmacyMedicineUseCase(FarmacyMedicineService farmacyMedicineService) {
        this.farmacyMedicineService = farmacyMedicineService;
    }

    public FarmacyMedicine execute(String codeFarmacy , String codeMedicine) {
        return farmacyMedicineService.deleteFarmacyMedicine(codeFarmacy,codeMedicine);
    }
}
