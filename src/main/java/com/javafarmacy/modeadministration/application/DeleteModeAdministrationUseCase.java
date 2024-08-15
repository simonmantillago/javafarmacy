package com.javafarmacy.modeadministration.application;

import com.javafarmacy.modeadministration.domain.entity.ModeAdministration;
import com.javafarmacy.modeadministration.domain.service.ModeAdministrationService;

public class DeleteModeAdministrationUseCase {
    private final ModeAdministrationService modeAdministrationService;

    public DeleteModeAdministrationUseCase(ModeAdministrationService modeAdministrationService) {
        this.modeAdministrationService = modeAdministrationService;
    }

    public ModeAdministration execute(String codeModeAdministration) {
        return modeAdministrationService.deleteModeAdministration(codeModeAdministration);
    }
}
