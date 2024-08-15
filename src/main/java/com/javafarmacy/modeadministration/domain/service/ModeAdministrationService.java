package com.javafarmacy.modeadministration.domain.service;

import java.util.Optional;

import com.javafarmacy.modeadministration.domain.entity.ModeAdministration;

public interface ModeAdministrationService {
    void createModeAdministration (ModeAdministration modeAdministration);
    void updateModeAdministration (ModeAdministration modeAdministration);
    ModeAdministration deleteModeAdministration (String id);
    Optional<ModeAdministration> findModeAdministrationById(String id);
}
