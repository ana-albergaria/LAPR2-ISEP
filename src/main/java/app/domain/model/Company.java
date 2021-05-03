package app.domain.model;

import app.domain.store.ClientSore;
import app.domain.store.ParameterCategoryStore;
import app.domain.store.TestTypeStore;
import auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {

    private String designation;
    private AuthFacade authFacade;
    private ParameterCategoryStore parameterCategoryStore;
    private TestTypeStore testTypeStore; //Company uses TestTypeStore
    private List<Laboratory> laboratories; //Company owns Laboratory
    private List<Parameter> prmList;
    private List<ClinicalAnalysisLaboratory> calList;

    private ClientSore clientSore;

    public Company(String designation)
    {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();
        this.testTypeStore = new TestTypeStore();
        this.parameterCategoryStore = new ParameterCategoryStore();
        this.calList = new ArrayList<>();
    }

    public String getDesignation() {
        return designation;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    public ParameterCategoryStore getParameterCategoryStore() {
        return parameterCategoryStore;
    }

    //to be used in US8
    public TestTypeStore getTestTypeStore() {
        return testTypeStore;
    }

    public ClientSore getClientSore(){
        return clientSore;
    }

    //to be used in US10
    public boolean validateParameter(Parameter prm){
        if (prm == null)
            return false;
        return ! this.prmList.contains(prm);
    }

    public boolean saveParameter(Parameter prm){
        if (!validateParameter(prm))
            return false;
        return this.prmList.add(prm);
    }


    //to be used in US8
    public ClinicalAnalysisLaboratory createClinicalAnalysisLaboratory(String laboratoryID,
                                                                       String name,
                                                                       String address,
                                                                       String phoneNumber,
                                                                       String numTIN,
                                                                       List<TestType> selectedTT) {
        return new ClinicalAnalysisLaboratory(laboratoryID, name, address,
                phoneNumber, numTIN, selectedTT);
    }


    public boolean validateClinicalAnalysisLaboratory(ClinicalAnalysisLaboratory cal){
        if (cal == null)
            return false;
        return ! this.calList.contains(cal);
    }

    public boolean saveClinicalAnalysisLaboratory(ClinicalAnalysisLaboratory cal){
        if (!validateClinicalAnalysisLaboratory(cal))
            return false;
        return this.calList.add(cal);
    }

}