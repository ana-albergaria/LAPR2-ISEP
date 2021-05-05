package app.domain.model;

import app.domain.store.ClientSore;
import app.domain.store.ParameterCategoryStore;
import app.domain.store.ParameterStore;
import app.domain.store.TestTypeStore;
import app.mappers.dto.ClinicalAnalysisLaboratoryDTO;
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
    private ParameterStore parameterStore;
    private TestTypeStore testTypeStore; //Company uses TestTypeStore
    private List<Laboratory> laboratories; //Company owns Laboratory
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

    //to be used in US10
    public ParameterStore getParameterStore(){
        return parameterStore;
    }

    //to be used in US8
    public TestTypeStore getTestTypeStore() {
        return testTypeStore;
    }

    public ClientSore getClientSore(){
        return clientSore;
    }

    //to be used in US8

    /*
    public ClinicalAnalysisLaboratory createClinicalAnalysisLaboratory(String laboratoryID,
                                                                       String name,
                                                                       String address,
                                                                       String phoneNumber,
                                                                       String numTIN,
                                                                       List<TestType> selectedTT) {
        return new ClinicalAnalysisLaboratory(laboratoryID, name, address,
                phoneNumber, numTIN, selectedTT);
    }
     */

    public ClinicalAnalysisLaboratory createClinicalAnalysisLaboratory(ClinicalAnalysisLaboratoryDTO calDTO) {
        TestTypeStore storeTest = getTestTypeStore();
        List<TestType> selectedTT = storeTest.getTestTypesByCode(calDTO.getTestTypeCodes());

        return new ClinicalAnalysisLaboratory(calDTO.getLaboratoryID(), calDTO.getName(),
                calDTO.getAddress(), calDTO.getPhoneNumber(), calDTO.getNumTIN(), selectedTT);
    }

    public boolean validateClinicalAnalysisLaboratory(ClinicalAnalysisLaboratory cal){
        if (cal == null)
            return false;
        checkCalDuplicates(cal);
        return ! this.calList.contains(cal);
    }

    public boolean saveClinicalAnalysisLaboratory(ClinicalAnalysisLaboratory cal){
        if (!validateClinicalAnalysisLaboratory(cal))
            return false;

        return this.calList.add(cal);
    }

    private void checkCalDuplicates(ClinicalAnalysisLaboratory cal) {
        for (ClinicalAnalysisLaboratory item : calList) {
            if(cal.getLaboratoryID().equalsIgnoreCase(item.getLaboratoryID()))
                throw new IllegalArgumentException("Laboratory ID already registered in the system.");
            if(cal.getAddress().equalsIgnoreCase(item.getAddress()))
                throw new IllegalArgumentException("Address already registered in the system.");
            if(cal.getPhoneNumber().equals(item.getPhoneNumber()))
                throw new IllegalArgumentException("Phone Number already registered in the system.");
            if(cal.getNumTIN().equals(item.getNumTIN()))
                throw new IllegalArgumentException("TIN Number already registered in the system.");
        }
    }

}