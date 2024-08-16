package com.javafarmacy.medicine.application;

import com.javafarmacy.medicine.domain.entity.Medicine;
import com.javafarmacy.medicine.domain.service.MedicineService;

public class CreateMedicineUseCase {
    private final MedicineService medicineService;

    public CreateMedicineUseCase(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    public void execute(Medicine medicine){
        medicineService.createMedicine(medicine);
    }
}
