package app.mappers.dto;

import app.domain.model.Laboratory;
import app.domain.model.TestType;

import java.util.ArrayList;
import java.util.List;

public class ClinicalAnalysisLaboratoryDTO extends Laboratory {
    /**
     * The laboratory ID of the Clinical Analysis Laboratory.
     */
    private String laboratoryID;

    /**
     * The type of tests the Clinical Analysis Laboratory operates.
     */
    private List<String> testTypeCodes;

    /**
     * Builds a Clinical Analysis Laboratory's instance receiving:
     * the Laboratory ID, the name, the address, the phone number, the TIN number and
     * the type of tests
     *
     * @param laboratoryID the laboratory ID of the Clinical Analysis Laboratory
     * @param name the name of the Clinical Analysis Laboratory
     * @param address the address of the Clinical Analysis Laboratory
     * @param phoneNumber the phone number of the Clinical Analysis Laboratory
     * @param numTIN the TIN number of the Clinical Analysis Laboratory
     * @param testTypeCodes the codes of the selected types of tests the Clinical Analysis Laboratory operates
     */
    public ClinicalAnalysisLaboratoryDTO(String laboratoryID, String name, String address, String phoneNumber, String numTIN, List<String> testTypeCodes) {
        super(name, address, phoneNumber, numTIN);
        this.laboratoryID = laboratoryID;
        this.testTypeCodes = new ArrayList<>(testTypeCodes);
    }

    /**
     * Returns the laboratory ID of the Clinical Analysis Laboratory
     *
     * @return laboratory ID of the Clinical Analysis Laboratory
     */
    public String getLaboratoryID() {
        return laboratoryID;
    }

    /**
     * Returns the codes of the selected test types of the Clinical Analysis Laboratory
     *
     * @return codes of test types of the Clinical Analysis Laboratory
     */
    public List<String> getTestTypeCodes() {
        return new ArrayList<>(testTypeCodes);
    }
}
