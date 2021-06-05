package app.controller;

import app.domain.model.Company;
import app.domain.model.CompanyPerformanceAnalysis;
import app.domain.store.ClientStore;
import app.domain.store.TestStore;

import java.util.ArrayList;

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

    /*public ArrayList<Integer> getTestOverview(){
        ClientStore clientStore = this.company.getClientStore();
        ArrayList<Integer> testOverview = clientStore.getClientInfo();
        TestStore testStore = this.company.getTestStore();
        testOverview.addAll(testStore.getTestsInfo());
        return  testOverview;
    }*/

    /*
    testOverview -> arraylist of integers with:
    number of clients
    ---
    number of tests waiting for results
    number of tests waiting for diagnosis
    total number of tests processed in the laboratory in each day...
    week...
    month...
    year
    */

    //+findWorstSubInt(firstDayToAnalyse, lastDayToAnalyse, chosen Algorithm)

}
