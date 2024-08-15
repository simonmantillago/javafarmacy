package com.javafarmacy.modeadministration.application;

import com.javafarmacy.modeadministration.domain.entity.ModeAdministration;
import com.javafarmacy.modeadministration.domain.service.ModeAdministrationService;

public class UpdateModeAdministrationUseCase {
    private final ModeAdministrationService modeAdministrationService;

    public UpdateModeAdministrationUseCase(ModeAdministrationService modeAdministrationService) {
        this.modeAdministrationService = modeAdministrationService;
    }

    public void execute(ModeAdministration modeAdministration){
        modeAdministrationService.updateModeAdministration(modeAdministration);
    }
}
