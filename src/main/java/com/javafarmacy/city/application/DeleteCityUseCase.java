package com.javafarmacy.city.application;

import com.javafarmacy.city.domain.entity.City;
import com.javafarmacy.city.domain.service.CityService;

public class DeleteCityUseCase {
  private final CityService cityService;

    public  DeleteCityUseCase(CityService cityService) {
        this.cityService = cityService;
    }

    public City execute(String codeCity){
        return cityService.deleteCity(codeCity);
    }
}
