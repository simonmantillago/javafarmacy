package com.javafarmacy.city.application;

import java.util.Optional;

import com.javafarmacy.city.domain.entity.City;
import com.javafarmacy.city.domain.service.CityService;

public class FindCityByIdUseCase {
    private final CityService cityService;

    public  FindCityByIdUseCase(CityService cityService) {
        this.cityService = cityService;
    }

    public Optional<City> execute(String codeCity){
        return cityService.findCityById(codeCity);
    }
}
    