package app.domain.model;

import app.domain.store.ClientSore;
import app.domain.store.ParameterCategoryStore;
import app.domain.store.ParameterStore;
import app.domain.store.TestTypeStore;
import app.mappers.dto.ClinicalAnalysisLaboratoryDTO;
import app.mappers.dto.EmployeeDTO;
import app.mappers.dto.SpecialistDoctorDTO;
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
    private List<Employee> empList;
    private List<OrgRole> roles;

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
        this.empList = new ArrayList<>();
        this.roles = new ArrayList<>();
        this.parameterStore = new ParameterStore();
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
            throw new IllegalArgumentException("The Clinical Analysis Laboratory cannot be null.");
            //return false;
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


    //to be used in US7

    public List<OrgRole> getRoles() {
        return new ArrayList<>(roles);
    }

    /**
     * Get Organization Role according to the its description
     * @param roleDescription
     * @return Organization Role reference
     */
    private OrgRole getRoleByDescription(String roleDescription) {
        //PASSAR ESTE CÓDIGO PARA O CONSTRUTOR DA COMPANY
        OrgRole r1 = new OrgRole("Administrator");
        OrgRole r2 = new OrgRole("Receptionist");
        OrgRole r3 = new OrgRole("Medical Lab Technician");
        OrgRole r4 = new OrgRole("Laboratory Coordinator");
        OrgRole r5 = new OrgRole("Specialist Doctor");
        OrgRole r6 = new OrgRole("Clinical Chemistry Technologist");
        this.roles.add(r1);
        this.roles.add(r2);
        this.roles.add(r3);
        this.roles.add(r4);
        this.roles.add(r5);
        this.roles.add(r6);

        for (OrgRole role : roles) {
            if(role.getDescription().equalsIgnoreCase(roleDescription)){
                return role;
            }
        }
        throw new UnsupportedOperationException("Organization Role not found with given description: " + roleDescription);
    }

    public Employee createEmployee(EmployeeDTO empDTO) {
        String roleDesignation = empDTO.getRoleDesignation();
        OrgRole role = getRoleByDescription(roleDesignation);

        return new Employee(role, empDTO.getEmployeeID(), empDTO.getName(),
                empDTO.getAddress(), empDTO.getPhoneNumber(), empDTO.getEmail(), empDTO.getSocCode());
    }

    public SpecialistDoctor createSpecialistDoctor(SpecialistDoctorDTO sdDTO) {
        String roleDesignation = sdDTO.getRoleDesignation();
        OrgRole role = getRoleByDescription(roleDesignation);

        return new SpecialistDoctor(role, sdDTO.getEmployeeID(), sdDTO.getName(),
                sdDTO.getAddress(), sdDTO.getPhoneNumber(), sdDTO.getEmail(),
                sdDTO.getSocCode(), sdDTO.getDoctorIndexNumber());
    }

    public boolean validateEmployee(Employee emp) {
        if(emp == null)
            return false;
        return ! this.empList.contains(emp);
    }

    public boolean saveEmployee(Employee emp) {
        if(!validateEmployee(emp))
            return false;

        return this.empList.add(emp);
    }

}