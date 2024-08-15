package com.javafarmacy.unitmeasurement.application;

import com.javafarmacy.unitmeasurement.domain.entity.UnitMeasurement;
import com.javafarmacy.unitmeasurement.domain.service.UnitMeasurementService;

public class UpdateUnitMeasurementUseCase {
    private final UnitMeasurementService unitMeasurementService;

    public UpdateUnitMeasurementUseCase(UnitMeasurementService unitMeasurementService) {
        this.unitMeasurementService = unitMeasurementService;
    }

    public void execute(UnitMeasurement unitMeasurement){
        unitMeasurementService.updateUnitMeasurement(unitMeasurement);
    }
}
