package app.controller;

import app.domain.model.*;
import app.domain.store.TestTypeStore;
import app.mappers.dto.ClinicalAnalysisLaboratoryDTO;
import app.mappers.dto.TestTypeDTO;

import java.util.List;

public class RegisterNewCalController {
    private Company company;
    private TestType tt;
    private Laboratory lab;
    private ClinicalAnalysisLaboratory cal;

    public RegisterNewCalController() {
        this(App.getInstance().getCompany());
    }

    public RegisterNewCalController(Company company) {
        this.company = company;
        this.tt = null;
        this.lab = null;
        this.cal = null;
    }

    public boolean createClinicalAnalysisLaboratory(String laboratoryID,
                                                    String name,
                                                    String address,
                                                    String phoneNumber,
                                                    String numTIN,
                                                    List<String> testTypeCodes){


        TestTypeStore storeTest = this.company.getTestTypeStore();
        List<TestType> selectedTT = storeTest.getTestTypesByCode(testTypeCodes);



        this.cal = this.company.createClinicalAnalysisLaboratory(laboratoryID, name, address, phoneNumber, numTIN, selectedTT);
        return this.company.validateClinicalAnalysisLaboratory(cal);
    }


    /*
    public createClinicalAnalysisLaboratory(ClinicalAnalysisLaboratoryDTO calDto) {

    }
     */

    public boolean saveClinicalAnalysisLaboratory(){
        return this.company.saveClinicalAnalysisLaboratory(cal);
    }

    public List<TestTypeDTO> getTestTypes() {
        TestTypeStore storeTest = this.company.getTestTypeStore();
        return storeTest.getTestTypesDto();
    }



}
