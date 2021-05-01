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
        checkSelectedTestTypesRules(selectedTT);
        this.laboratoryID = laboratoryID;
        this.selectedTT = new ArrayList<>(selectedTT);
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

    private void checkSelectedTestTypesRules(List<TestType> selectedTT) {
        if(selectedTT == null || selectedTT.isEmpty())
            throw new IllegalArgumentException("The list containing the Types of Test cannot be blank.");
    }


}
