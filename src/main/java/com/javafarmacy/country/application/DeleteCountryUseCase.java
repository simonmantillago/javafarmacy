package com.javafarmacy.country.application;

import com.javafarmacy.country.domain.entity.Country;
import com.javafarmacy.country.domain.service.CountryService;

public class DeleteCountryUseCase {
    private final CountryService countryService;

    public DeleteCountryUseCase(CountryService countryService) {
        this.countryService = countryService;
    }

    public Country execute(String codeCountry) {
        return countryService.deleteCountry(codeCountry);
    }
}
