package app.controller;

import app.domain.model.*;

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

        //LaboratoryStore storeLab = this.lab.getLaboratoryStore()
        // cal = create
        //- codeWithMe Plugin

        List<TestType> selectedTT = this.company.getTestTypeStore().getTestTypesByCode(testTypeCodes);

        this.cal = this.lab.createClinicalAnalysisLaboratory(laboratoryID, name, address, phoneNumber, numTIN, selectedTT);
        return this.lab.validateClinicalAnalysisLaboratory(cal);
    }

    public boolean saveClinicalAnalysisLaboratory(){
        return this.lab.saveClinicalAnalysisLaboratory(cal);
    }

    public List<TestType> getTestTypes() {
        List<TestType> totalTT = this.company.getTestTypeStore().getTestTypes();
        return totalTT;
    }



}
