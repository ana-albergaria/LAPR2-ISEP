package app.controller;

import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.Company;
import app.domain.model.Laboratory;
import app.domain.model.TestType;
import auth.UserSession;

import java.util.List;

public class RegisterNewCalController {
    private Company company;
    private TestType tt;
    private Laboratory lab;
    private ClinicalAnalysisLaboratory cal;
    /*
    private App app;
    private UserSession userSession;
     */

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
                                                    int phoneNumber,
                                                    int numTIN,
                                                    List<String> TestTypeDesignations){
        this.cal = this.lab.createClinicalAnalysisLaboratory(laboratoryID, address, phoneNumber, numTIN, TestTypeDesignations);
        return this.lab.validateClinicalAnalysisLaboratory(cal);
    }

    public boolean saveClinicalAnalysisLaboratory(){
        return this.lab.saveClinicalAnalysisLaboratory(cal);
    }

    //FALTA MÉTODO GETTESTTYPES!!

}
