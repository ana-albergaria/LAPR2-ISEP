package app.domain.store;

import app.domain.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Store of tests
 *
 * @author João Wolff
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
     * Gets the number of tests that were waiting for results on a specific day
     * @return number of tests that were waiting for results on a specific day
     */
    public int getNumTestsWaitingForResultsDay(Date day){
        int num = 0;
        Date date1, date2;
        for (Test test : testList) {
            date1=test.getDateOfSamplesCollection();
            date2=test.getDateOfChemicalAnalysis();
            if (date1!=null)
                if (date2==null)
                    date2=new Date(10000,Calendar.JANUARY,1);
                if (date1.before(day) && date2.after(day))
                    num++;
        }
        return num;
    }

    /**
     * Gets the number of tests that were waiting for results between two specific days
     * @return number of tests that were waiting for results between two specific days
     */
    public int getNumTestsWaitingForResultsInterval(Date beginningDay, Date endingDay){ //endingDay vai ser as ...:59 do domingo PARA PERTENCER
        int num = 0;
        Date date1, date2;
        for (Test test : testList) {
            date1 = test.getDateOfChemicalAnalysis();
            date2 = test.getDateOfSamplesCollection();
            if (date2!=null)
                if (date1==null)
                    date1=new Date(10000,Calendar.JANUARY,1);
                if ((date1.after(beginningDay) && date1.before(endingDay)) || (date2.before(endingDay) && date1.after(endingDay)))
                    num++;
        }
        return num;
    }

    /**
     * Gets the number of tests that were waiting for diagnosis on a specific day
     * @return number of tests that were waiting for diagnosis on a specific day
     */
    public int getNumTestsWaitingForDiagnosisDay(Date day){
        int num = 0;
        Date date1, date2;
        for (Test test : testList) {
            date1=test.getDateOfChemicalAnalysis();
            date2=test.getDateOfDiagnosis();
            if (date1!=null)
                if (date2==null)
                    date2=new Date(10000,Calendar.JANUARY,1);
                if (date1.before(day) && date2.after(day))
                    num++;
        }
        return num;
    }

    /**
     * Gets the number of tests that were waiting for diagnosis between two specific days
     * @return number of tests that were waiting for diagnosis between two specific days
     */
    public int getNumTestsWaitingForDiagnosisInterval(Date beginningDay, Date endingDay){
        int num = 0;
        Date date1, date2;
        for (Test test : testList) {
            date1 = test.getDateOfDiagnosis();
            date2 = test.getDateOfChemicalAnalysis();
            if (date2!=null)
                if (date1==null)
                    date1=new Date(10000,Calendar.JANUARY,1);
                if ((date1.after(beginningDay) && date1.before(endingDay)) || (date2.before(endingDay) && date1.after(endingDay)))
                    num++;
        }
        return num;
    }

    /**
     * Gets the number of tests processed in lab on a specific day
     * @return number of tests processed in lab on a specific day
     */
    public int getNumTestsProcessedInLabDay(Date day){
        int num = 0;
        Date date1;
        for (Test test : testList) {
            date1 = test.getDateOfTestRegistration();
            if (date1!=null)
                if (date1.before(day) || date1.equals(day))
                    num++;
        }
        return num;
    }

    /**
     * Gets the number of tests processed in lab between two specific days
     * @return number of tests processed in lab between two specific days
     */
    public int getNumTestsProcessedInLabInterval(Date beginningDay, Date endingDay){
        int num = 0;
        Date date1;
        for (Test test : testList) {
            date1 = test.getDateOfTestRegistration();
            if (date1!=null)
                if ((date1.after(beginningDay) && date1.before(endingDay)) || date1.before(beginningDay))
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
     * @param beginningDate the beginning date of the desired interval of time
     * @param endDate the end date of the desired interval of time
     * @return the number of tests that were registered between the desired interval of time
     */
    public int getNumberOfTestsByIntervalDateOfTestRegistration(Date beginningDate, Date endDate){
        int num = 0;
        for (Test test : testList) {
            if ((test.getDateOfTestRegistration().after(beginningDate) && test.getDateOfTestRegistration().before(endDate)) || test.getDateOfTestRegistration().equals(beginningDate))
                num++;
        }
        return num;
    }

    /**
     * Gets the number of tests that were validated between the desired interval of time
     *
     * @param beginningDate the beginning date of the desired interval of time
     * @param endDate the end date of the desired interval of time
     * @return the number of tests that were validated between the desired interval of time
     */
    public int getNumberOfTestsByIntervalDateOfDiagnosis(Date beginningDate, Date endDate){
        //because it only becomes available to the client after the diagnosis
        int num = 0;
        for (Test test : testList) {
            if ((test.getDateOfDiagnosis().after(beginningDate) && test.getDateOfDiagnosis().before(endDate)) || test.getDateOfDiagnosis().equals(beginningDate))
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
                                                    List<String> dates) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int[] observedPositives = new int[numberOfObservations];
        int indexDate = 0;

        for (Test test : testList) {
            if(test.hasPositiveResultForCovid()) {
                for (int i = 0; i < dates.size(); i++) {
                    String testDateOfDiagnosis = sdf.format(test.getDateOfDiagnosis());
                    if(testDateOfDiagnosis.equals(dates.get(i))) {
                        indexDate = i;
                        observedPositives[indexDate]++;
                    }
                }
            }
        }
        return observedPositives;
    }


    public double getNumberOfCovidTestsRealizedInADay(Date date) {
        double testsInADay = 0;
        for (Test test : testList) {
            if(test.isCovidTest() && test.isValidated() && checkIfDatesAreEqual(test.getDateOfDiagnosis(), date))
                testsInADay++;
        }
        return testsInADay;
    }

    public double getMeanAgeOfClientsOfCovidTestsInADay(Date date) {
        double sumAges = 0, numClients = 0;
        for (Test test : testList) {
            if(test.isCovidTest() && test.isValidated() && checkIfDatesAreEqual(test.getDateOfDiagnosis(), date)) {
                sumAges += test.getClient().getAge();
                numClients++;
            }
        }
        return sumAges / numClients;
    }

    public double getObservedPositivesCovidInADay(Date date) {
        double positives = 0;
        for (Test test : testList) {
            if(test.hasPositiveResultForCovid() && test.isValidated() && checkIfDatesAreEqual(test.getDateOfDiagnosis(), date))
                positives++;
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
        Calendar auxEndDate = Calendar.getInstance();
        auxEndDate.setTime(endDate);

        while(!checkIfDatesAreEqual(beginDate, endDate)) {
            double testsInADay = getNumberOfCovidTestsRealizedInADay(endDate);
            covidTestList.add(testsInADay);
            double meanAgeInADay = getMeanAgeOfClientsOfCovidTestsInADay(endDate);
            meanAgeList.add(meanAgeInADay);
            double observedPositivesInADay = getObservedPositivesCovidInADay(endDate);
            observedPositives.add(observedPositivesInADay);
            auxEndDate.add(Calendar.DAY_OF_MONTH,-1);
            endDate = auxEndDate.getTime();
        }
    }

    public double[] getNumberOfCovidTestsInHistoricalPoints(List<String> dates) {
        double[] covidTestsInHistoricalPoints = new double[dates.size()];
        int indexDate = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (Test test : testList) {
            if(test.isCovidTest() && test.isValidated()) {
                for (int i = 0; i < dates.size(); i++) {
                    String testDateOfDiagnosis = sdf.format(test.getDateOfDiagnosis());
                    if(testDateOfDiagnosis.equals(dates.get(i))) {
                        indexDate = i;
                        covidTestsInHistoricalPoints[indexDate]++;
                    }
                }
            }
        }
        return covidTestsInHistoricalPoints;
    }

    public double[] getMeanAgeInHistoricalPoints(List<String> dates) {
        double[] meanAgeInHistoricalPoints = new double[dates.size()];
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
            meanAgeInHistoricalPoints[i] = sumAges / numClients;
        }
        return meanAgeInHistoricalPoints;
    }

    public boolean checkIfDatesAreEqual(Date date, Date otherDate) {
        Calendar cal = Calendar.getInstance(), otherCal = Calendar.getInstance();
        cal.setTime(date);
        otherCal.setTime(otherDate);
        return cal.get(Calendar.DAY_OF_MONTH) == otherCal.get(Calendar.DAY_OF_MONTH) &&
                cal.get(Calendar.MONTH) == otherCal.get(Calendar.MONTH) &&
                cal.get(Calendar.YEAR) == otherCal.get(Calendar.YEAR);
    }



}
