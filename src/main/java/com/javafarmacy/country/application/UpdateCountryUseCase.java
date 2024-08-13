package com.javafarmacy.country.application;

import com.javafarmacy.country.domain.entity.Country;
import com.javafarmacy.country.domain.service.CountryService;

public class UpdateCountryUseCase {
 private final CountryService countryService;

    public UpdateCountryUseCase(CountryService countryService) {
        this.countryService = countryService;
    }

    public void execute(Country country){
        countryService.updateCountry(country);
    }
}
