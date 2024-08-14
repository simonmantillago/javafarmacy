package com.javafarmacy.city.application;

import com.javafarmacy.city.domain.entity.City;
import com.javafarmacy.city.domain.service.CityService;

public class CreateCityUseCase {
    private final CityService cityService;

    public CreateCityUseCase(CityService cityService) {
        this.cityService = cityService;
    }

    public void execute(City city){
        cityService.createCity(city);
    }
}
