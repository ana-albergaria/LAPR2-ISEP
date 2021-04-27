package app.domain.model;

import java.util.List;

public class Laboratory {
    private String name;
    private String address;
    private int phoneNumber;
    private int numTIN;

    public Laboratory(String name, String address, int phoneNumber, int numTIN) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.numTIN = numTIN;
    }

    private List<ClinicalAnalysisLaboratory> calList;


    public ClinicalAnalysisLaboratory createClinicalAnalysisLaboratory(String laboratoryID,
                                                                       String address,
                                                                       int phoneNumber,
                                                                       int numTIN,
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



    //SUPOSTAMENTE ESTÁ COMPLETO!


}
