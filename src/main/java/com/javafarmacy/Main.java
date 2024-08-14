package com.javafarmacy;

import com.javafarmacy.country.application.CreateCountryUseCase;
import com.javafarmacy.country.application.DeleteCountryUseCase;
import com.javafarmacy.country.application.FindCountryByIdUseCase;
import com.javafarmacy.country.application.UpdateCountryUseCase;
import com.javafarmacy.country.domain.service.CountryService;
import com.javafarmacy.country.infrastructure.CountryRepository;
import com.javafarmacy.country.infrastructure.CountryUi.CountryUiController;
import com.javafarmacy.customer.application.CreateCustomerUseCase;
import com.javafarmacy.customer.application.DeleteCustomerUseCase;
import com.javafarmacy.customer.application.FindAllCustomerUseCase;
import com.javafarmacy.customer.application.FindCustomerByIdUseCase;
import com.javafarmacy.customer.application.UpdateCustomerUseCase;
import com.javafarmacy.customer.domain.service.CustomerService;
import com.javafarmacy.customer.infrastructure.CustomerRepository;
import com.javafarmacy.customer.infrastructure.CustomerUi.CustomerCrudUi;


public class Main {
    public static void main(String[] args) {
        // CountryService countryService = new CountryRepository();
        // CreateCountryUseCase createCountryUseCase = new CreateCountryUseCase(countryService);
        // UpdateCountryUseCase updateCountryUseCase = new UpdateCountryUseCase(countryService);
        // FindCountryByIdUseCase findCountryByIdUseCase = new FindCountryByIdUseCase(countryService);
        // DeleteCountryUseCase deleteCountryUseCase = new DeleteCountryUseCase(countryService);

        // CountryUiController countryUiController = new CountryUiController(createCountryUseCase, findCountryByIdUseCase,updateCountryUseCase, deleteCountryUseCase);
        // countryUiController.showCrudOptions();

        CustomerService customerService = new CustomerRepository();
        CreateCustomerUseCase createCustomerUseCase = new CreateCustomerUseCase(customerService);
        UpdateCustomerUseCase updateCustomerUseCase = new UpdateCustomerUseCase(customerService);
        DeleteCustomerUseCase DeleteCustomerUseCase = new DeleteCustomerUseCase(customerService);
        FindCustomerByIdUseCase findCustomerByIdUseCase = new FindCustomerByIdUseCase(customerService);
        FindAllCustomerUseCase findAllCustomerUseCase = new FindAllCustomerUseCase(customerService);

        CustomerCrudUi customerCrudUi = new CustomerCrudUi(createCustomerUseCase, findCustomerByIdUseCase, updateCustomerUseCase, DeleteCustomerUseCase, findAllCustomerUseCase);
        customerCrudUi.showCrudOptions();

    }
}