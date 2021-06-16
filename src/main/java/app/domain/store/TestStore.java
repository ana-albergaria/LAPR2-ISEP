package app.domain.store;

import app.domain.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Store of tests
 *
 * @author SRC-Code-23
 */
public class TestStore {

    /**
     * List of all existing tests
     */
    private List<Test> testList = new ArrayList<>();

    /**
     * Method for creating an instance of Test
     *
     * @param nhsCode          National health system code of a given test
     * @param associatedClient client object which has solicited a test
     * @param testType         Type of test to be conduted
     * @param parameters       List of parameters to be measured of a given test
     */
    public Test createTest(String nhsCode, Client associatedClient, TestType testType, List<Parameter> parameters, ClinicalAnalysisLaboratory cal) {
        return new Test(nhsCode, associatedClient, testType, parameters, cal);
    }

    /**
     * Method for creating an instance of Test
     *
     * @param nhsCode          National health system code of a given test
     * @param associatedClient client object which has solicited a test
     * @param testType         Type of test to be conduted
     * @param parameters       List of parameters to be measured of a given test
     */
    public Test createTest(String nhsCode, Client associatedClient, TestType testType, List<Parameter> parameters,List<Double> paramsResults,
                           ClinicalAnalysisLaboratory cal, Date testRegDate, Date testChemDate, Date testDiagnosisDate, Date testValidationDate) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        return new Test(nhsCode, associatedClient, testType, parameters, paramsResults, cal, testRegDate, testChemDate, testDiagnosisDate, testValidationDate);
    }

    /**
     * Validation of test instance relative to list of tests. checking for null and duplicity
     *
     * @param test test to be validated
     * @return true for success and false for fail
     */
    public boolean validateTest(Test test) {
        if (test == null)
            return false;
        return !this.testList.contains(test);
    }

    /**
     * Save of test instance inside the list of test store, checking for validation before saving.
     *
     * @param test test to be saved to the list
     * @return true for success and false for fail
     */
    public boolean saveTest(Test test) {
        if (!validateTest(test))
            return false;
        return this.testList.add(test);
    }

    /**
     * Gets all tests stored in the store
     * @return list of tests
     */
    public List<Test> getTests() {
        return new ArrayList<>(testList);
    }

    /**
     * Gets the number of tests that were waiting for results on a specific day/interval
     * @return number of tests that were waiting for results on a specific day/interval
     */
    public int getNumTestsWaitingForResultsDayOrInterval(Date beginningDay, Date endingDay){ //endingDay vai ser as 19:59:59 do (domingo) sábado PARA NÃO PERTENCER
        int num = 0;
        Date date1, date2;
        for (Test test : testList) {
            date2 = test.getDateOfSamplesCollection();
            date1 = test.getDateOfChemicalAnalysis();
            if (date2!=null && date1==null)
                date1=new Date(10000,Calendar.JANUARY,1);
            if ((date2!=null && date1.after(beginningDay) && date1.before(endingDay)) //waiting in moment beginningDay
                    || (date2!=null && date1.equals(endingDay)) //waiting before endingDay
                    || (date2!=null && date2.before(endingDay) && date1.after(endingDay))) //waiting in moment endingDay and maybe before too
                num++;
        }
        return num;
    }

    /**
     * Gets the number of tests that were waiting for diagnosis on a specific day/interval
     * @return number of tests that were waiting for diagnosis on a specific day/interval
     */
    public int getNumTestsWaitingForDiagnosisDayOrInterval(Date beginningDay, Date endingDay){
        //number of tests that were waiting for the diagnosis on a specific day/interval
        //in case of a day, the difference between the beginningDay and the endingDay is in the hours
        int num = 0;
        Date date1, date2;
        for (Test test : testList) {
            date2 = test.getDateOfChemicalAnalysis();
            date1 = test.getDateOfDiagnosis();
            if (date2!=null && date1==null)
                date1=new Date(10000,Calendar.JANUARY,1);
            if ((date2!=null && date1.after(beginningDay) && date1.before(endingDay)) //waiting in moment beginningDay
                    || (date2!=null && date1.equals(endingDay)) //waiting before endingDay
                    || (date2!=null && date2.before(endingDay) && date1.after(endingDay))) //waiting in moment endingDay and maybe before too
                num++;
        }
        return num;
    }

    /**
     * Gets the number of tests processed in lab on a specific day/interval
     * @return number of tests processed in lab on a specific day/interval
     */
    public int getNumTestsProcessedInLabDayOrInterval(Date beginningDay, Date endingDay){
        //number of tests that became completed in the specific day/interval
        //in case of a day, the difference between the beginningDay and the endingDay is in the hours
        int num = 0;
        Date date1, date2, date3, date4, date5;
        for (Test test : testList) {
            date1 = test.getDateOfTestRegistration();
            date2 = test.getDateOfSamplesCollection();
            date3 = test.getDateOfChemicalAnalysis();
            date4 = test.getDateOfDiagnosis();
            date5 = test.getDateOfValidation();
            if (date1!=null && date2!=null && date3!=null && date4!=null && date5!=null &&
                    ((date4.after(beginningDay) && date4.before(endingDay)) || date4.equals(endingDay)))
                num++;
        }
        return num;
    }

    /**
     * gets the tests which are ready to be diagnosed
     * @return list of ready to diagnosis tests
     */
    public List<Test> getTestsReadyToDiagnose() {
        List<Test> listTestsReadyToDiagnose = new ArrayList<>();

        for (Test test : testList) {
            if (test.hasSamplesAnalysed() && (test.getDiagnosisReport() == null))
                listTestsReadyToDiagnose.add(test);
        }
        return listTestsReadyToDiagnose;
    }

    /**
     * Gets the tests by its code
     * @param code code of the test to be found
     * @return Found test
     */
    public Test getTestByCode(String code) {
        for (Test tst : getTestsReadyToDiagnose()) {
            if (tst.getCode().equalsIgnoreCase(code)) {
                return tst;
            }
        }
        throw new UnsupportedOperationException("Test not found in ready to diagnose list!");
    }

    public ArrayList<Test> getTestsByClient(Client client){
        ArrayList<Test> clientTests = new ArrayList<Test>();
        for (Test tst : testList){
            if (tst.getClient().equals(client)){
                clientTests.add(tst);
            }
        }
        return clientTests;
    }

    public List<Test> getValidatedTestsByClientTin(String tinNumber){
        List<Test> clientTests = new ArrayList<>();
        for (Test tst : testList){
            if (tst.isOfClient(tinNumber) && tst.isValidated()){
                clientTests.add(tst);
            }
        }
        return clientTests;
    }

    /**
     * Method for getting a test by it's code in the tests list stored in the testStore
     * @param code code of the test to be found
     * @return found Test object with given code
     */
    public Test getTestByCodeInTestList(String code) {
        for (Test test : testList) {
            if (test.getCode().equalsIgnoreCase(code))
                return test;
        }
        throw new UnsupportedOperationException("Test not found!");
    }

    /**
     * Gets the number of tests that were registered between the desired interval of time
     *
     * @param beginningDay the beginning date of the desired interval of time
     * @param endingDay the end date of the desired interval of time
     * @return the number of tests that were registered between the desired interval of time
     */
    public int getNumberOfTestsByIntervalDateOfTestRegistration(Date beginningDay, Date endingDay){
        int num = 0;
        for (Test test : testList) {
            if ((test.getDateOfTestRegistration().after(beginningDay) && test.getDateOfTestRegistration().before(endingDay))
            || (test.getDateOfTestRegistration().equals(beginningDay)) || (test.getDateOfTestRegistration().equals(endingDay)))
                num++;
        }
        return num;
    }

    /**
     * Gets the number of tests that were validated between the desired interval of time
     *
     * @param beginningDay the beginning date of the desired interval of time
     * @param endingDay the end date of the desired interval of time
     * @return the number of tests that were validated between the desired interval of time
     */
    public int getNumberOfTestsByIntervalDateOfDiagnosis(Date beginningDay, Date endingDay){
        //because it only becomes available to the client after the diagnosis
        int num = 0;
        for (Test test : testList) {
            if ((test.getDateOfDiagnosis().after(beginningDay) && test.getDateOfDiagnosis().before(endingDay))
            || (test.getDateOfTestRegistration().equals(beginningDay)) || (test.getDateOfTestRegistration().equals(endingDay)))
                num++;
        }
        return num;
    }

    /**
     * Gets a list of test parameters of a test
     * @param tst test to retrieve list
     * @return list of test parameters
     */
    public List<TestParameter> getTestParameters(Test tst) {
        return new ArrayList<>(tst.getParameters());
    }

    /**
     * Method for getting list of tests in the store list with no samples collected.
     *
     *
     * @return list of tests with no samples
     */
    public List<Test> getTestsWithNoSamples(String laboratoryID) {
        List<Test> listTestsNoSamples = new ArrayList<>();

        for (Test test : testList) {
            if (!test.hasSamples() && test.getCalId().equals(laboratoryID))
                listTestsNoSamples.add(test);
        }
        return listTestsNoSamples;
    }

    public List<Client> getClientsWithValidatedTests (){
        List<Client> clientsWithValidatedTests  = new ArrayList<>();
        for(Test test : testList){
            if(test.isValidated()){
                clientsWithValidatedTests.add(test.getClient());
            }
        }
        return clientsWithValidatedTests;
    }

    /**
     * Gets a list of the parameters of the test parameters of an specified test object
     * @param test test object to find parameters
     * @return list of parameters
     */
    public List<Parameter> getTotalTestParameters(Test test) {
        List<Parameter> listTotalTestParameters = new ArrayList<>();

        for (TestParameter testParameter : test.getParameters())
            listTotalTestParameters.add(testParameter.getParameter());

        return listTotalTestParameters;
    }

    public Test getTestByNhsNumber(String nhsNumber){
        for (Test test : testList) {
            if (test.getNhsCode().equalsIgnoreCase(nhsNumber))
                return test;
        }
        throw new UnsupportedOperationException("Test not found with given nhs number!");
    }



    /**
     * Gets a test object by its sample barcode number
     * @param barcodeNumber barcode number to find in the tests
     * @return found test
     */
    public Test getTestByBarcodeNumber(String barcodeNumber) {
        for (Test test : testList) {
            if (testHasBarcodeNumber(test, barcodeNumber))
                return test;
        }
        throw new UnsupportedOperationException("Test not found!");
    }

    /**
     * Checks if an specified test has a given barcode number
     * @param test test to search barcode number
     * @param barcodeNumber barcode number to be searched
     * @return true if has barcode number, false otherwise
     */
    private boolean testHasBarcodeNumber(Test test, String barcodeNumber) {
        for (Sample sample : test.getSamples()) {
            if (sample.getMyBarcode().getBarcodeNumber().equals(barcodeNumber))
                return true;
        }
        return false;
    }

    /*
    to be used in US19
    WARNING: - Confirm if it's tests with results OR validated tests -> A: Validated.
            - Confirm if the client wishes the date of test registration or date of results
     */
    public int[] getObservedPositivesToTableOfValues(int numberOfObservations,
                                                    List<String> dates) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int[] observedPositives = new int[numberOfObservations];
        int indexDate = 0;

        for (Test test : testList) {
            if(test.hasPositiveResultForCovid()) {
                for (int i = 0; i < dates.size(); i++) {
                    String testDateOfValidation = sdf.format(test.getDateOfValidation());
                    if(testDateOfValidation.equals(dates.get(i))) {
                        indexDate = i;
                        observedPositives[indexDate]++;
                    }
                }
            }
        }
        return observedPositives;
    }

    public int[] getWeeklyObservedPositivesToTableOfValues(List<String> dates) throws ParseException {
        int[] observedPositives = new int[dates.size()];
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < dates.size(); i++) {
            String[] intervalDatesInString = dates.get(i).split("-");
            Date beginDate = sdf.parse(intervalDatesInString[0]), endDate = sdf.parse(intervalDatesInString[1]);
            observedPositives[i] = getObservedPositivesInOneWeek(beginDate, endDate);
        }
        return observedPositives;
    }

    public int getObservedPositivesInOneWeek(Date beginDate, Date endDate) {
        int weeklyPositives = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        Date auxEndDate = cal.getTime();
        while(!beginDate.after(auxEndDate) && !endDate.before(auxEndDate)) {
            weeklyPositives += getObservedPositivesCovidInADay(auxEndDate);
            cal.add(Calendar.DAY_OF_MONTH,-1);
            auxEndDate = cal.getTime();
        }
        return weeklyPositives;
    }


    public double getNumberOfCovidTestsRealizedInADay(Date date) {
        double testsInADay = 0;
        /*for (Test test : testList) {
            if(test.isCovidTest() && test.isValidated() && checkIfDatesAreEqual(test.getDateOfValidation(), date))
                testsInADay++;
        }
         */
        List<Test> testListCopy = new CopyOnWriteArrayList<>(testList);
        for (Iterator<Test> iterator = testListCopy.iterator(); iterator.hasNext();) {
            Test test = iterator.next();
            if (test.isCovidTest() && test.isValidated() && checkIfDatesAreEqual(test.getDateOfValidation(), date)) {
                testsInADay++;
            }
        }
        return testsInADay;
    }


    public double getMeanAgeOfClientsOfCovidTestsInADay(Date date) {
        double sumAges = 0, numClients = 0;
        /*for (Test test : testList) {
            if(test.isCovidTest() && test.isValidated() && checkIfDatesAreEqual(test.getDateOfValidation(), date)) {
                sumAges += test.getClient().getAge();
                numClients++;
            }
        }
         */
        List<Test> testListCopy = new CopyOnWriteArrayList<>(testList);
        for (Iterator<Test> iterator = testListCopy.iterator(); iterator.hasNext();) {
            Test test = iterator.next();
            if (test.isCovidTest() && test.isValidated() && checkIfDatesAreEqual(test.getDateOfValidation(), date)) {
                sumAges += test.getClient().getAge();
                numClients++;
            }
        }

        //COLOCAR EXCEÇÃO!!!!!!! OU ENTÃO QUE COLOCAR?
        return (numClients != 0) ? sumAges / numClients : 0;
    }


    public double getObservedPositivesCovidInADay(Date date) {
        double positives = 0;
        /*for (Test test : testList) {
            if(test.hasPositiveResultForCovid() && test.isValidated() && checkIfDatesAreEqual(test.getDateOfValidation(), date))
                positives++;
        }
         */
        List<Test> testListCopy = new CopyOnWriteArrayList<>(testList);
        for (Iterator<Test> iterator = testListCopy.iterator(); iterator.hasNext();) {
            Test test = iterator.next();
            if (test.hasPositiveResultForCovid() && test.isValidated() && checkIfDatesAreEqual(test.getDateOfValidation(), date)) {
                positives++;
            }
        }
        return positives;
    }

    public List< List<Double> > getAllDataToFitTheModel(Date beginDate, Date endDate) {
        Calendar auxEndDate = Calendar.getInstance();
        auxEndDate.setTime(endDate);

        List<Double> covidTestList = new ArrayList<>();
        List<Double> meanAgeList = new ArrayList<>();
        List<Double> observedPositives = new ArrayList<>();

        addAllDataFromDateInterval(beginDate, endDate, covidTestList, meanAgeList, observedPositives);

        List< List<Double> > dataList = new ArrayList<>();
        dataList.add(covidTestList);
        dataList.add(meanAgeList);
        dataList.add(observedPositives);

        return dataList;
    }

    public void addAllDataFromDateInterval(Date beginDate,
                                           Date endDate,
                                           List<Double> covidTestList,
                                           List<Double> meanAgeList,
                                           List<Double> observedPositives) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        Date auxEndDate = cal.getTime();

        while(!beginDate.after(auxEndDate) && !endDate.before(auxEndDate)) {
            double testsInADay = getNumberOfCovidTestsRealizedInADay(auxEndDate);
            covidTestList.add(testsInADay);
            double meanAgeInADay = getMeanAgeOfClientsOfCovidTestsInADay(auxEndDate);
            meanAgeList.add(meanAgeInADay);
            double observedPositivesInADay = getObservedPositivesCovidInADay(auxEndDate);
            observedPositives.add(observedPositivesInADay);
            cal.add(Calendar.DAY_OF_MONTH,-1);
            if ((cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY))
                cal.add(Calendar.DAY_OF_MONTH,-1);
            auxEndDate = cal.getTime();
        }
    }

    public Double[] getNumberOfCovidTestsInHistoricalPoints(List<String> dates) {
        double[] covidTestsInHistoricalPointsPrimitive = new double[dates.size()];
        int indexDate = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (Test test : testList) {
            if(test.isCovidTest() && test.isValidated()) {
                for (int i = 0; i < dates.size(); i++) {
                    String testDateOfValidation = sdf.format(test.getDateOfValidation());
                    if(testDateOfValidation.equals(dates.get(i))) {
                        indexDate = i;
                        covidTestsInHistoricalPointsPrimitive[indexDate]++;
                    }
                }
            }
        }
        Double[] covidTestsInHistoricalPoints = turnPrimitiveIntoDoubleArray(covidTestsInHistoricalPointsPrimitive);
        return covidTestsInHistoricalPoints;
    }

    public Double[] getWeeklyNumberOfCovidTestsInHistoricalPoints(List<String> dates) throws ParseException {
        double[] covidTestsInHistoricalPointsPrimitive = new double[dates.size()];
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < dates.size(); i++) {
            String[] intervalDatesInString = dates.get(i).split("-");
            Date beginDate = sdf.parse(intervalDatesInString[0]), endDate = sdf.parse(intervalDatesInString[1]);
            covidTestsInHistoricalPointsPrimitive[i] = getNumberOfCovidTestsInOneWeek(beginDate, endDate);
        }
        Double[] covidTestsInHistoricalPoints = turnPrimitiveIntoDoubleArray(covidTestsInHistoricalPointsPrimitive);
        return covidTestsInHistoricalPoints;
    }

    public double getNumberOfCovidTestsInOneWeek(Date beginDate, Date endDate) {
        int weeklyTests = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        Date auxEndDate = cal.getTime();
        while(!beginDate.after(auxEndDate) && !endDate.before(auxEndDate)) {
            weeklyTests += getNumberOfCovidTestsRealizedInADay(auxEndDate);
            cal.add(Calendar.DAY_OF_MONTH,-1);
            auxEndDate = cal.getTime();
        }
        return weeklyTests;
    }

//TESTAR POR CAUSA DO NULL DO DOUBLE!!!!

    public Double[] getMeanAgeInHistoricalPoints(List<String> dates) {
        Double[] meanAgeInHistoricalPoints = new Double[dates.size()];
        double sumAges = 0, numClients = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (int i = 0; i < dates.size(); i++) {
            for (Test test : testList) {
                if(test.isCovidTest() && test.isValidated()) {
                    String testDateOfDiagnosis = sdf.format(test.getDateOfDiagnosis());
                    if(testDateOfDiagnosis.equals(dates.get(i))) {
                        sumAges += test.getClient().getAge();
                        numClients++;
                    }
                }
            }
            //SE O NUM CLIENTES == 0, O QUE FAZER????
            if(numClients == 0)
                meanAgeInHistoricalPoints[i] = 0.0;
            else
                meanAgeInHistoricalPoints[i] = sumAges / numClients; //antes só tinha esta linha
            sumAges = 0;
            numClients = 0;
        }
        return meanAgeInHistoricalPoints;
    }

    public Double[] getWeeklyMeanAgeInHistoricalPoints(List<String> dates) throws ParseException {
        Double[] meanAgeInHistoricalPoints = new Double[dates.size()];
        double sumAges = 0, numClients = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < dates.size(); i++) {
            String[] intervalDatesInString = dates.get(i).split("-");
            Date beginDate = sdf.parse(intervalDatesInString[0]), endDate = sdf.parse(intervalDatesInString[1]);
            meanAgeInHistoricalPoints[i] = getMeanAgeInOneWeek(beginDate, endDate);
        }
        return meanAgeInHistoricalPoints;
    }

    public double getMeanAgeInOneWeek(Date beginDate, Date endDate) {
        int weeklyTests = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        Date auxEndDate = cal.getTime();
        while(!beginDate.after(auxEndDate) && !endDate.before(auxEndDate)) {
            weeklyTests += getMeanAgeOfClientsOfCovidTestsInADay(auxEndDate);
            cal.add(Calendar.DAY_OF_MONTH,-1);
            auxEndDate = cal.getTime();
        }
        return weeklyTests;
    }



    public boolean checkIfDatesAreEqual(Date date, Date otherDate) {
        Calendar cal = Calendar.getInstance(), otherCal = Calendar.getInstance();
        cal.setTime(date);
        otherCal.setTime(otherDate);
        return cal.get(Calendar.DAY_OF_MONTH) == otherCal.get(Calendar.DAY_OF_MONTH) &&
                cal.get(Calendar.MONTH) == otherCal.get(Calendar.MONTH) &&
                cal.get(Calendar.YEAR) == otherCal.get(Calendar.YEAR);
    }

    public Double[] turnPrimitiveIntoDoubleArray(double[] array) {
        Double[] wishedArray = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            wishedArray[i] = array[i];
        }
        return wishedArray;
    }



}
