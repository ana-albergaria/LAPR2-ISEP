package app.domain.model;

import java.util.List;

public class LaboratoryStore {
    private List<Laboratory> labList;
    private List<ClinicalAnalysisLaboratory> calList;


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


    //SUPOSTAMENTE ESTÁ COMPLETO!
}
