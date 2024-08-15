package com.javafarmacy.unitmeasurement.application;

import com.javafarmacy.unitmeasurement.domain.entity.UnitMeasurement;
import com.javafarmacy.unitmeasurement.domain.service.UnitMeasurementService;

public class DeleteUnitMeasurementUseCase {
    private final UnitMeasurementService unitMeasurementService;

    public DeleteUnitMeasurementUseCase(UnitMeasurementService unitMeasurementService) {
        this.unitMeasurementService = unitMeasurementService;
    }

    public UnitMeasurement execute(String codeUnitMeasurement) {
        return unitMeasurementService.deleteUnitMeasurement(codeUnitMeasurement);
    }
}
