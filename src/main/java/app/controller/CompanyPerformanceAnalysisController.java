package app.controller;

import app.domain.model.Company;
import app.domain.store.ClientStore;
import app.domain.store.TestStore;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Marta Ribeiro (1201592)
 */
public class CompanyPerformanceAnalysisController {

    /**
     * The company associated with the Controller.
     */
    private Company company;

    /**
     * Builds an empty constructor for having the actual instance of the company when instantiated.
     */
    public CompanyPerformanceAnalysisController(){
        this(App.getInstance().getCompany());
    }

    /**
     * Builds a Company Performance Analysis Controller's instance receiving the company.
     *
     * @param company company associated to the Controller.
     */
    public CompanyPerformanceAnalysisController(Company company){
        this.company = company;
    }

    /**
     * Creates and returns an ArrayList with all the info to be considered when analysing the company performance
     * @return an ArrayList with all the info to be considered when analysing the company performance
     */
    public ArrayList<Integer> getTestOverview(){
        ArrayList<Integer> testOverview = new ArrayList<>();
        ClientStore clientStore = this.company.getClientStore();
        int clientInfo = clientStore.getClientInfo();
        testOverview.add(clientInfo);
        TestStore testStore = this.company.getTestStore();
        testOverview.addAll(testStore.getTestsInfo());
        return  testOverview;
    }

        /*
    testOverview -> arraylist of integers with:
    number of clients
    number of tests waiting for results
    number of tests waiting for diagnosis
    -------------
    faltam:
    -------------
    total number of tests processed in the laboratory in each day...
    week...
    month...
    year
    */

    public ArrayList<Integer> findWorstSubIntWithBenchmarkAlgorithm(Date firstDayToAnalyse, Date lastDayToAnalyse){
        //falta fazer
        return null;
    }

    public ArrayList<Integer> findWorstSubIntWithBruteforceAlgorithm(Date firstDayToAnalyse, Date lastDayToAnalyse){
        //falta fazer
        return null;
    }

}