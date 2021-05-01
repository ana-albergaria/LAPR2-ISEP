package app.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClinicalAnalysisLaboratory extends Laboratory {
    private String laboratoryID;
    private Laboratory isOf; //ClinicalAnalysisLaboratory isOf Laboratory
    private List<TestType> selectedTT; //ClinicalAnalysisLaboratory operates * TestType

    public ClinicalAnalysisLaboratory(String laboratoryID, String name, String address, String phoneNumber, String numTIN, List<TestType> selectedTT) {
        super(name, address, phoneNumber, numTIN);
        checkLaboratoryIDRules(laboratoryID);
        this.laboratoryID = laboratoryID;
        this.selectedTT = new ArrayList<TestType>(selectedTT);
    }

    @Override
    public String toString() {
        System.out.printf("%sLaboratory ID: %s%nTest Types: %n",
                super.toString(), laboratoryID);
        selectedTT.forEach(System.out::println);
        return "";
    }

    private void checkLaboratoryIDRules(String laboratoryID) {
        if (StringUtils.isBlank(laboratoryID))
            throw new IllegalArgumentException("Laboratory ID cannot be blank.");
        if ((laboratoryID.length() != 5))
            throw new IllegalArgumentException("Laboratory ID cannot have more or less than five alphanumeric characters.");
        if(!StringUtils.isAlphanumeric(laboratoryID))
            throw new IllegalArgumentException("Laboratory ID must only have alphanumeric characters.");
    }


    //FALTA FAZER VALIDAÇÃO DOS ACCEPTING CRITERIA LOCALMENTE + TOSTRING
    //FALTA VALIDAÇÃO TIPOS DE TESTE!!!
    /*ALTERNATIVA 1
        if ((!laboratoryID.matches("^[a-zA-Z0-9]*$")
            throw new IllegalArgumentException("Laboratory ID must only have alphanumeric characters.");
        //ALTERNATIVA 2
        if ((!laboratoryID.chars().allMatch(Character::isLetterOrDigit)))
            throw new IllegalArgumentException("Laboratory ID must only have alphanumeric characters.");
         */
}
