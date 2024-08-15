package com.javafarmacy.unitmeasurement.application;

import com.javafarmacy.unitmeasurement.domain.entity.UnitMeasurement;
import com.javafarmacy.unitmeasurement.domain.service.UnitMeasurementService;

public class CreateUnitMeasurementUseCase {

    private final UnitMeasurementService unitMeasurementService;

    public CreateUnitMeasurementUseCase(UnitMeasurementService unitMeasurementService) {
        this.unitMeasurementService = unitMeasurementService;
    }

    public void execute(UnitMeasurement unitMeasurement){
        unitMeasurementService.createUnitMeasurement(unitMeasurement);
}
}
