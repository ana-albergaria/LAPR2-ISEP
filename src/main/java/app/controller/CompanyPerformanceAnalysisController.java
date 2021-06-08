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

    /*
     * Creates and returns an ArrayList with all the info to be considered when analysing the company performance
     * @return an ArrayList with all the info to be considered when analysing the company performance
     */
    /*
    public ArrayList<Integer> getTestOverview(){
        ArrayList<Integer> testOverview = new ArrayList<>();
        ClientStore clientStore = this.company.getClientStore();
        int clientInfo = clientStore.getNumClients();
        testOverview.add(clientInfo);
        TestStore testStore = this.company.getTestStore();
        testOverview.addAll(testStore.getTestsInfo());
        return  testOverview;
    }
    */

    //ALL TIME
    //GET NUM CLIENTS -> clientStore.getNumClients()
    //GET NUM TESTS WAITING FOR RESULTS ->
    //GET NUM TESTS WAITING FOR DIAGNOSIS ->
    //GET NUM TESTS PROCESSES IN THE LAB ->

    //EACH YEAR
    //GET NUM CLIENTS ->
    //GET NUM TESTS WAITING FOR RESULTS ->
    //GET NUM TESTS WAITING FOR DIAGNOSIS ->
    //GET NUM TESTS PROCESSES IN THE LAB ->

    //EACH MONTH
    //GET NUM CLIENTS ->
    //GET NUM TESTS WAITING FOR RESULTS ->
    //GET NUM TESTS WAITING FOR DIAGNOSIS ->
    //GET NUM TESTS PROCESSES IN THE LAB ->

    //EACH WEEK
    //GET NUM CLIENTS ->
    //GET NUM TESTS WAITING FOR RESULTS ->
    //GET NUM TESTS WAITING FOR DIAGNOSIS ->
    //GET NUM TESTS PROCESSES IN THE LAB ->

    //EACH DAY
    //GET NUM CLIENTS ->
    //GET NUM TESTS WAITING FOR RESULTS ->
    //GET NUM TESTS WAITING FOR DIAGNOSIS ->
    //GET NUM TESTS PROCESSES IN THE LAB ->

    //12 WORKING HOURS PER DAY
    //WORKING DAY IS FROM 8:00 TO 20:00
    //SUNDAY IS NOT A WORKING DAY

    /**
     * Creates an array with the difference between the number of new tests and the number of results available to the client during each half an hour period
     *
     * @param firstDayToAnalyse beginning date of the interval
     * @param lastDayToAnalyse end date of the interval
     * @return an array with the difference between the number of new tests and the number of results available to the client during each half an hour period
     */
    public int[] makeIntervalArray(Date firstDayToAnalyse, Date lastDayToAnalyse){
        TestStore testStore = company.getTestStore();
        ArrayList<Integer> intervalArrayList = new ArrayList<>();
        int numRegistered = 0;
        int numValidated = 0;
        int intToKeep = 0;
        int minToAdd = 30;
        Date date1 = firstDayToAnalyse;
        Date date2 = DateUtils.addMinutes(date1, minToAdd);
        do{
            if ((date1.getDay()!=0 && date2.getDay()!=0) && (date1.getHours()>=8 && date2.getHours()<20))
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
     * Finds the contiguous subsequence with maximum sum of an interval, through a benchmark algorithm
     *
     * @param firstDayToAnalyse beginning date of the interval
     * @param lastDayToAnalyse end date of the interval
     * @return the contiguous subsequence with maximum sum of an interval
     */
    /*public int[] findWorstSubIntWithBenchmarkAlgorithm(Date firstDayToAnalyse, Date lastDayToAnalyse){
        int[] interval = makeIntervalArray(firstDayToAnalyse, lastDayToAnalyse);
        int[] worstSubInt = Sum.Max(interval);
        return worstSubInt;
    }*/

    /*
     * Finds the contiguous subsequence with maximum sum of an interval, through a brute-force algorithm
     *
     * @param firstDayToAnalyse beginning date of the interval
     * @param lastDayToAnalyse end date of the interval
     * @return the contiguous subsequence with maximum sum of an interval
     */
    /*public int[] findWorstSubIntWithBruteforceAlgorithm(Date firstDayToAnalyse, Date lastDayToAnalyse){
        int[] interval = makeIntervalArray(firstDayToAnalyse, lastDayToAnalyse);
        int[] worstSubInt = SubMaxSum.findSubMaxSum(interval);
        return null;
    }*/



}