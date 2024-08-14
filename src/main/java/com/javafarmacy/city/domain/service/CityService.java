package com.javafarmacy.city.domain.service;

import java.util.Optional;

import com.javafarmacy.city.domain.entity.City;

public interface CityService {
    void createCity (City city);
    void updateCity (City city);
    City deleteCity (String codeCity);
    Optional<City> findCityById(String codeCity);
}
