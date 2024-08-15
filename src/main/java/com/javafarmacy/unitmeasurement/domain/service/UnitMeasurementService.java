package com.javafarmacy.unitmeasurement.domain.service;

import java.util.Optional;

import com.javafarmacy.unitmeasurement.domain.entity.UnitMeasurement;


public interface UnitMeasurementService {
    void createUnitMeasurement (UnitMeasurement unitMeasurement);
    void updateUnitMeasurement (UnitMeasurement unitMeasurement);
    UnitMeasurement deleteUnitMeasurement (String idum);
    Optional<UnitMeasurement> findUnitMeasurementById(String idum);
}
