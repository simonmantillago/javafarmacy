package com.javafarmacy.modeadministration.application;

import com.javafarmacy.modeadministration.domain.entity.ModeAdministration;
import com.javafarmacy.modeadministration.domain.service.ModeAdministrationService;

public class CreateModeAdministrationUseCase {

    private final ModeAdministrationService modeAdministrationService;

    public CreateModeAdministrationUseCase(ModeAdministrationService modeAdministrationService) {
        this.modeAdministrationService = modeAdministrationService;
    }

    public void execute(ModeAdministration modeAdministration){
        modeAdministrationService.createModeAdministration(modeAdministration);
}
}
