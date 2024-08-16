package com.javafarmacy.labatory.application;

import com.javafarmacy.labatory.domain.entity.Labatory;
import com.javafarmacy.labatory.domain.service.LabatoryService;

public class DeleteLabatoryUseCase {
    private final LabatoryService labatoryService;

    public DeleteLabatoryUseCase(LabatoryService labatoryService) {
        this.labatoryService = labatoryService;
    }

    public Labatory execute(String codelabatory){
        return labatoryService.deleteLabatory(codelabatory);
    }
}
