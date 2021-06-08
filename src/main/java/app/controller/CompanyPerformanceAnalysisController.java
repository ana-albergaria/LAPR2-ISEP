package app.controller;

import app.domain.model.Company;
import app.domain.store.ClientStore;
import app.domain.store.TestStore;
//import com.isep.mdis.Sum;
import org.apache.commons.lang3.time.DateUtils;

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

    //12 WORKING HOURS PER DAY
    //WORKING DAY IS FROM 8:00 TO 20:00
    //SUNDAY IS NOT A WORKING DAY

    //test store:
    //getNumberOfTestsByIntervalDateOfTestRegistration(beginningDate, endDate)
    //getNumberOfTestsByIntervalDateOfDiagnosis(beginningDate, endDate)

    //eg.:
    //firstDayToAnalise d1d1/m1m1/y1y1 às 08:00:00:00
    //lastDay d2d2/m2m2/y2y2 às 19:59:59:59
    public int[] makeIntervalArray(Date firstDayToAnalyse, Date lastDayToAnalyse){ //começa às 8:00 e acaba às 20:00
        TestStore testStore = company.getTestStore();
        ArrayList<Integer> intervalArrayList = new ArrayList<>();
        int numRegistered;
        int numValidated;
        int intToKeep;
        int minToAdd = 30;
        Date date1 = firstDayToAnalyse;
        Date date2 = DateUtils.addMinutes(date1, minToAdd);
        do{
            numRegistered = testStore.getNumberOfTestsByIntervalDateOfTestRegistration(date1, date2);
            numValidated = testStore.getNumberOfTestsByIntervalDateOfDiagnosis(date1, date2);
            date1 = DateUtils.addMinutes(date1, minToAdd);
            date2 = DateUtils.addMinutes(date2, minToAdd);
            intToKeep = numRegistered-numValidated;
            intervalArrayList.add(intToKeep);
        } while (date2.before(lastDayToAnalyse));
        int[] intervalArray = new int[intervalArrayList.size()];
        for (int i = 0; i < intervalArray.length; i++) {
            intervalArray[i] = intervalArrayList.get(i).intValue();
        }
        return intervalArray;
    }
/*
    public int[] findWorstSubIntWithBenchmarkAlgorithm(Date firstDayToAnalyse, Date lastDayToAnalyse){
        int[] interval = makeIntervalArray(firstDayToAnalyse, lastDayToAnalyse);
        int[] worstSubInt = Sum.Max(interval);
        return worstSubInt;
    }

 */

    public int[] findWorstSubIntWithBruteforceAlgorithm(Date firstDayToAnalyse, Date lastDayToAnalyse){
        int[] interval = makeIntervalArray(firstDayToAnalyse, lastDayToAnalyse);
        //TO DO
        return null;
    }

}