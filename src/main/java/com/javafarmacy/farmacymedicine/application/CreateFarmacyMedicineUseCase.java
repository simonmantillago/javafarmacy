package com.javafarmacy.farmacymedicine.application;

import com.javafarmacy.farmacymedicine.domain.entity.FarmacyMedicine;
import com.javafarmacy.farmacymedicine.domain.service.FarmacyMedicineService;

public class CreateFarmacyMedicineUseCase {
    private final FarmacyMedicineService farmacyMedicineService;

    public CreateFarmacyMedicineUseCase(FarmacyMedicineService farmacyMedicineService) {
        this.farmacyMedicineService = farmacyMedicineService;
    }

    public void execute(FarmacyMedicine farmacyMedicine){
        farmacyMedicineService.createFarmacyMedicine(farmacyMedicine);
    }

}
