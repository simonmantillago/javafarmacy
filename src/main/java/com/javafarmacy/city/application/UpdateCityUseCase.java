package com.javafarmacy.city.application;

import com.javafarmacy.city.domain.entity.City;
import com.javafarmacy.city.domain.service.CityService;

public class UpdateCityUseCase {
    private final CityService cityService;

    public UpdateCityUseCase(CityService cityService) {
        this.cityService = cityService;
    }

    public void execute(City city){
        cityService.updateCity(city);
    }
}
