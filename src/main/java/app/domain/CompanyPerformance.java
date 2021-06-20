package app.domain;

import app.controller.ImportTestController;
import app.domain.interfaces.SubMaxSumAlgorithms;
import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.shared.Constants;
import app.domain.store.TestStore;
import app.mappers.dto.TestFileDTO;
import app.ui.console.utils.TestFileUtils;
import net.sourceforge.barbecue.BarcodeException;
import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Represents the company performance through:
 *
 *
 * @author Marta Ribeiro 1201592
 */
public class CompanyPerformance {

    /**
     * The company associated with the Company Performance.
     */
    private Company company;

    /**
     * Set the company associated with the Company Performance.
     *
     * @param company the company to associate with the Company Performance
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * The beginning date of the interval.
     */
    private Date beginningDate;

    /**
     * The ending date of the interval.
     */
    private Date endingDate;

    /**
     * The chosen algorithm.
     */
    private String chosenAlg;

    /**
     * The number of clients.
     */
    private int clientsNum;

    /**
     * The number os processed tests.
     */
    private int processTestsNum;

    /**
     * The tests info for the days of the interval.
     */
    private ArrayList<int[]> testInfoDay;

    /**
     * The tests info for the weeks of the interval.
     */
    private ArrayList<int[]> testInfoWeek;

    /**
     * The tests info for the months of the interval.
     */
    private ArrayList<int[]> testInfoMonth;

    /**
     * The tests info for the years of the interval.
     */
    private ArrayList<int[]> testInfoYear;

    /**
     * The beginning and end of the contiguous subsequence with maximum sum.
     */
    private Date[] worstSubInt;

    public CompanyPerformance(Date beginningDate, Date endingDate, String chosenAlg, Company company) {
        this.company = company;
        this.beginningDate=beginningDate;
        this.endingDate=endingDate;
        this.chosenAlg=chosenAlg;
        this.clientsNum=getClientsInfoPerInterval(getDays(beginningDate,endingDate));
        this.processTestsNum=getNumTestsProcessedInterval(getDays(beginningDate,endingDate));
        this.testInfoDay=getTestInfoPerDay(getDays(beginningDate,endingDate));
        if (beginningDate.getYear()!=endingDate.getYear() || beginningDate.getMonth()!=endingDate.getMonth() || beginningDate.getDate()!=endingDate.getDate()) {
            this.testInfoWeek = getTestInfoPerWeek(getDays(beginningDate, endingDate));
            this.testInfoMonth = getTestInfoPerMonth(getDays(beginningDate, endingDate));
            this.testInfoYear = getTestInfoPerYear(getDays(beginningDate, endingDate));
        }
        try {
            this.worstSubInt=findWorstSubIntWithChosenAlgorithm(getDays(beginningDate,endingDate),chosenAlg);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the number of clients.
     * @return number of clients
     */
    public int getClientsNum() {
        return clientsNum;
    }

    /**
     * Returns the number os processed tests.
     * @return number of processed tests
     */
    public int getProcessTestsNum() {
        return processTestsNum;
    }

    /**
     * Returns the tests info for the days of the interval.
     * @return tests info
     */
    public ArrayList<int[]> getTestInfoDay() {
        return testInfoDay;
    }

    /**
     * Returns the tests info for the weeks of the interval.
     * @return tests info
     */
    public ArrayList<int[]> getTestInfoWeek() {
        return testInfoWeek;
    }

    /**
     * Returns the tests info for the months of the interval.
     * @return tests info
     */
    public ArrayList<int[]> getTestInfoMonth() {
        return testInfoMonth;
    }

    /**
     * Returns the tests info for the years of the interval.
     * @return tests info
     */
    public ArrayList<int[]> getTestInfoYear() {
        return testInfoYear;
    }

    /**
     * Returns the beginning and end of the contiguous subsequence with maximum sum.
     * @return beginning and end of the contiguous subsequence with maximum sum
     */
    public Date[] getWorstSubInt() {
        return worstSubInt;
    }

    /**
     * Gets the number of clients for an interval
     * @return the number of clients for an interval
     */
    public int getClientsInfoPerInterval(ArrayList<Date> days){
        Date endingDay = new Date(days.get(days.size()-1).getYear(), days.get(days.size()-1).getMonth(), days.get(days.size()-1).getDate(), 20, 0, 0);
        TestStore testStore = this.company.getTestStore();
        ArrayList<String> clientEmails = new ArrayList<>();
        String clientEmail;
        boolean repeated = false;
        String notEmp = "";
        clientEmails.add(notEmp);
        for (Test test : testStore.getTests()){
            if (test.getDateOfTestRegistration().before(endingDay)){
                clientEmail=test.getClient().getEmail();
                for (int i = 0; i < clientEmails.size(); i++) {
                    if (clientEmail.equals(clientEmails.get(i))){
                        repeated=true;
                    }
                }
                if (!repeated){
                    clientEmails.add(clientEmail);
                }
                repeated=false;
            }
        }
        return clientEmails.size()-1;
    }

    /**
     * Gets the number of processed tests for an interval
     * @return the number of processed tests for an interval
     */
    public int getNumTestsProcessedInterval(ArrayList<Date> days){
        Date endingDay = new Date(days.get(days.size()-1).getYear(), days.get(days.size()-1).getMonth(), days.get(days.size()-1).getDate(), 20, 0, 0);
        TestStore testStore = this.company.getTestStore();
        int quant=0;
        for (Test test : testStore.getTests()){
            if (test.getDateOfValidation()!=null && test.getDateOfValidation().before(endingDay)){
                quant++;
            }
        }
        return quant;
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
        int[] testInfo = new int[2];
        TestStore testStore = this.company.getTestStore();
        Date beginningDay;
        Date endingDay;
        for (Date day : days) {
            beginningDay = new Date(day.getYear(), day.getMonth(), day.getDate(), 8, 0, 0);
            endingDay = new Date(day.getYear(), day.getMonth(), day.getDate(), 19, 59, 59);
            testInfo[0] = testStore.getNumTestsWaitingForResultsDayOrInterval(beginningDay, endingDay);
            testInfo[1] = testStore.getNumTestsWaitingForDiagnosisDayOrInterval(beginningDay, endingDay);
            testInfoPerDay.add(testInfo);
        }
        return testInfoPerDay;
    }

    /**
     * Gets an ArrayList with the tests info for the weeks of the interval
     * @return ArrayList with the tests info for the weeks of the interval
     */
    public ArrayList<int[]> getTestInfoPerWeek(ArrayList<Date> days){ //WEEK: FROM MONDAY TO SATURDAY (NO WORK ON SUNDAY)
        ArrayList<int[]> testInfoPerWeek = new ArrayList<>();
        int[] testInfo = new int[2];
        TestStore testStore = this.company.getTestStore();
        ArrayList<ArrayList<Date>> weeks = new ArrayList<>();
        ArrayList<Date> week = new ArrayList<>(); //NO WORK ON SUNDAY
        if (days.get(days.size()-1).getDay()!=6){
            Date lastSat = days.get(days.size()-1);
            do {
                lastSat = DateUtils.addDays(lastSat,-1);
            }while (lastSat.getDay()!=6);
            if (lastSat.before(days.get(0))){
                for (Date date : days){
                    week.add(date);
                }
                weeks.add((ArrayList<Date>) week.clone());
                week.clear();
            } else {
                for (Date date : days) {
                    if (date.getDay() != 0) {
                        week.add(date);
                    }
                    if (date.getDay() == 6) {
                        weeks.add((ArrayList<Date>) week.clone());
                        week.clear();
                    } else if (date==days.get(days.size()-1)) {
                        weeks.add((ArrayList<Date>) week.clone());
                        week.clear();
                    }
                }
            }
        } else {
            for (Date date : days) {
                if (date.getDay() != 0) {
                    week.add(date);
                }
                if (date.getDay() == 6) {
                    weeks.add((ArrayList<Date>) week.clone());
                    week.clear();
                }
            }
        }
        Date beginningDay;
        Date endingDay;
        for (ArrayList<Date> singleWeek : weeks) {
            beginningDay = new Date(singleWeek.get(0).getYear(), singleWeek.get(0).getMonth(), singleWeek.get(0).getDate(), 8, 0, 0);
            endingDay = new Date(singleWeek.get(singleWeek.size()-1).getYear(), singleWeek.get(singleWeek.size()-1).getMonth(), singleWeek.get(singleWeek.size()-1).getDate(), 19, 59, 59);
            testInfo[0] = testStore.getNumTestsWaitingForResultsDayOrInterval(beginningDay, endingDay);
            testInfo[1] = testStore.getNumTestsWaitingForDiagnosisDayOrInterval(beginningDay, endingDay);
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
        int[] testInfo = new int[2];
        TestStore testStore = this.company.getTestStore();
        ArrayList<ArrayList<Date>> months = new ArrayList<>();
        ArrayList<Date> month = new ArrayList<>();
        if (days.get(days.size()-1).getDate()!=31 &&
                (days.get(days.size()-1).getMonth() == Calendar.JANUARY || days.get(days.size()-1).getMonth() == Calendar.MARCH || days.get(days.size()-1).getMonth() == Calendar.MAY ||
                        days.get(days.size()-1).getMonth() == Calendar.JULY || days.get(days.size()-1).getMonth() == Calendar.AUGUST || days.get(days.size()-1).getMonth() == Calendar.OCTOBER || days.get(days.size()-1).getMonth() == Calendar.DECEMBER)){
            Date lastDayMonth = days.get(days.size()-1);
            do{
                lastDayMonth = DateUtils.addDays(lastDayMonth,-1);
            }while (lastDayMonth.getDate()!=31);
            if (lastDayMonth.before(days.get(0))){
                for (Date date : days){
                    month.add(date);
                }
                months.add((ArrayList<Date>) month.clone());
                month.clear();
            } else {
                for (Date date : days) {
                    month.add(date);
                    if (date.getDate()==31){
                        months.add((ArrayList<Date>) month.clone());
                        month.clear();
                    } else if (date==days.get(days.size()-1)){
                        months.add((ArrayList<Date>) month.clone());
                        month.clear();
                    }
                }
            }
        //FAZER TAMBÉM ELSE PARA OS OUTROS MESES
        }else {
            for (Date date : days) {
                if (date.getMonth() == Calendar.JANUARY || date.getMonth() == Calendar.MARCH || date.getMonth() == Calendar.MAY ||
                        date.getMonth() == Calendar.JULY || date.getMonth() == Calendar.AUGUST || date.getMonth() == Calendar.OCTOBER || date.getMonth() == Calendar.DECEMBER) {
                    if (date.getDay() != 0) {
                        month.add(date);
                    }
                    if (date.getDate() == 31) {
                        months.add((ArrayList<Date>) month.clone());
                        month.clear();
                    }
                } else if (date.getMonth() == Calendar.APRIL || date.getMonth() == Calendar.JUNE || date.getMonth() == Calendar.SEPTEMBER || date.getMonth() == Calendar.NOVEMBER) {
                    if (date.getDay() != 0) {
                        month.add(date);
                    }
                    if (date.getDate() == 30) {
                        months.add((ArrayList<Date>) month.clone());
                        month.clear();
                    }
                } else if (date.getMonth() == Calendar.FEBRUARY && (date.getYear() % 400 == 0) || ((date.getYear() % 100) != 0 && (date.getYear() % 4 == 0))) {
                    if (date.getDay() != 0) {
                        month.add(date);
                    }
                    if (date.getDate() == 29) {
                        months.add((ArrayList<Date>) month.clone());
                        month.clear();
                    }
                } else {
                    if (date.getDay() != 0) {
                        month.add(date);
                    }
                    if (date.getDate() == 28) {
                        months.add((ArrayList<Date>) month.clone());
                        month.clear();
                    }
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
        int[] testInfo = new int[2];
        TestStore testStore = this.company.getTestStore();
        ArrayList<ArrayList<Date>> years = new ArrayList<>();
        ArrayList<Date> year = new ArrayList<>();
        if (days.get(days.size()-1).getMonth()!=Calendar.DECEMBER && days.get(days.size()-1).getDate()!=31){
            //TO DO (based on the week one)
        } else {
            for (Date date : days) {
                if (date.getDay() != 0) {
                    year.add(date);
                }
                if ((date.getMonth() == Calendar.DECEMBER && date.getDate() == 31)) {
                    years.add(year);
                    year.clear();
                }
            }
        }
        Date beginningDay;
        Date endingDay;
        for(ArrayList<Date> singleYear : years) {
            beginningDay = new Date(singleYear.get(0).getYear(), singleYear.get(0).getMonth(), singleYear.get(0).getDate(), 8, 0, 0);
            endingDay = new Date(singleYear.get(singleYear.size()-1).getYear(), singleYear.get(singleYear.size()-1).getMonth(), singleYear.get(singleYear.size()-1).getDate(), 19, 59, 59);
            testInfo[0] = testStore.getNumTestsWaitingForResultsDayOrInterval(beginningDay, endingDay);
            testInfo[1] = testStore.getNumTestsWaitingForDiagnosisDayOrInterval(beginningDay, endingDay);
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
        if (day.getYear()==end.getYear() && day.getMonth()==end.getMonth() && day.getDate()==end.getDate()) {
            days.add(endingDay);
        } else {
            if (end.getDay() != 0) {
                do {
                    if (day.getDay() != 0 && (day.getYear() != end.getYear() || day.getMonth() != end.getMonth() || day.getDate() != end.getDate())) //NO WORK ON SUNDAYS
                        days.add(day);
                    day = DateUtils.addDays(day, 1);
                } while (day.before(end));
                end.setHours(19);
                end.setMinutes(59);
                end.setSeconds(59);
                days.add(end);
            } else {
                end = DateUtils.addDays(day, -1);
                do {
                    if (day.getDay() != 0 && (day.getYear() != end.getYear() || day.getMonth() != end.getMonth() || day.getDate() != end.getDate())) //NO WORK ON SUNDAYS
                        days.add(day);
                    day = DateUtils.addDays(day, 1);
                } while (day.before(end));
                end.setHours(19);
                end.setMinutes(59);
                end.setSeconds(59);
                days.add(end);
            }
        }
        return days;
    }

    /**
     * Creates an array with the difference between the number of new tests and the number of results available to the client during each half an hour period
     *
     * @param days days of the interval
     * @return an array with the difference between the number of new tests and the number of results available to the client during each half an hour period
     */
    public int[] makeIntervalArray(ArrayList<Date> days){ //EX: 14/01/2020 AT 08:00:00 - 16-02-2020 AT 19:59:59
        TestStore testStore = this.company.getTestStore();
        ArrayList<Integer> intervalArrayList = new ArrayList<>();
        int numRegistered = 0, numValidated = 0, intToKeep = 0;
        Date finalDate = new Date(days.get(days.size()-1).getYear(),days.get(days.size()-1).getMonth(),days.get(days.size()-1).getDate(),8,0,0);
        days.set(days.size()-1,finalDate);
        for (Date day : days){
            System.out.println("day " + day.toString());
            Date date1 = day;
            Date date2 = DateUtils.addMinutes(date1, 30);
            Date finish = new Date(day.getYear(), day.getMonth(), day.getDate(), 20,0,1);
            Date endDay = (Date)date2.clone();
            endDay = DateUtils.addSeconds(endDay,-1);
            do{
                System.out.println("date1 " + date1);
                System.out.println("date2 " + date2);
                System.out.println("date3 " + endDay);
                if (date1.getHours()>=8 && date2.getHours()<20) {
                    numRegistered = testStore.getNumberOfTestsByIntervalDateOfTestRegistration(date1, date2);
                    numValidated = testStore.getNumberOfTestsByIntervalDateOfDiagnosis(date1, date2);
                    intToKeep = numRegistered - numValidated;
                    intervalArrayList.add(intToKeep);
                } else if ((date2.getHours()==20 && date2.getMinutes()==0)) {
                    numRegistered = testStore.getNumberOfTestsByIntervalDateOfTestRegistration(date1, endDay);
                    numValidated = testStore.getNumberOfTestsByIntervalDateOfDiagnosis(date1, endDay);
                    intToKeep = numRegistered - numValidated;
                    intervalArrayList.add(intToKeep);
                }
                date1 = DateUtils.addMinutes(date1, 30); //19:30
                date2 = DateUtils.addMinutes(date2, 30); //20:00
                endDay = DateUtils.addMinutes(endDay, 30); //19:59:59
            } while (date2.before(finish));
        }
        int[] intervalArray = new int[intervalArrayList.size()];
        for (int i = 0; i < intervalArray.length; i++) {
            intervalArray[i] = intervalArrayList.get(i).intValue();
        }
        return intervalArray;
    }

    /**
     * Finds the beginning and the ending dates of the contiguous subsequence with maximum sum of an interval, through the chosen algorithm
     *
     * @param days days of the interval
     * @param chosenAlgorithm the chosen algorithm
     * @return the beginning and the ending dates of the contiguous subsequence with maximum sum
     * @throws ClassNotFoundException if the class name of the external API is not found
     * @throws InstantiationException if the class object of the external API cannot be instantiated
     * @throws IllegalAccessException if there's a method invoked does not have access to the class representing the API
     */
    public Date[] findWorstSubIntWithChosenAlgorithm(ArrayList<Date> days, String chosenAlgorithm) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        int[] interval = makeIntervalArray(days); //EX: 14/01/2020 AT 08:00:00 - 16-02-2020 AT 19:59:59
        String algorithmClass = getChosenAlgorithmAdapter(chosenAlgorithm);
        Class<?> oClass = Class.forName(algorithmClass);
        SubMaxSumAlgorithms subMaxSumAlgorithm = (SubMaxSumAlgorithms) oClass.newInstance();
        int[] worstSubInt = subMaxSumAlgorithm.findSubMaxSum(interval);
        int num=0, ind, ref=0;
        Date[] limits = new Date[2];
        System.out.println("WORSTSUBINT.LENGTH: " + worstSubInt.length);
        if (worstSubInt.length!=0) { //é 0 quando a company é igualmente eficiente ao longo do interval (ex: todos os valores são 0)
            for (int j = 0; j < interval.length; j++) {
                System.out.println("VALOR J: " + j);
                if (num!=worstSubInt.length) {
                    if (interval[j] == worstSubInt[0]) {
                        ind = j;
                        System.out.println("VALOR IND=J: " + ind);
                        for (int value : worstSubInt) {
                            System.out.println("VALOR VALUE: " + value);
                            if (value == interval[ind]) {
                                System.out.println("VALOR INTERVAL[IND]: " + interval[ind]);
                                num++;
                                System.out.println("VALOR NUM: " + num);
                            }
                            ind++;
                            System.out.println("VALOR IND: " + ind);
                        }
                        System.out.println("VALOR NUM IGUALDADE: " + num);
                        System.out.println("VALOR WORSTSUBINT.LENGTH: " + worstSubInt.length);
                        if (num == worstSubInt.length) {
                            ref = j;
                            System.out.println("VALOR REF: " + ref);
                        } else {
                            num = 0;
                        }
                    }
                }
            }
            int startIndex = ref;
            int endIndex = startIndex + worstSubInt.length - 1;
            Date first = days.get(0);
            Date last = DateUtils.addMinutes(first, 30);
            Date resultFor0 = first;
            Date resultFor1 = last;
            int quant = 0;
            if (quant != startIndex) {
                System.out.println("QUANT: " + quant);
                System.out.println("STARTINDEX: " + endIndex);
                for (Date day : days) {
                    System.out.println("DAY: " + day);
                    first = day;
                    last = DateUtils.addMinutes(first, 30);
                    do {
                        System.out.println("FIRST: " + first);
                        System.out.println("LAST: " + last);
                        if (first.getHours() >= 8 && last.getHours() < 20) {
                            first = DateUtils.addMinutes(first, 30);
                            last = DateUtils.addMinutes(last, 30);
                            quant++;
                            if (quant==startIndex){
                                resultFor0=first;
                            }
                        } else if (last.getHours()==20 && last.getMinutes()==0){
                            quant++;
                            if (quant==startIndex){
                                resultFor0=first;
                            }
                        }
                        System.out.println("ATT QUANT: " + quant);
                    } while (last.getHours()!=20 && last.getMinutes()!=0);
                }
                System.out.println("6");
            }
            limits[0] = resultFor0;
            quant = 0;
            if (quant != endIndex) {
                System.out.println("QUANT: " + quant);
                System.out.println("ENDINDEX: " + endIndex);
                for (Date day : days) {
                    System.out.println("DAY: " + day);
                    first = day;
                    last = DateUtils.addMinutes(first, 30);
                    do {
                        System.out.println("FIRST: " + first);
                        System.out.println("LAST: " + last);
                        if (first.getHours() >= 8 && last.getHours() < 20) {
                            first = DateUtils.addMinutes(first, 30);
                            last = DateUtils.addMinutes(last, 30);
                            quant++;
                            if (quant==endIndex){
                                resultFor1=last;
                            }
                        } else if (last.getHours()==20 && last.getMinutes()==0){
                            quant++;
                            if (quant==endIndex){
                                resultFor1=last;
                            }
                        }
                        System.out.println("ATT QUANT: " + quant);
                    } while (last.getHours()!=20 && last.getMinutes()!=0);
                }
                System.out.println("7");
            }
            limits[1] = resultFor1;
        }else{
            limits[0]=null;
            limits[1]=null;
        }
        return limits;
    }

    /**
     * Gets the chosen algorithm adapter.
     *
     * @param chosenAlgorithm the chosen algorithm
     * @return the chosen algorithm adapter
     */
    public String getChosenAlgorithmAdapter(String chosenAlgorithm) {
        String chosenAlgorithmAdapter;
        if(chosenAlgorithm.equals("Benchmark Algorithm"))
            chosenAlgorithmAdapter = Constants.BENCHMARK_ALGORITHM_ADAPTER;
        else
            chosenAlgorithmAdapter = Constants.BRUTEFORCE_ALGORITHM_ADAPTER;

        return chosenAlgorithmAdapter;
    }

}