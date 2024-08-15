package com.javafarmacy.labatory.domain.service;

import java.util.Optional;

import com.javafarmacy.labatory.domain.entity.Labatory;

public interface LabatoryService {
    void createLabatory (Labatory labatory);
    void updateLabatory(Labatory labatory);
    Labatory deleteLabatory (String codelabatory);
    Optional<Labatory> findLabatoryById(String codelabatory);
}
