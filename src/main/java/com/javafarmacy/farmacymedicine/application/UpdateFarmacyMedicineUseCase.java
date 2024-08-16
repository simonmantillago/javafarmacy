package com.javafarmacy.farmacymedicine.application;

import com.javafarmacy.farmacymedicine.domain.entity.FarmacyMedicine;
import com.javafarmacy.farmacymedicine.domain.service.FarmacyMedicineService;

public class UpdateFarmacyMedicineUseCase {
 private final FarmacyMedicineService farmacyMedicineService;

    public UpdateFarmacyMedicineUseCase(FarmacyMedicineService farmacyMedicineService) {
        this.farmacyMedicineService = farmacyMedicineService;
    }

    public void execute(FarmacyMedicine farmacyMedicine){
        farmacyMedicineService.updateFarmacyMedicine(farmacyMedicine);
    }
}
