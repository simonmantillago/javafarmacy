package com.javafarmacy.farmacymedicine.domain.service;

import java.util.Optional;

import com.javafarmacy.farmacymedicine.domain.entity.FarmacyMedicine;


public interface FarmacyMedicineService {
    void createFarmacyMedicine (FarmacyMedicine farmacyMedicine);
    void updateFarmacyMedicine (FarmacyMedicine farmacyMedicine);
    FarmacyMedicine deleteFarmacyMedicine (String codeFarmacy,String codeMedicine);
    Optional<FarmacyMedicine> findFarmacyMedicineById(String codeFarmacy,String codeMedicine);
}
