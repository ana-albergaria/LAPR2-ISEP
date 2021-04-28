package app.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClinicalAnalysisLaboratory extends Laboratory {
    private String laboratoryID;
    private Laboratory isOf;
    private List<TestType> selectedTT;

    public ClinicalAnalysisLaboratory(String laboratoryID, String name, String address, int phoneNumber, int numTIN, List<TestType> selectedTT) {
        super(name, address, phoneNumber, numTIN);
        this.laboratoryID = laboratoryID;
        this.selectedTT = new ArrayList<TestType>(selectedTT);
    }

    /*
     **AC1:** All required field must be filled in.
     * **AC2:** The Laboratory ID must have five alphanumeric characters.
     * **AC3:** The name is a string with no more than 20 characters.
     * **AC4:** The Phone Number is a 11 digit number.
     * **AC5:** The TIN Number is a 10 digit number.
     * **AC6:** Type of tests must be an attribute of the Clinical Analysis Laboratory.
     */

    private void checkLaboratoryIDRules(String laboratoryID) {
        if (StringUtils.isBlank(laboratoryID))
            throw new IllegalArgumentException("Laboratory ID cannot be blank.");
        if ((laboratoryID.length() != 5)
            throw new IllegalArgumentException("Laboratory ID must have exactly five alphanumeric characters.");
        //ALTERNATIVA 1
        /*
        if ((!laboratoryID.matches("^[a-zA-Z0-9]*$")
            throw new IllegalArgumentException("Laboratory ID must only have alphanumeric characters.");
         */
        //ALTERNATIVA 2
        if ((!laboratoryID.chars().allMatch(Character::isLetterOrDigit))
            throw new IllegalArgumentException("Laboratory ID must only have alphanumeric characters.");
    }

    public void checkNameRules(String name) {
        if (StringUtils.isBlank(laboratoryID))
            throw new IllegalArgumentException("Name cannot be blank.");
        if (!laboratoryID.matches())
            throw new IllegalArgumentException("Name cannot be blank.");

    }

    //FALTA FAZER VALIDAÇÃO DOS ACCEPTING CRITERIA LOCALMENTE + TOSTRING
}
