package com.javafarmacy.modeadministration.application;

import java.util.Optional;

import com.javafarmacy.modeadministration.domain.entity.ModeAdministration;
import com.javafarmacy.modeadministration.domain.service.ModeAdministrationService;

public class FindModeAdministrationByIdUseCase {
    private final ModeAdministrationService modeAdministrationService;

    public FindModeAdministrationByIdUseCase(ModeAdministrationService modeAdministrationService) {
        this.modeAdministrationService = modeAdministrationService;
    }

    public Optional<ModeAdministration> execute(String id) {
        return modeAdministrationService.findModeAdministrationById(id);
    }
}
