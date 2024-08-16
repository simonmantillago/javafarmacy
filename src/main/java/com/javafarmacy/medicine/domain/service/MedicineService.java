package com.javafarmacy.medicine.domain.service;

import java.util.Optional;

import com.javafarmacy.medicine.domain.entity.Medicine;

public interface MedicineService {
    void createMedicine (Medicine medicine);
    void updateMedicine(Medicine medicine);
    Medicine deleteMedicine (String codeMedicine);
    Optional<Medicine> findMedicineById(String codeMedicine);
}
