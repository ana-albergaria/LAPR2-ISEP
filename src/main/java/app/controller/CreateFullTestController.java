package app.controller;

import app.domain.model.*;
import app.domain.store.*;
import app.mappers.dto.TestFileDTO;

import java.util.List;

public class CreateFullTestController {

    /**
     * Company instance of the session
     */
    private final Company company;

    /**
     * Test to be created by the controller
     */
    private Test test;

    public CreateFullTestController() {
        this(App.getInstance().getCompany());
    }

    /**
     * Construtor recieving the company as an argument
     *
     * @param company instance of company to be used
     */
    public CreateFullTestController(Company company) {
        this.company = company;
    }

    public boolean createTest(TestFileDTO testFileDTO){
        TestStore testStore = this.company.getTestStore();
        ClientStore clientStore = this.company.getClientStore();
        TestTypeStore testTypeStore = this.company.getTestTypeStore();
        ClinicalAnalysisLaboratoryStore calStore = this.company.getCalStore();
        ParameterStore parameterStore = this.company.getParameterStore();

        Client associatedClient = clientStore.getClientByTinNumber(testFileDTO.getClientDTO().getTinNumber());
        TestType testType = testTypeStore.getSingleTestTypeByCode(testFileDTO.getTestTypeCode());
        List<Parameter> parameters = parameterStore.getParamsByCodes(testFileDTO.getTestParameterCodes());
        ClinicalAnalysisLaboratory cal = calStore.getCalByCode(testFileDTO.getLabId());

        this.test = testStore.createTest(nhsCode, associatedClient, testType, parameters, currentCal);

        return testStore.validateTest(test);
    }

}
