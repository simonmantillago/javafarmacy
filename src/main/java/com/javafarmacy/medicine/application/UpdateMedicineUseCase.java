package com.javafarmacy.medicine.application;

import com.javafarmacy.medicine.domain.entity.Medicine;
import com.javafarmacy.medicine.domain.service.MedicineService;

public class UpdateMedicineUseCase {
    private final MedicineService medicineService;

    public UpdateMedicineUseCase(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    public void execute(Medicine medicine){
        medicineService.updateMedicine(medicine);
    }}
