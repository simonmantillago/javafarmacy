package com.javafarmacy.farmacy.domain.service;

import java.util.Optional;

import com.javafarmacy.farmacy.domain.entity.Farmacy;

public interface FarmacyService {
    void createFarmacy (Farmacy farmacy);
    void updateFarmacy (Farmacy farmacy);
    Farmacy deleteFarmacy (String codeFarmacy);
    Optional<Farmacy> findFarmacyById(String codeFarmacy);
}

