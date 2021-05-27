package app.controller;

import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.Company;
import app.domain.store.ClinicalAnalysisLaboratoryStore;
import app.mappers.ClinicalAnalysisLaboratoryMapper;
import app.mappers.dto.ClinicalAnalysisLaboratoryDTO;

import java.util.List;

public class SelectCalController {
    private Company company;
    private ClinicalAnalysisLaboratory cal;

    /**
     * Builds an empty constructor for having the actual instance of the company when instantiated.
     */
    public SelectCalController() {
        this(App.getInstance().getCompany());
    }

    /**
     * Builds a Record Samples Controller's instance receiving the company.
     *
     * @param company company associated to the Controller.
     */
    public SelectCalController(Company company) {
        this.company = company;
        this.cal = null;
    }

    public List<ClinicalAnalysisLaboratoryDTO> getCalListDTO() {
        ClinicalAnalysisLaboratoryMapper mapper = new ClinicalAnalysisLaboratoryMapper();
        return mapper.toDTO(getCalList());
    }

    public List<ClinicalAnalysisLaboratory> getCalList() {
        ClinicalAnalysisLaboratoryStore calStore = this.company.getCalStore();
        return calStore.getCalList();
    }
}
