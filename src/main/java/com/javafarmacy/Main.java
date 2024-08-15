package com.javafarmacy;

import com.javafarmacy.activeprinciple.application.CreateActiveprincipleUseCase;
import com.javafarmacy.activeprinciple.application.DeleteActiveprincipleUseCase;
import com.javafarmacy.activeprinciple.application.FindActiveprincipleByIdUseCase;
import com.javafarmacy.activeprinciple.application.UpdateActiveprincipleUseCase;
import com.javafarmacy.activeprinciple.domain.service.ActiveprincipleService;
import com.javafarmacy.activeprinciple.infrastructure.ActiveprincipleRepository;
import com.javafarmacy.activeprinciple.infrastructure.activePrincipleUi.ActiveprincipleUiController;
import com.javafarmacy.city.application.CreateCityUseCase;
import com.javafarmacy.city.application.DeleteCityUseCase;
import com.javafarmacy.city.application.FindCityByIdUseCase;
import com.javafarmacy.city.application.UpdateCityUseCase;
import com.javafarmacy.city.domain.service.CityService;
import com.javafarmacy.city.infrastructure.CityRepository;
import com.javafarmacy.city.infrastructure.cityUi.CityUiController;
import com.javafarmacy.country.application.CreateCountryUseCase;
import com.javafarmacy.country.application.DeleteCountryUseCase;
import com.javafarmacy.country.application.FindCountryByIdUseCase;
import com.javafarmacy.country.application.UpdateCountryUseCase;
import com.javafarmacy.country.infrastructure.CountryUi.CountryUiController;
import com.javafarmacy.customer.application.CreateCustomerUseCase;
import com.javafarmacy.customer.application.DeleteCustomerUseCase;
import com.javafarmacy.customer.application.FindAllCustomerUseCase;
import com.javafarmacy.customer.application.FindCustomerByIdUseCase;
import com.javafarmacy.customer.application.UpdateCustomerUseCase;
import com.javafarmacy.customer.domain.service.CustomerService;
import com.javafarmacy.customer.infrastructure.CustomerRepository;
import com.javafarmacy.customer.infrastructure.CustomerUi.CustomerCrudUi;
import com.javafarmacy.labatory.application.CreateLabatoryUseCase;
import com.javafarmacy.labatory.application.DeleteLabatoryUseCase;
import com.javafarmacy.labatory.application.FindLabatoryByIdUseCase;
import com.javafarmacy.labatory.application.UpdateLabatoryUseCase;
import com.javafarmacy.labatory.domain.service.LabatoryService;
import com.javafarmacy.labatory.infrastructure.LabatoryRepository;
import com.javafarmacy.labatory.infrastructure.labatoryUi.LabatoryUiController;
import com.javafarmacy.modeadministration.application.CreateModeAdministrationUseCase;
import com.javafarmacy.modeadministration.application.DeleteModeAdministrationUseCase;
import com.javafarmacy.modeadministration.application.FindModeAdministrationByIdUseCase;
import com.javafarmacy.modeadministration.application.UpdateModeAdministrationUseCase;
import com.javafarmacy.modeadministration.infrastructure.ModeAdministrationRepository;
import com.javafarmacy.modeadministration.infrastructure.modeAdministrationUi.ModeAdministrationUiController;
import com.javafarmacy.modeadministration.domain.service.ModeAdministrationService;
import com.javafarmacy.region.application.CreateRegionUseCase;
import com.javafarmacy.region.application.DeleteRegionUseCase;
import com.javafarmacy.region.application.FindRegionByIdUseCase;
import com.javafarmacy.region.application.UpdateRegionUseCase;
import com.javafarmacy.region.infrastructure.RegionRepository;
import com.javafarmacy.region.domain.service.RegionService;
import com.javafarmacy.region.infrastructure.regionUi.RegionUiController;
import com.javafarmacy.unitmeasurement.application.CreateUnitMeasurementUseCase;
import com.javafarmacy.unitmeasurement.application.DeleteUnitMeasurementUseCase;
import com.javafarmacy.unitmeasurement.application.FindUnitMeasurementByIdUseCase;
import com.javafarmacy.unitmeasurement.application.UpdateUnitMeasurementUseCase;
import com.javafarmacy.unitmeasurement.infrastructure.UnitMeasurementRepository;
import com.javafarmacy.unitmeasurement.infrastructure.unitMesurementUi.UnitMeasurementUiController;
import com.javafarmacy.unitmeasurement.domain.service.UnitMeasurementService;
import com.javafarmacy.country.domain.service.CountryService;
import com.javafarmacy.country.infrastructure.CountryRepository;


public class Main {
    public static void main(String[] args) {
        CountryService countryService = new CountryRepository();
        CreateCountryUseCase createCountryUseCase = new CreateCountryUseCase(countryService);
        UpdateCountryUseCase updateCountryUseCase = new UpdateCountryUseCase(countryService);
        FindCountryByIdUseCase findCountryByIdUseCase = new FindCountryByIdUseCase(countryService);
        DeleteCountryUseCase deleteCountryUseCase = new DeleteCountryUseCase(countryService);

        CountryUiController countryUiController = new CountryUiController(createCountryUseCase, findCountryByIdUseCase,updateCountryUseCase, deleteCountryUseCase);
        countryUiController.showCrudOptions();

        // CustomerService customerService = new CustomerRepository();
        // CreateCustomerUseCase createCustomerUseCase = new CreateCustomerUseCase(customerService);
        // UpdateCustomerUseCase updateCustomerUseCase = new UpdateCustomerUseCase(customerService);
        // DeleteCustomerUseCase DeleteCustomerUseCase = new DeleteCustomerUseCase(customerService);
        // FindCustomerByIdUseCase findCustomerByIdUseCase = new FindCustomerByIdUseCase(customerService);
        // FindAllCustomerUseCase findAllCustomerUseCase = new FindAllCustomerUseCase(customerService);

        // CustomerCrudUi customerCrudUi = new CustomerCrudUi(createCustomerUseCase, findCustomerByIdUseCase, updateCustomerUseCase, DeleteCustomerUseCase, findAllCustomerUseCase);
        // customerCrudUi.showCrudOptions();

        // RegionService regionService = new RegionRepository();
        // CreateRegionUseCase createRegionUseCase = new CreateRegionUseCase(regionService);
        // UpdateRegionUseCase updateRegionUseCase = new UpdateRegionUseCase(regionService);
        // FindRegionByIdUseCase findRegionByIdUseCase = new FindRegionByIdUseCase(regionService);
        // DeleteRegionUseCase deleteRegionUseCase = new DeleteRegionUseCase(regionService);

        // RegionUiController regionUiController = new RegionUiController(createRegionUseCase, findRegionByIdUseCase,updateRegionUseCase, deleteRegionUseCase);
        // regionUiController.showCrudOptions();

        // CityService cityService = new CityRepository();
        // CreateCityUseCase createCityUseCase = new CreateCityUseCase(cityService);
        // UpdateCityUseCase updateCityUseCase = new UpdateCityUseCase(cityService);
        // FindCityByIdUseCase findCityByIdUseCase = new FindCityByIdUseCase(cityService);
        // DeleteCityUseCase deleteCityUseCase = new DeleteCityUseCase(cityService);

        // CityUiController cityUiController = new CityUiController(createCityUseCase, findCityByIdUseCase,updateCityUseCase, deleteCityUseCase);
        // cityUiController.showCrudOptions();

        // ActiveprincipleService activeprincipleService = new ActiveprincipleRepository();
        // CreateActiveprincipleUseCase createActiveprincipleUseCase = new CreateActiveprincipleUseCase(activeprincipleService);
        // UpdateActiveprincipleUseCase updateActiveprincipleUseCase = new UpdateActiveprincipleUseCase(activeprincipleService);
        // FindActiveprincipleByIdUseCase findActiveprincipleByIdUseCase = new FindActiveprincipleByIdUseCase(activeprincipleService);
        // DeleteActiveprincipleUseCase deleteActiveprincipleUseCase = new DeleteActiveprincipleUseCase(activeprincipleService);

        // ActiveprincipleUiController activeprincipleUiController = new ActiveprincipleUiController(createActiveprincipleUseCase, findActiveprincipleByIdUseCase,updateActiveprincipleUseCase, deleteActiveprincipleUseCase);
        // activeprincipleUiController.showCrudOptions();

        // UnitMeasurementService unitMeasurementService = new UnitMeasurementRepository();
        // CreateUnitMeasurementUseCase createUnitMeasurementUseCase = new CreateUnitMeasurementUseCase(unitMeasurementService);
        // UpdateUnitMeasurementUseCase updateUnitMeasurementUseCase = new UpdateUnitMeasurementUseCase(unitMeasurementService);
        // FindUnitMeasurementByIdUseCase findUnitMeasurementByIdUseCase = new FindUnitMeasurementByIdUseCase(unitMeasurementService);
        // DeleteUnitMeasurementUseCase deleteUnitMeasurementUseCase = new DeleteUnitMeasurementUseCase(unitMeasurementService);

        // UnitMeasurementUiController unitMeasurementUiController = new UnitMeasurementUiController(createUnitMeasurementUseCase, findUnitMeasurementByIdUseCase,updateUnitMeasurementUseCase, deleteUnitMeasurementUseCase);
        // unitMeasurementUiController.showCrudOptions();

        // LabatoryService labatoryService = new LabatoryRepository();
        // CreateLabatoryUseCase createLabatoryUseCase = new CreateLabatoryUseCase(labatoryService);
        // UpdateLabatoryUseCase updateLabatoryUseCase = new UpdateLabatoryUseCase(labatoryService);
        // FindLabatoryByIdUseCase findLabatoryByIdUseCase = new FindLabatoryByIdUseCase(labatoryService);
        // DeleteLabatoryUseCase deleteLabatoryUseCase = new DeleteLabatoryUseCase(labatoryService);

        // LabatoryUiController labatoryUiController = new LabatoryUiController(createLabatoryUseCase, findLabatoryByIdUseCase,updateLabatoryUseCase, deleteLabatoryUseCase);
        // labatoryUiController.showCrudOptions();

        // ModeAdministrationService modeAdministrationService = new ModeAdministrationRepository();
        // CreateModeAdministrationUseCase createModeAdministrationUseCase = new CreateModeAdministrationUseCase(modeAdministrationService);
        // UpdateModeAdministrationUseCase updateModeAdministrationUseCase = new UpdateModeAdministrationUseCase(modeAdministrationService);
        // FindModeAdministrationByIdUseCase findModeAdministrationByIdUseCase = new FindModeAdministrationByIdUseCase(modeAdministrationService);
        // DeleteModeAdministrationUseCase deleteModeAdministrationUseCase = new DeleteModeAdministrationUseCase(modeAdministrationService);

        // ModeAdministrationUiController modeAdministrationUiController = new ModeAdministrationUiController(createModeAdministrationUseCase, findModeAdministrationByIdUseCase,updateModeAdministrationUseCase, deleteModeAdministrationUseCase);
        // modeAdministrationUiController.showCrudOptions();

        







    }
}