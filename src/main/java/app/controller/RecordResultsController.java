package app.controller;

import app.domain.model.Company;
import app.domain.model.Parameter;
import app.domain.model.Test;
import app.domain.model.TestParameterResult;
import app.domain.store.TestStore;
import app.mappers.ParameterMapper;
import app.mappers.dto.ParameterDTO;

import java.util.List;

public class RecordResultsController {

    //addTestResult(parameterCode, result, metric)

    /**
     * The company associated to the Controller
     */
    private Company company;

    /**
     * The test parameter result associated to the Controller.
     */
    private TestParameterResult result;

    private Test test;

    /**
     * Builds an empty constructor for having the actual instance of the company when instanciated.
     */
    public RecordResultsController(){
        this(App.getInstance().getCompany());
    }

    /**
     * Builds a Result Controller's instance receiving the company.
     *
     * @param company company associated to the Controller.
     */
    public RecordResultsController(Company company){
        this.company = company;
        this.result = null;
    }

    //public boolean createResult(...)


    public List<ParameterDTO> getTotalTestParameters(String barcodeNumber) {
        TestStore testStore = this.company.getTestStore();
        this.test = testStore.getTestByBarcodeNumber(barcodeNumber);
        List<Parameter> listTotalTestParameters = testStore.getTotalTestParameters(test);

        ParameterMapper mapper = new ParameterMapper();
        return mapper.toDTO(listTotalTestParameters);
    }

    //void?
    public void addTestResult(String parameterCode, Double result, String metric) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        this.test.addTestResult(parameterCode, result, metric);
    }






}