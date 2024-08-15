package com.javafarmacy.labatory.application;

import java.util.Optional;

import com.javafarmacy.labatory.domain.entity.Labatory;
import com.javafarmacy.labatory.domain.service.LabatoryService;

public class FindLabatoryByIdUseCase {
    private final LabatoryService labatoryService;

    public FindLabatoryByIdUseCase(LabatoryService labatoryService) {
        this.labatoryService = labatoryService;
    }

    public Optional<Labatory> execute(String codeLabatory) {
        return labatoryService.findLabatoryById(codeLabatory);
    }
}
