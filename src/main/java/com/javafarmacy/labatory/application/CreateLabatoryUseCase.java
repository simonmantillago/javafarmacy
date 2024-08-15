package com.javafarmacy.labatory.application;

import com.javafarmacy.labatory.domain.entity.Labatory;
import com.javafarmacy.labatory.domain.service.LabatoryService;

public class CreateLabatoryUseCase {
    private final LabatoryService labatoryService;

    public CreateLabatoryUseCase(LabatoryService labatoryService) {
        this.labatoryService = labatoryService;
    }

    public void execute(Labatory labatory){
        labatoryService.createLabatory(labatory);
    }
}
