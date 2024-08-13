package com.javafarmacy.country.application;

import java.util.Optional;

import com.javafarmacy.country.domain.entity.Country;
import com.javafarmacy.country.domain.service.CountryService;

public class FindCountryByIdUseCase {
    private final CountryService countryService;

    public FindCountryByIdUseCase(CountryService countryService) {
        this.countryService = countryService;
    }

    public Optional<Country> execute(String id) {
        return countryService.findCountryById(id);
    }
}
