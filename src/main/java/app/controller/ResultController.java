package app.controller;

import app.domain.model.Company;
import app.domain.model.Parameter;
import app.domain.model.Test;
import app.domain.model.TestParameterResult;
import app.domain.store.TestStore;
import app.mappers.TestMapper;
import app.mappers.dto.TestDTO;

import java.util.List;

public class ResultController {

    //addTestResult(parameterCode, result, metric)

    /**
     * The company associated to the Controller
     */
    private Company company;

    /**
     * The test parameter result associated to the Controller.
     */
    private TestParameterResult result;

    /**
     * Builds an empty constructor for having the actual instance of the company when instanciated.
     */
    public ResultController(){
        this(App.getInstance().getCompany());
    }

    /**
     * Builds a Result Controller's instance receiving the company.
     *
     * @param company company associated to the Controller.
     */
    public ResultController(Company company){
        this.company = company;
        this.result = null;
    }

    //public boolean createResult(...)

    /*
    public List<Parameter> getTotalTestParameters(String barcodeNumber) {
        TestStore testStore = this.company.getTestStore();
        Test selectedTest = testStore.getTestByBarcodeNumber(barcodeNumber);
        List<Test> listTotalTestParameters = testStore.getTotalTestParameters(selectedTest);

        TestMapper mapper = new TestMapper();
        return mapper.toDTO(listTotalTestParameters);
    }

     */
}
