package com.javafarmacy.labatory.application;

import com.javafarmacy.labatory.domain.entity.Labatory;
import com.javafarmacy.labatory.domain.service.LabatoryService;

public class UpdateLabatoryUseCase {
    private final LabatoryService labatoryService;

    public UpdateLabatoryUseCase(LabatoryService labatoryService) {
        this.labatoryService = labatoryService;
    }

    public void execute(Labatory labatory){
        labatoryService.updateLabatory(labatory);
    }
}
