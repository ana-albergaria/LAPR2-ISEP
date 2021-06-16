package app.controller;

import app.domain.model.*;
import app.domain.store.*;
import app.mappers.dto.TestFileDTO;
import net.sourceforge.barbecue.BarcodeException;
import java.util.Date;
import java.util.List;

/**
 * Controller class to coordenate the creation of a test with all attributes
 *
 * @author João Wolff
 */
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

    /**
     * Method for creating an test instance with all attributes
     * @param testFileDTO testfile dto which contains all needed data
     * @return true if successfully created and false otherwise
     * @throws IllegalAccessException if there's a method invoked does not have access to the class representing the API
     * @throws ClassNotFoundException if the class name of the external API is not found
     * @throws InstantiationException if the class object of the external API cannot be instantiated
     */
    public boolean createTest(TestFileDTO testFileDTO) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        TestStore testStore = this.company.getTestStore();
        ClientStore clientStore = this.company.getClientStore();
        TestTypeStore testTypeStore = this.company.getTestTypeStore();
        ClinicalAnalysisLaboratoryStore calStore = this.company.getCalStore();
        ParameterStore parameterStore = this.company.getParameterStore();

        Client associatedClient = clientStore.getClientByTinNumber(testFileDTO.getClientDTO().getTinNumber());
        TestType testType = testTypeStore.getSingleTestTypeByCode(testFileDTO.getTestTypeCode());
        List<Parameter> parameters = parameterStore.getParamsByCodes(testFileDTO.getTestParameterCodes());
        List<Double> results = testFileDTO.getTestParameterResults();
        ClinicalAnalysisLaboratory cal = calStore.getCalByCode(testFileDTO.getLabId());

        Date testRegDate = testFileDTO.getDateOfTestRegistration();
        Date testChemDate = testFileDTO.getDateOfChemicalAnalysis();
        Date testDiagnosisDate = testFileDTO.getDateOfDiagnosis();
        Date testValidationDate = testFileDTO.getDateOfValidation();

        String nhsCode = testFileDTO.getNhsCode();

        this.test = testStore.createTest(nhsCode, associatedClient, testType, parameters,results, cal, testRegDate, testChemDate, testDiagnosisDate, testValidationDate);

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

    /**
     * Method for adding a sample if the current test has the date of chemical analysis
     * @return true if successfully added and false otherwise
     * @throws IllegalAccessException if there's a method invoked does not have access to the class representing the API
     * @throws ClassNotFoundException if the class name of the external API is not found
     * @throws InstantiationException if the class object of the external API cannot be instantiated
     * @throws BarcodeException  if the data to be encoded in the barcode is invalid
     */
    public boolean addSample() throws IllegalAccessException, InstantiationException, BarcodeException, ClassNotFoundException {
        if(test.getDateOfChemicalAnalysis() != null) {
            RecordSamplesController samplesController = new RecordSamplesController();
            samplesController.createSample();
            return samplesController.addSample(test.getCode());
        }
        return true;
    }

}
