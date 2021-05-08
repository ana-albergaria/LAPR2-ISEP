package app.domain.model;

import app.domain.store.ClientStore;
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
    private List<ClinicalAnalysisLaboratory> calList;
    private List<Employee> empList;
    private List<OrgRole> roles;

    private ClientStore clientStore;

    public Company(String designation)
    {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();
        this.testTypeStore = new TestTypeStore();
        this.clientStore = new ClientStore();
        this.parameterCategoryStore = new ParameterCategoryStore();
        this.calList = new ArrayList<>();
        this.empList = new ArrayList<>();
        this.roles = new ArrayList<>();
        this.parameterStore = new ParameterStore();
        OrgRole r1 = new OrgRole("ADMINISTRATOR");
        OrgRole r2 = new OrgRole("RECEPTIONIST");
        OrgRole r3 = new OrgRole("MED LAB TECH");
        OrgRole r4 = new OrgRole("LAB COORDINATOR");
        OrgRole r5 = new OrgRole("SPEC DOCTOR");
        OrgRole r6 = new OrgRole("C CHEM TECH");
        this.roles.add(r1);
        this.roles.add(r2);
        this.roles.add(r3);
        this.roles.add(r4);
        this.roles.add(r5);
        this.roles.add(r6);
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

    public ClientStore getClientStore(){
        return clientStore;
    }

    public List<Employee> getEmpList() {
        return empList;
    }

    //to be used in US8

    /**
     * Called method through the RegisterNewCalController to create a new Clinical Analysis Laboratory
     * through a Dto containing all the requested data:
     * laboratoryID, name, address, phone number, TIN number and the codes of the types of test
     *
     * @param calDTO
     * @return instance of ClinicalAnalysisLaboratory Class with the information provided by the Dto
     *          received by parameter
     */
    public ClinicalAnalysisLaboratory createClinicalAnalysisLaboratory(ClinicalAnalysisLaboratoryDTO calDTO) {
        TestTypeStore storeTest = getTestTypeStore();
        List<TestType> selectedTT = storeTest.getTestTypesByCode(calDTO.getTestTypeCodes());

        return new ClinicalAnalysisLaboratory(calDTO.getLaboratoryID(), calDTO.getName(),
                calDTO.getAddress(), calDTO.getPhoneNumber(), calDTO.getNumTIN(), selectedTT);
    }

    /**
     * Called method through the RegisterNewCalController to validate a new Clinical Analysis Laboratory.
     *
     * @param cal
     * @return true if the new Clinical Analysis Laboratory was successfully validated.
     *          Otherwise, returns false.
     */
    public boolean validateClinicalAnalysisLaboratory(ClinicalAnalysisLaboratory cal){
        if(cal == null)
            return false;
        checkCalDuplicates(cal);
        return ! this.calList.contains(cal);
    }

    /**
     * Called method through the RegisterNewCalController to save a new Clinical Analysis Laboratory.
     *
     * @param cal
     * @return true if the new Clinical Analysis Laboratory was successfully saved.
     *          Otherwise, returns false.
     */
    public boolean saveClinicalAnalysisLaboratory(ClinicalAnalysisLaboratory cal){
        if (!validateClinicalAnalysisLaboratory(cal))
            return false;

        return this.calList.add(cal);
    }

    /**
     * Global validation method regarding the creation of a new Clinical Analysis Laboratory.
     * Its goal is to make sure there aren't any Clinical Analysis Laboratories with the same:
     * laboratory ID or address or phone number or TIN number.
     *
     * It receives in the parameter the Clinical Analysis Laboratory to be created.
     *
     * @param cal
     */
    public void checkCalDuplicates(ClinicalAnalysisLaboratory cal) {
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

        return new Employee(role, empDTO.getName(),
                empDTO.getAddress(), empDTO.getPhoneNumber(), empDTO.getEmail(), empDTO.getSocCode());
    }

    public SpecialistDoctor createSpecialistDoctor(SpecialistDoctorDTO sdDTO) {
        String roleDesignation = sdDTO.getRoleDesignation();
        OrgRole role = getRoleByDescription(roleDesignation);

        return new SpecialistDoctor(role, sdDTO.getName(), sdDTO.getAddress(),
                sdDTO.getPhoneNumber(), sdDTO.getEmail(), sdDTO.getSocCode(), sdDTO.getDoctorIndexNumber());
    }

    public boolean validateEmployee(Employee emp) {
        if(emp == null)
            return false;
        return !this.empList.contains(emp);
    }

    public boolean saveEmployee(Employee emp) {
        if(!validateEmployee(emp)) {
            return false;
        }
        return this.empList.add(emp);
    }

    public boolean addUserRole(Employee emp) {
        return this.getAuthFacade().addUserRole(emp.getRole().getDescription(), emp.getRole().getDescription());
    }

    public boolean makeEmployeeAUser(Employee emp, String generatedPassword){
        boolean success = this.authFacade.addUserWithRole(emp.getName(), emp.getEmail(), generatedPassword, emp.getRole().getDescription());
        if(!success){
            addUserRole(emp);
            return this.authFacade.addUserWithRole(emp.getName(), emp.getEmail(), generatedPassword, emp.getRole().getDescription());
        }
        return success;
    }

}