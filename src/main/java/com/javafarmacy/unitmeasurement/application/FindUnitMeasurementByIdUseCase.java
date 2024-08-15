package com.javafarmacy.unitmeasurement.application;

import java.util.Optional;

import com.javafarmacy.unitmeasurement.domain.entity.UnitMeasurement;
import com.javafarmacy.unitmeasurement.domain.service.UnitMeasurementService;

public class FindUnitMeasurementByIdUseCase {
    private final UnitMeasurementService unitMeasurementService;

    public FindUnitMeasurementByIdUseCase(UnitMeasurementService unitMeasurementService) {
        this.unitMeasurementService = unitMeasurementService;
    }

    public Optional<UnitMeasurement> execute(String id) {
        return unitMeasurementService.findUnitMeasurementById(id);
    }
}
