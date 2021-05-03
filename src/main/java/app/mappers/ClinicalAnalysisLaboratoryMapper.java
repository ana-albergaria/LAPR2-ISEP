package app.mappers;

import app.mappers.dto.ClinicalAnalysisLaboratoryDTO;
import app.domain.model.ClinicalAnalysisLaboratory;

public class ClinicalAnalysisLaboratoryMapper {

    public ClinicalAnalysisLaboratoryDTO toDTO(ClinicalAnalysisLaboratory cal) {
        return new ClinicalAnalysisLaboratoryDTO(cal.getLaboratoryID(), cal.getName(),
                cal.getAddress(), cal.getPhoneNumber(), cal.getNumTIN(), cal.getSelectedTT());
    }

    /*
    TO MODEL()??????
     */
}
