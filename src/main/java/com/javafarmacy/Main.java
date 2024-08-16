package com.javafarmacy;

import com.javafarmacy.activeprinciple.application.CreateActiveprincipleUseCase;
import com.javafarmacy.activeprinciple.application.DeleteActiveprincipleUseCase;
import com.javafarmacy.activeprinciple.application.FindActiveprincipleByIdUseCase;
import com.javafarmacy.activeprinciple.application.UpdateActiveprincipleUseCase;
import com.javafarmacy.activeprinciple.domain.service.ActiveprincipleService;
import com.javafarmacy.activeprinciple.infrastructure.ActiveprincipleRepository;
import com.javafarmacy.activeprinciple.infrastructure.activePrincipleUi.ActiveprincipleUiController;
import com.javafarmacy.farmacymedicine.application.CreateFarmacyMedicineUseCase;
import com.javafarmacy.farmacymedicine.application.DeleteFarmacyMedicineUseCase;
import com.javafarmacy.farmacymedicine.application.FindFarmacyMedicineByIdUseCase;
import com.javafarmacy.farmacymedicine.application.UpdateFarmacyMedicineUseCase;
import com.javafarmacy.farmacymedicine.domain.service.FarmacyMedicineService;
import com.javafarmacy.farmacymedicine.infrastructure.FarmacyMedicineRepository;
import com.javafarmacy.farmacymedicine.infrastructure.farmacymedicineUi.FarmacyMedicineUiController;
import com.javafarmacy.country.application.CreateCountryUseCase;
import com.javafarmacy.country.application.DeleteCountryUseCase;
import com.javafarmacy.country.application.FindCountryByIdUseCase;
import com.javafarmacy.country.application.UpdateCountryUseCase;
import com.javafarmacy.country.infrastructure.CountryUi.CountryUiController;
import com.javafarmacy.country.domain.service.CountryService;
import com.javafarmacy.country.infrastructure.CountryRepository;
import com.javafarmacy.farmacy.application.CreateFarmacyUseCase;
import com.javafarmacy.farmacy.application.DeleteFarmacyUseCase;
import com.javafarmacy.farmacy.application.FindFarmacyByIdUseCase;
import com.javafarmacy.farmacy.application.UpdateFarmacyUseCase;
import com.javafarmacy.farmacy.domain.service.FarmacyService;
import com.javafarmacy.farmacy.infrastructure.FarmacyRepository;
import com.javafarmacy.farmacy.infrastructure.farmacyUi.FarmacyCrudUi;
import com.javafarmacy.labatory.application.CreateLabatoryUseCase;
import com.javafarmacy.labatory.application.DeleteLabatoryUseCase;
import com.javafarmacy.labatory.application.FindLabatoryByIdUseCase;
import com.javafarmacy.labatory.application.UpdateLabatoryUseCase;
import com.javafarmacy.labatory.domain.service.LabatoryService;
import com.javafarmacy.labatory.infrastructure.LabatoryRepository;
import com.javafarmacy.labatory.infrastructure.labatoryUi.LabatoryUiController;
import com.javafarmacy.medicine.application.CreateMedicineUseCase;
import com.javafarmacy.medicine.application.DeleteMedicineUseCase;
import com.javafarmacy.medicine.application.FindMedicineByIdUseCase;
import com.javafarmacy.medicine.application.UpdateMedicineUseCase;
import com.javafarmacy.medicine.domain.service.MedicineService;
import com.javafarmacy.medicine.infrastructure.MedicineRepository;
import com.javafarmacy.medicine.infrastructure.medicineUi.MedicineCrudUi;
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


public class Main {
    public static void main(String[] args) {
        CountryService countryService = new CountryRepository();
        CreateCountryUseCase createCountryUseCase = new CreateCountryUseCase(countryService);
        UpdateCountryUseCase updateCountryUseCase = new UpdateCountryUseCase(countryService);
        FindCountryByIdUseCase findCountryByIdUseCase = new FindCountryByIdUseCase(countryService);
        DeleteCountryUseCase deleteCountryUseCase = new DeleteCountryUseCase(countryService);

        CountryUiController countryUiController = new CountryUiController(createCountryUseCase, findCountryByIdUseCase,updateCountryUseCase, deleteCountryUseCase);
        countryUiController.showCrudOptions();

        FarmacyService farmacyService = new FarmacyRepository();
        CreateFarmacyUseCase createFarmacyUseCase = new CreateFarmacyUseCase(farmacyService);
        UpdateFarmacyUseCase updateFarmacyUseCase = new UpdateFarmacyUseCase(farmacyService);
        DeleteFarmacyUseCase DeleteFarmacyUseCase = new DeleteFarmacyUseCase(farmacyService);
        FindFarmacyByIdUseCase findFarmacyByIdUseCase = new FindFarmacyByIdUseCase(farmacyService);

        FarmacyCrudUi farmacyCrudUi = new FarmacyCrudUi(createFarmacyUseCase, findFarmacyByIdUseCase, updateFarmacyUseCase, DeleteFarmacyUseCase);
        farmacyCrudUi.showCrudOptions();

        RegionService regionService = new RegionRepository();
        CreateRegionUseCase createRegionUseCase = new CreateRegionUseCase(regionService);
        UpdateRegionUseCase updateRegionUseCase = new UpdateRegionUseCase(regionService);
        FindRegionByIdUseCase findRegionByIdUseCase = new FindRegionByIdUseCase(regionService);
        DeleteRegionUseCase deleteRegionUseCase = new DeleteRegionUseCase(regionService);

        RegionUiController regionUiController = new RegionUiController(createRegionUseCase, findRegionByIdUseCase,updateRegionUseCase, deleteRegionUseCase);
        regionUiController.showCrudOptions();

        FarmacyMedicineService farmacyMedicineService = new FarmacyMedicineRepository();
        CreateFarmacyMedicineUseCase createFarmacyMedicineUseCase = new CreateFarmacyMedicineUseCase(farmacyMedicineService);
        UpdateFarmacyMedicineUseCase updateFarmacyMedicineUseCase = new UpdateFarmacyMedicineUseCase(farmacyMedicineService);
        FindFarmacyMedicineByIdUseCase findFarmacyMedicineByIdUseCase = new FindFarmacyMedicineByIdUseCase(farmacyMedicineService);
        DeleteFarmacyMedicineUseCase deleteFarmacyMedicineUseCase = new DeleteFarmacyMedicineUseCase(farmacyMedicineService);

        FarmacyMedicineUiController farmacyMedicineCrudUi = new FarmacyMedicineUiController(createFarmacyMedicineUseCase, findFarmacyMedicineByIdUseCase,updateFarmacyMedicineUseCase, deleteFarmacyMedicineUseCase);
        farmacyMedicineCrudUi.showCrudOptions();

        ActiveprincipleService activeprincipleService = new ActiveprincipleRepository();
        CreateActiveprincipleUseCase createActiveprincipleUseCase = new CreateActiveprincipleUseCase(activeprincipleService);
        UpdateActiveprincipleUseCase updateActiveprincipleUseCase = new UpdateActiveprincipleUseCase(activeprincipleService);
        FindActiveprincipleByIdUseCase findActiveprincipleByIdUseCase = new FindActiveprincipleByIdUseCase(activeprincipleService);
        DeleteActiveprincipleUseCase deleteActiveprincipleUseCase = new DeleteActiveprincipleUseCase(activeprincipleService);

        ActiveprincipleUiController activeprincipleUiController = new ActiveprincipleUiController(createActiveprincipleUseCase, findActiveprincipleByIdUseCase,updateActiveprincipleUseCase, deleteActiveprincipleUseCase);
        activeprincipleUiController.showCrudOptions();

        UnitMeasurementService unitMeasurementService = new UnitMeasurementRepository();
        CreateUnitMeasurementUseCase createUnitMeasurementUseCase = new CreateUnitMeasurementUseCase(unitMeasurementService);
        UpdateUnitMeasurementUseCase updateUnitMeasurementUseCase = new UpdateUnitMeasurementUseCase(unitMeasurementService);
        FindUnitMeasurementByIdUseCase findUnitMeasurementByIdUseCase = new FindUnitMeasurementByIdUseCase(unitMeasurementService);
        DeleteUnitMeasurementUseCase deleteUnitMeasurementUseCase = new DeleteUnitMeasurementUseCase(unitMeasurementService);

        UnitMeasurementUiController unitMeasurementUiController = new UnitMeasurementUiController(createUnitMeasurementUseCase, findUnitMeasurementByIdUseCase,updateUnitMeasurementUseCase, deleteUnitMeasurementUseCase);
        unitMeasurementUiController.showCrudOptions();

        LabatoryService labatoryService = new LabatoryRepository();
        CreateLabatoryUseCase createLabatoryUseCase = new CreateLabatoryUseCase(labatoryService);
        UpdateLabatoryUseCase updateLabatoryUseCase = new UpdateLabatoryUseCase(labatoryService);
        FindLabatoryByIdUseCase findLabatoryByIdUseCase = new FindLabatoryByIdUseCase(labatoryService);
        DeleteLabatoryUseCase deleteLabatoryUseCase = new DeleteLabatoryUseCase(labatoryService);

        LabatoryUiController labatoryUiController = new LabatoryUiController(createLabatoryUseCase, findLabatoryByIdUseCase,updateLabatoryUseCase, deleteLabatoryUseCase);
        labatoryUiController.showCrudOptions();

        ModeAdministrationService modeAdministrationService = new ModeAdministrationRepository();
        CreateModeAdministrationUseCase createModeAdministrationUseCase = new CreateModeAdministrationUseCase(modeAdministrationService);
        UpdateModeAdministrationUseCase updateModeAdministrationUseCase = new UpdateModeAdministrationUseCase(modeAdministrationService);
        FindModeAdministrationByIdUseCase findModeAdministrationByIdUseCase = new FindModeAdministrationByIdUseCase(modeAdministrationService);
        DeleteModeAdministrationUseCase deleteModeAdministrationUseCase = new DeleteModeAdministrationUseCase(modeAdministrationService);

        ModeAdministrationUiController modeAdministrationUiController = new ModeAdministrationUiController(createModeAdministrationUseCase, findModeAdministrationByIdUseCase,updateModeAdministrationUseCase, deleteModeAdministrationUseCase);
        modeAdministrationUiController.showCrudOptions();

        MedicineService medicineService = new MedicineRepository();
        CreateMedicineUseCase createMedicineUseCase = new CreateMedicineUseCase(medicineService);
        UpdateMedicineUseCase updateMedicineUseCase = new UpdateMedicineUseCase(medicineService);
        FindMedicineByIdUseCase findMedicineByIdUseCase = new FindMedicineByIdUseCase(medicineService);
        DeleteMedicineUseCase deleteMedicineUseCase = new DeleteMedicineUseCase(medicineService);

        MedicineCrudUi medicineCrudUi = new MedicineCrudUi(createMedicineUseCase, findMedicineByIdUseCase,updateMedicineUseCase, deleteMedicineUseCase);
        medicineCrudUi.showCrudOptions();

    }
}