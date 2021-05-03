package app.domain.mappers;

import app.domain.mappers.dto.ClinicalAnalysisLaboratoryDTO;
import app.domain.mappers.dto.TestTypeDTO;
import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.TestType;

import java.util.ArrayList;
import java.util.List;

public class ClinicalAnalysisLaboratoryMapper {

    public ClinicalAnalysisLaboratoryDTO toDTO(ClinicalAnalysisLaboratory cal) {
        return new ClinicalAnalysisLaboratoryDTO(cal.getLaboratoryID(), cal.getName(),
                cal.getAddress(), cal.getPhoneNumber(), cal.getNumTIN(), cal.getSelectedTT());
    }

    /*
    TO MODEL()??????
     */
}
