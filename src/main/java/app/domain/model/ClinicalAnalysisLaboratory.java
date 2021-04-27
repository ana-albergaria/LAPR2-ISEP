package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class ClinicalAnalysisLaboratory extends Laboratory {
    private String laboratoryID;
    private Laboratory isOf;
    private List<TestType> selectedTT;

    public ClinicalAnalysisLaboratory(String laboratoryID, String name, String address, int phoneNumber, int numTIN, List<TestType> selectedTT) {
        super(name, address, phoneNumber, numTIN);
        this.laboratoryID = laboratoryID;
        selectedTT = new ArrayList<TestType>();
    }

    //FALTA FAZER VALIDAÇÃO DOS ACCEPTING CRITERIA LOCALMENTE + TOSTRING
}
