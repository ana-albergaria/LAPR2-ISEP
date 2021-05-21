package app.controller;

import app.domain.model.Company;
import app.domain.model.TestParameterResult;

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

}
