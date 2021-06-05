package app.controller;

import app.domain.model.Company;
import app.domain.model.CompanyPerformanceAnalysis;

public class CompanyPerformanceAnalysisController {

    /**
     * The company associated with the Controller.
     */
    private Company company;

    private CompanyPerformanceAnalysis analysis;

    /**
     * Builds an empty constructor for having the actual instance of the company when instantiated.
     */
    public CompanyPerformanceAnalysisController(){
        this(App.getInstance().getCompany());
    }

    /**
     * Builds a Report Controller's instance receiving the company.
     *
     * @param company company associated to the Controller.
     */
    public CompanyPerformanceAnalysisController(Company company){
        this.company = company;
        this.analysis = null;
    }



    //+getTestOverview()

    //+findWorstSubInt(firstDayToAnalyse, lastDayToAnalyse, chosen Algorithm)

}
