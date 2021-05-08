package app.controller;

import app.domain.model.*;
import app.domain.store.TestTypeStore;
import app.mappers.TestTypeMapper;
import app.mappers.dto.ClinicalAnalysisLaboratoryDTO;
import app.mappers.dto.TestTypeDTO;

import java.util.List;

public class RegisterNewCalController {
    private Company company;
    private ClinicalAnalysisLaboratory cal;

    public RegisterNewCalController() {
        this(App.getInstance().getCompany());
    }

    public RegisterNewCalController(Company company) {
        this.company = company;
        this.cal = null;
    }


    public boolean createClinicalAnalysisLaboratory(ClinicalAnalysisLaboratoryDTO calDto) {
        this.cal = this.company.createClinicalAnalysisLaboratory(calDto);
        return this.company.validateClinicalAnalysisLaboratory(cal);
    }


    public boolean saveClinicalAnalysisLaboratory(){
        return this.company.saveClinicalAnalysisLaboratory(cal);
    }

    public List<TestTypeDTO> getTestTypes() {
        TestTypeStore storeTest = this.company.getTestTypeStore();
        List<TestType> listTestType = storeTest.getTestTypes();

        TestTypeMapper mapper = new TestTypeMapper();
        return mapper.toDTO(listTestType);
    }



}
