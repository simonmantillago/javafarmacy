package com.javafarmacy.medicine.application;

import java.util.Optional;

import com.javafarmacy.medicine.domain.entity.Medicine;
import com.javafarmacy.medicine.domain.service.MedicineService;

public class FindMedicineByIdUseCase {
    private final MedicineService medicineService;

    public FindMedicineByIdUseCase(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    public Optional<Medicine> execute(String codeMedicine) {
        return medicineService.findMedicineById(codeMedicine);
    }
}
