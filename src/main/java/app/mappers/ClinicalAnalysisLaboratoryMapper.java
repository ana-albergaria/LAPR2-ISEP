package app.mappers;

import app.domain.model.ClinicalAnalysisLaboratory;
import app.mappers.dto.ClinicalAnalysisLaboratoryDTO;

import java.util.ArrayList;
import java.util.List;

public class ClinicalAnalysisLaboratoryMapper {
    public ClinicalAnalysisLaboratoryDTO toDTO(ClinicalAnalysisLaboratory cal) {
        return new ClinicalAnalysisLaboratoryDTO(cal.getLaboratoryID(), cal.getName(), cal.getAddress(),
                cal.getPhoneNumber(), cal.getNumTIN(), cal.getSelectedTT(), cal.getCalTestList());
    }

    public List<ClinicalAnalysisLaboratoryDTO> toDTO(List<ClinicalAnalysisLaboratory> calTestList) {
        List<ClinicalAnalysisLaboratoryDTO> calTestListDTOS = new ArrayList<>();
        for (ClinicalAnalysisLaboratory cal : calTestList) {
            calTestListDTOS.add(this.toDTO(cal));
        }
        return calTestListDTOS;
    }
}

