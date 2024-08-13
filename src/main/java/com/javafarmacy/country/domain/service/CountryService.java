package com.javafarmacy.country.domain.service;

import java.util.Optional;

import com.javafarmacy.country.domain.entity.Country;

public interface CountryService {
    void createCountry (Country country);
    void updateCountry (Country country);
    Country deleteCountry (String codeCountry);
    Optional<Country> findCountryById(String codeCountry);
}
