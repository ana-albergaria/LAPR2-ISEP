package app.controller;

import app.domain.interfaces.SubMaxSumAlgorithms;
import app.domain.model.Company;
import app.domain.shared.Constants;
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
     * Gets an array with the tests info on a specific day
     * @return array with the tests info on a specific day
     */
    public int[] getTestInfoDay(Date day){
        int[] testInfo = new int[3];
        TestStore testStore = new TestStore();
        testInfo[0]=testStore.getNumTestsWaitingForResultsDay(day);
        testInfo[1]=testStore.getNumTestsWaitingForDiagnosisDay(day);
        testInfo[2]=testStore.getNumTestsProcessedInLabDay(day);
        return testInfo;
    }

    /**
     * Gets an array with the tests info between two specific days
     * @return array with the tests info between two specific days
     */
    public int[] getTestInfoInterval(Date beginningDay, Date endingDay){
        int[] testInfo = new int[3];
        TestStore testStore = new TestStore();
        testInfo[0]=testStore.getNumTestsWaitingForResultsInterval(beginningDay,endingDay);
        testInfo[1]=testStore.getNumTestsWaitingForDiagnosisInterval(beginningDay,endingDay);
        testInfo[2]=testStore.getNumTestsProcessedInLabInterval(beginningDay,endingDay);
        return testInfo;
    }

    //12 WORKING HOURS PER DAY
    //WORKING DAY IS FROM 8:00 TO 20:00
    //SUNDAY IS NOT A WORKING DAY

    /**
     * Creates an array with the difference between the number of new tests and the number of results available to the client during each half an hour period
     *
     * @param beginningDay beginning date of the interval
     * @param endingDay end date of the interval
     * @return an array with the difference between the number of new tests and the number of results available to the client during each half an hour period
     */
    public int[] makeIntervalArray(Date beginningDay, Date endingDay){
        TestStore testStore = company.getTestStore();
        ArrayList<Integer> intervalArrayList = new ArrayList<>();
        int numRegistered = 0, numValidated = 0, intToKeep = 0, minToAdd = 30;
        Date date1 = beginningDay, date2 = DateUtils.addMinutes(date1, minToAdd);
        do{
            if ((date1.getDay()!=0 && date2.getDay()!=0) && (date1.getHours()>=8 && date2.getHours()<20))
                numRegistered = testStore.getNumberOfTestsByIntervalDateOfTestRegistration(date1, date2);
            numValidated = testStore.getNumberOfTestsByIntervalDateOfDiagnosis(date1, date2);
            date1 = DateUtils.addMinutes(date1, minToAdd);
            date2 = DateUtils.addMinutes(date2, minToAdd);
            intToKeep = numRegistered-numValidated;
            intervalArrayList.add(intToKeep);
        } while (date2.before(endingDay));
        int[] intervalArray = new int[intervalArrayList.size()];
        for (int i = 0; i < intervalArray.length; i++) {
            intervalArray[i] = intervalArrayList.get(i).intValue();
        }
        return intervalArray;
    }


    /**
     * Finds the contiguous subsequence with maximum sum of an interval, through the chosen algorithm
     *
     * @param beginningDay beginning date of the interval
     * @param endingDay end date of the interval
     * @param chosenAlgorithm the chosen algorithm
     * @return the contiguous subsequence with maximum sum of an interval
     */
    public int[] findWorstSubIntWithChosenAlgorithm(Date beginningDay, Date endingDay, int chosenAlgorithm) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        int[] interval = makeIntervalArray(beginningDay, endingDay);

        String algorithmClass = getAlgorithmClass(chosenAlgorithm);
        Class<?> oClass = Class.forName(algorithmClass);
        SubMaxSumAlgorithms subMaxSum = (SubMaxSumAlgorithms) oClass.newInstance();

        int[] worstSubInt = subMaxSum.findSubMaxSum(interval);

        return worstSubInt;
    }

    public String getAlgorithmClass(int chosenAlgorithm) {
        String algorithmClass;
        if(chosenAlgorithm == 1)
            algorithmClass = Constants.BENCHMARK_ALGORITHM_ADAPTER;
        else
            algorithmClass = Constants.BRUTEFORCE_ALGORITHM_ADAPTER;

        return algorithmClass;
    }

}