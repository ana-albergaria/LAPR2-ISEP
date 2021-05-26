package app.controller;

import app.domain.model.*;
import app.domain.store.TestStore;
import app.mappers.TestMapper;
import app.mappers.dto.TestDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for making a diagnosis and writing a report for a given test.
 *
 * @author Marta Ribeiro 1201592
 */
public class WriteReportController {

    /**
     * The company associated to the Controller.
     */
    private Company company;

    /**
     * The report associated to the controller.
     */
    private Report report;

    /**
     * Builds an empty constructor for having the actual instance of the company when instantiated.
     */
    public WriteReportController(){
        this(App.getInstance().getCompany());
    }

    /**
     * Builds a Report Controller's instance receiving the company.
     *
     * @param company company associated to the Controller.
     */
    public WriteReportController(Company company){
        this.company = company;
        this.report = null;
    }

    /*public boolean createReport(String reportText){
        this.report = company.createReport(reportText);
        return
    }*/

    /**
     * Retrieves actual tests to diagnose list.
     *
     * @return tests to diagnose list.
     */
    public List<TestDTO> getTestsToDiagnose(){
        TestStore testStore = this.company.getTestStore();
        List<Test> testsToDiagnose = testStore.getTestsReadyToDiagnose();
        TestMapper mapper = new TestMapper();
        return mapper.toDTO(testsToDiagnose);
    }

    /*public List<TestParameterResult> getTestParametersResults(String code){
        TestStore tstStore = this.company.getTestStore();
        Test tst = tstStore.getTestByCode(code);
        List<TestParameter> listTestParams = tstStore.getTestParameters(tst);
        List<TestParameterResult> listTestResults = new ArrayList<>();
        for (TestParameter testParam : listTestParams )
            listTestResults.add(testParam.getParameterResult());
        return listTestResults;
    }*/

}
