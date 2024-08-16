package com.javafarmacy.medicine.application;

import com.javafarmacy.medicine.domain.entity.Medicine;
import com.javafarmacy.medicine.domain.service.MedicineService;

public class DeleteMedicineUseCase {
    private final MedicineService medicineService;

    public DeleteMedicineUseCase(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    public Medicine execute(String codeMedicine){
        return medicineService.deleteMedicine(codeMedicine);
    }
}
