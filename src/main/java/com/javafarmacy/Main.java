package com.javafarmacy;

import com.javafarmacy.country.application.CreateCountryUseCase;
import com.javafarmacy.country.application.DeleteCountryUseCase;
import com.javafarmacy.country.application.FindCountryByIdUseCase;
import com.javafarmacy.country.application.UpdateCountryUseCase;
import com.javafarmacy.country.domain.service.CountryService;
import com.javafarmacy.country.infrastructure.CountryRepository;
import com.javafarmacy.country.infrastructure.CountryUi.CountryUiController;


public class Main {
    public static void main(String[] args) {
        CountryService countryService = new CountryRepository();
        CreateCountryUseCase createCountryUseCase = new CreateCountryUseCase(countryService);
        UpdateCountryUseCase updateCountryUseCase = new UpdateCountryUseCase(countryService);
        FindCountryByIdUseCase findCountryByIdUseCase = new FindCountryByIdUseCase(countryService);
        DeleteCountryUseCase deleteCountryUseCase = new DeleteCountryUseCase(countryService);

        CountryUiController countryUiController = new CountryUiController(createCountryUseCase, findCountryByIdUseCase,updateCountryUseCase, deleteCountryUseCase);
        countryUiController.showCrudOptions();
    }
}