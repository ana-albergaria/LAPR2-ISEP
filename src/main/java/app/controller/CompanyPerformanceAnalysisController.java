package app.controller;

import app.domain.interfaces.SubMaxSumAlgorithms;
import app.domain.model.Company;
import app.domain.shared.Constants;
import app.domain.store.ClientStore;
import app.domain.store.TestStore;
import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
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

    //FIRST THE LB SELECTS DAY OR INTERVAL
    //SHOW ERROR MESSAGES!!! (CAN'T CHOOSE PRESENT OR FUTURE)
    //12 WORKING HOURS PER DAY
    //WORKING DAY IS FROM 8:00 TO 20:00 (endingDay will end at 19:59:59)
    //SUNDAY IS NOT A WORKING DAY

    /**
     * Gets an ArrayList with the tests info for the days of the interval
     * @return ArrayList with the tests info for the days of the interval
     */
    public ArrayList<int[]> getTestInfoPerDay(ArrayList<Date> days){
        ArrayList<int[]> testInfoPerDay = new ArrayList<>();
        int[] testInfo = new int[3];
        TestStore testStore = new TestStore();
        Date beginningDay;
        Date endingDay;
        for (Date day : days) {
            beginningDay = new Date(day.getYear(), day.getMonth(), day.getDate(), 8, 0, 0);
            endingDay = new Date(day.getYear(), day.getMonth(), day.getDate(), 19, 59, 59);
            testInfo[0] = testStore.getNumTestsWaitingForResultsDayOrInterval(beginningDay, endingDay);
            testInfo[1] = testStore.getNumTestsWaitingForDiagnosisDayOrInterval(beginningDay, endingDay);
            testInfo[2] = testStore.getNumTestsProcessedInLabDayOrInterval(beginningDay, endingDay);
            testInfoPerDay.add(testInfo);
        }
        return testInfoPerDay;
    }

    /**
     * Gets an ArrayList with the tests info for the weeks of the interval
     * @return ArrayList with the tests info for the weeks of the interval
     */
    public ArrayList<int[]> getTestInfoPerWeek(ArrayList<Date> days){ //WEEK: FROM MONDAY TO SATURDAY (NO WORK AT SUNDAY)
        ArrayList<int[]> testInfoPerWeek = new ArrayList<>();
        int[] testInfo = new int[3];
        TestStore testStore = new TestStore();
        ArrayList<ArrayList<Date>> weeks = new ArrayList<>();
        ArrayList<Date> week = new ArrayList<>(); //NO WORK AT SUNDAY
        for (Date date : days) {
            if (date.getDay() != 6) {
                week.add(date);
            } else {
                week.add(date);
                weeks.add(week);
                week.clear();
            }
        }
        Date beginningDay;
        Date endingDay;
        for (ArrayList<Date> singleWeek : weeks) {
            beginningDay = new Date(singleWeek.get(0).getYear(), singleWeek.get(0).getMonth(), singleWeek.get(0).getDate(), 8, 0, 0);
            endingDay = new Date(singleWeek.get(singleWeek.size()-1).getYear(), singleWeek.get(singleWeek.size()-1).getMonth(), singleWeek.get(singleWeek.size()-1).getDate(), 19, 59, 59);
            testInfo[0] = testStore.getNumTestsWaitingForResultsDayOrInterval(beginningDay, endingDay);
            testInfo[1] = testStore.getNumTestsWaitingForDiagnosisDayOrInterval(beginningDay, endingDay);
            testInfo[2] = testStore.getNumTestsProcessedInLabDayOrInterval(beginningDay, endingDay);
            testInfoPerWeek.add(testInfo);
        }
        return testInfoPerWeek;
    }

    /**
     * Gets an ArrayList with the tests info for the months of the interval
     * @return ArrayList with the tests info for the months of the interval
     */
    public ArrayList<int[]> getTestInfoPerMonth(ArrayList<Date> days){ //MONTH: FROM 1 TO END OF MONTH
        ArrayList<int[]> testInfoPerMonth = new ArrayList<>();
        int[] testInfo = new int[3];
        TestStore testStore = new TestStore();
        ArrayList<ArrayList<Date>> months = new ArrayList<>();
        ArrayList<Date> month = new ArrayList<>();
        for (Date date : days) {
            if (date.getMonth()==Calendar.JANUARY || date.getMonth()==Calendar.MARCH || date.getMonth()==Calendar.MAY ||
                    date.getMonth()==Calendar.JULY || date.getMonth()==Calendar.AUGUST || date.getMonth()==Calendar.OCTOBER || date.getMonth()==Calendar.DECEMBER) {
                if (date.getDate() != 31) {
                    month.add(date);
                } else {
                    month.add(date);
                    months.add(month);
                    month.clear();
                }
            } else if (date.getMonth()==Calendar.APRIL || date.getMonth()==Calendar.JUNE || date.getMonth()==Calendar.SEPTEMBER || date.getMonth()==Calendar.NOVEMBER) {
                if (date.getDate() != 30) {
                    month.add(date);
                } else {
                    month.add(date);
                    months.add(month);
                    month.clear();
                }
            }else if (date.getMonth()==Calendar.FEBRUARY && (date.getYear()%400 == 0) || ((date.getYear()%100) != 0 && (date.getYear()%4 == 0))) {
                if (date.getDate() != 29) {
                    month.add(date);
                } else {
                    month.add(date);
                    months.add(month);
                    month.clear();
                }
            } else {
                if (date.getDate() != 28) {
                    month.add(date);
                } else {
                    month.add(date);
                    months.add(month);
                    month.clear();
                }
            }
        }
        Date beginningDay;
        Date endingDay;
        for (ArrayList<Date> singleMonth : months) {
            beginningDay = new Date(singleMonth.get(0).getYear(), singleMonth.get(0).getMonth(), singleMonth.get(0).getDate(), 8, 0, 0);
            endingDay = new Date(singleMonth.get(singleMonth.size()-1).getYear(), singleMonth.get(singleMonth.size()-1).getMonth(), singleMonth.get(singleMonth.size()-1).getDate(), 19, 59, 59);
            testInfo[0] = testStore.getNumTestsWaitingForResultsDayOrInterval(beginningDay, endingDay);
            testInfo[1] = testStore.getNumTestsWaitingForDiagnosisDayOrInterval(beginningDay, endingDay);
            testInfo[2] = testStore.getNumTestsProcessedInLabDayOrInterval(beginningDay, endingDay);
            testInfoPerMonth.add(testInfo);
        }
        return testInfoPerMonth;
    }

    /**
     * Gets an ArrayList with the tests info for the years of the interval
     * @return ArrayList with the tests info for the years of the interval
     */
    public ArrayList<int[]> getTestInfoPerYear(ArrayList<Date> days){ //YEAR: FROM JAN 1 TO DEC 31
        ArrayList<int[]> testInfoPerYear = new ArrayList<>();
        int[] testInfo = new int[3];
        TestStore testStore = new TestStore();
        ArrayList<ArrayList<Date>> years = new ArrayList<>();
        ArrayList<Date> year = new ArrayList<>();
        for (Date date : days) {
            if (!(date.getMonth()==Calendar.DECEMBER && date.getDate()==31)) {
                year.add(date);
            } else {
                year.add(date);
                years.add(year);
                year.clear();
                }
        }
        Date beginningDay;
        Date endingDay;
        for(ArrayList<Date> singleYear : years) {
            beginningDay = new Date(singleYear.get(0).getYear(), singleYear.get(0).getMonth(), singleYear.get(0).getDate(), 8, 0, 0);
            endingDay = new Date(singleYear.get(singleYear.size()-1).getYear(), singleYear.get(singleYear.size()-1).getMonth(), singleYear.get(singleYear.size()-1).getDate(), 19, 59, 59);
            testInfo[0] = testStore.getNumTestsWaitingForResultsDayOrInterval(beginningDay, endingDay);
            testInfo[1] = testStore.getNumTestsWaitingForDiagnosisDayOrInterval(beginningDay, endingDay);
            testInfo[2] = testStore.getNumTestsProcessedInLabDayOrInterval(beginningDay, endingDay);
            testInfoPerYear.add(testInfo);
        }
        return testInfoPerYear;
    }

    /**
     * Creates an ArrayList with all the days of the interval
     * @param beginningDay beginning date of the interval
     * @param endingDay end date of the interval
     * @return an ArrayList with all the days of the interval
     */
    public ArrayList<Date> getDays(Date beginningDay, Date endingDay){ //EX: 14/01/2020 AT 08:00:00 - 16-02-2020 AT 19:59:59
        ArrayList<Date> days = new ArrayList<>();
        Date day = beginningDay;
        Date end = new Date(endingDay.getYear(), endingDay.getMonth(), endingDay.getDate(), 8,0,0);
        do {
            if (day.getDay()!=0) //NO WORK ON SUNDAYS
                days.add(day);
            day = DateUtils.addDays(day, 1);
        } while (day.before(end));
        return days;
    }

    /**
     * Creates an array with the difference between the number of new tests and the number of results available to the client during each half an hour period
     *
     * @param days days of the interval
     * @return an array with the difference between the number of new tests and the number of results available to the client during each half an hour period
     */
    public int[] makeIntervalArray(ArrayList<Date> days){ //EX: 14/01/2020 AT 08:00:00 - 16-02-2020 AT 19:59:59
        TestStore testStore = company.getTestStore();
        ArrayList<Integer> intervalArrayList = new ArrayList<>();
        int numRegistered = 0, numValidated = 0, intToKeep = 0, minToAdd = 30;
        Date date1 = days.get(0), date2 = DateUtils.addMinutes(date1, minToAdd);
        Date finish = new Date(days.get(days.size()-1).getYear(), days.get(days.size()-1).getMonth(), days.get(days.size()-1).getDate(), 20,0,0);
        Date endDay = date1;
        do{
            if (date1.getHours()>=8 && date2.getHours()<20) {
                numRegistered = testStore.getNumberOfTestsByIntervalDateOfTestRegistration(date1, endDay);
                numValidated = testStore.getNumberOfTestsByIntervalDateOfDiagnosis(date1, endDay);
                intToKeep = numRegistered - numValidated;
                intervalArrayList.add(intToKeep);
            } else if (date2.getHours()==20 && date2.getMinutes()==0) {
                numRegistered = testStore.getNumberOfTestsByIntervalDateOfTestRegistration(date1, endDay);
                numValidated = testStore.getNumberOfTestsByIntervalDateOfDiagnosis(date1, endDay);
                intToKeep = numRegistered - numValidated;
                intervalArrayList.add(intToKeep);
            }
            date1 = DateUtils.addMinutes(date1, minToAdd);
            date2 = DateUtils.addMinutes(date2, minToAdd);
            endDay = date2;
            endDay.setHours(19);
            endDay.setMinutes(59);
            endDay.setSeconds(59);
        } while (!(date2.equals(finish)));
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
        int[] interval = makeIntervalArray(getDays(beginningDay, endingDay)); //EX: 14/01/2020 AT 08:00:00 - 16-02-2020 AT 19:59:59

        String algorithmClass = getChosenAlgorithmAdapter(chosenAlgorithm);
        Class<?> oClass = Class.forName(algorithmClass);
        SubMaxSumAlgorithms subMaxSumAlgorithm = (SubMaxSumAlgorithms) oClass.newInstance();

        int[] worstSubInt = subMaxSumAlgorithm.findSubMaxSum(interval);

        return worstSubInt;
    }

    /**
     *
     *
     * @param chosenAlgorithm the chosen algorithm
     * @return the chosen algorithm adapter
     */
    public String getChosenAlgorithmAdapter(int chosenAlgorithm) {
        String chosenAlgorithmAdapter;
        if(chosenAlgorithm == 1)
            chosenAlgorithmAdapter = Constants.BENCHMARK_ALGORITHM_ADAPTER;
        else
            chosenAlgorithmAdapter = Constants.BRUTEFORCE_ALGORITHM_ADAPTER;

        return chosenAlgorithmAdapter;
    }

}