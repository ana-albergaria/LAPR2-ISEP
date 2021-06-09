package app.controller;

import app.domain.model.BenchmarkAlgorithm;
import app.domain.model.BruteForceAlgorithm;
import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.store.ClientStore;
import app.domain.store.TestStore;
import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.Date;

/**
 * Controller class for analysing the company performance
 *
 * @author Marta Ribeiro 1201592
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
     * Gets the number of clients in the system.
     * @return the number of clients in the system.
     */
    public int getNumClients() {
        ClientStore clientStore = new ClientStore();
        int numClients = clientStore.getClients().size();
        return numClients;
    }

    //THE VERIFICATION OF THE NUMBER OF DAYS BETWEEN THE TWO DATES IS MADE IN THE UI
    //FIRST THE LB SELECTS DAY, WEEK, MONTH OR YEAR
    //SHOW ERROR MESSAGES!!!

    /**
     * Gets the number of tests that were waiting for results on a specific day
     * @return number of tests that were waiting for results on a specific day
     */
    public int getNumTestsWaitingForResultsDay(Date day){
        TestStore testStore = new TestStore();
        int numTestsWaitingForResultsDay = testStore.getNumTestsWaitingForResultsDay(day);
        return numTestsWaitingForResultsDay;
    }

    /**
     * Gets the number of tests that were waiting for results between two specific days
     * @return number of tests that were waiting for results between two specific days
     */
    public int getNumTestsWaitingForResultsInterval(Date beginningDay, Date endingDay){
        TestStore testStore = new TestStore();
        int numTestsWaitingForResultsInterval = testStore.getNumTestsWaitingForResultsInterval(beginningDay, endingDay);
        return numTestsWaitingForResultsInterval;
    }

    /**
     * Gets the number of tests that were waiting for diagnosis on a specific day
     * @return number of tests that were waiting for diagnosis on a specific day
     */
    public int getNumTestsWaitingForDiagnosisDay(Date day){
        TestStore testStore = new TestStore();
        int numTestsWaitingForDiagnosisDay = testStore.getNumTestsWaitingForDiagnosisDay(day);
        return numTestsWaitingForDiagnosisDay;
    }

    /**
     * Gets the number of tests that were waiting for diagnosis between two specific days
     * @return number of tests that were waiting for diagnosis between two specific days
     */
    public int getNumTestsWaitingForDiagnosisInterval(Date beginningDay, Date endingDay){
        TestStore testStore = new TestStore();
        int numTestsWaitingForDiagnosisInterval = testStore.getNumTestsWaitingForDiagnosisInterval(beginningDay, endingDay);
        return numTestsWaitingForDiagnosisInterval;
    }

    //WHICH ONES ARE THE PROCESSED?

    /**
     * Gets the number of tests processed in the lab on a specific day
     * @return number of tests processed in the lab on a specific day
     */
    public int getNumTestsProcessedInLabDay(Date day){
        //TO DO
        return 0;
    }

    /**
     * Gets the number of tests processed in the lab between two specific days
     * @return number of tests processed in the lab between two specific days
     */
    public int getNumTestsProcessedInLabInterval(Date beginningDay, Date endingDay){
        //TO DO
        return 0;
    }

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

    //IS THIS THE RIGHT WAY TO DO IT??? (SEE BELOW)

    BenchmarkAlgorithm bma = new BenchmarkAlgorithm();
    BruteForceAlgorithm bfa = new BruteForceAlgorithm();

    /**
     * Finds the contiguous subsequence with maximum sum of an interval, through the chosen algorithm
     *
     * @param firstDayToAnalyse beginning date of the interval
     * @param lastDayToAnalyse end date of the interval
     * @param chosenAlgorithm the chosen algorithm
     * @return the contiguous subsequence with maximum sum of an interval
     */
    public int[] findWorstSubIntWithChosenAlgorithm(Date firstDayToAnalyse, Date lastDayToAnalyse, boolean chosenAlgorithm){
        int[] interval = makeIntervalArray(firstDayToAnalyse, lastDayToAnalyse);
        int[] worstSubInt;
        if (chosenAlgorithm){
            worstSubInt = bma.findSubMaxSum(interval);
        } else {
            worstSubInt = bfa.findSubMaxSum(interval);
        }
        return worstSubInt;
    }

}