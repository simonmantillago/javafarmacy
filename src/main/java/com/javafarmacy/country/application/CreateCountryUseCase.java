package com.javafarmacy.country.application;

import com.javafarmacy.country.domain.entity.Country;
import com.javafarmacy.country.domain.service.CountryService;

public class CreateCountryUseCase {
    private final CountryService countryService;

    public CreateCountryUseCase(CountryService countryService) {
        this.countryService = countryService;
    }

    public void execute(Country country){
        countryService.createCountry(country);
    }

}
