package app.controller;

import app.domain.model.*;
import app.domain.store.*;
import app.mappers.dto.TestFileDTO;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;

import java.io.IOException;
import java.util.Date;
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

    public boolean createTest(TestFileDTO testFileDTO) throws IllegalAccessException, InstantiationException, ClassNotFoundException, BarcodeException, OutputException, IOException {
        TestStore testStore = this.company.getTestStore();
        ClientStore clientStore = this.company.getClientStore();
        TestTypeStore testTypeStore = this.company.getTestTypeStore();
        ClinicalAnalysisLaboratoryStore calStore = this.company.getCalStore();
        ParameterStore parameterStore = this.company.getParameterStore();

        Client associatedClient = clientStore.getClientByTinNumber(testFileDTO.getClientDTO().getTinNumber());
        TestType testType = testTypeStore.getSingleTestTypeByCode(testFileDTO.getTestTypeCode());
        List<Parameter> parameters = parameterStore.getParamsByCodes(testFileDTO.getTestParameterCodes());
        List<Double> results = testFileDTO.getTestParameterResults();
        /*ClinicalAnalysisLaboratory cal = calStore.getCalByCode(testFileDTO.getLabId());*/
        ClinicalAnalysisLaboratory cal = null;

        Date testRegDate = testFileDTO.getDateOfTestRegistration();
        Date testChemDate = testFileDTO.getDateOfChemicalAnalysis();
        Date testDiagnosisDate = testFileDTO.getDateOfDiagnosis();
        Date testValidationDate = testFileDTO.getDateOfValidation();

        String nhsCode = testFileDTO.getNhsCode();

        this.test = testStore.createTest(nhsCode, associatedClient, testType, parameters,results, cal, testRegDate, testChemDate, testDiagnosisDate, testValidationDate);

        if(test.hasChemDate()){
            RecordSamplesController sample_controller = new RecordSamplesController();
            sample_controller.createSample();
        }

        return testStore.validateTest(test);
    }

    /**
     * Saves current test in the test store
     *
     * @return True if successfully validated and saved false the otherway
     */
    public boolean saveTest() {
        TestStore testStore = this.company.getTestStore();
        return testStore.saveTest(test);
    }

}
