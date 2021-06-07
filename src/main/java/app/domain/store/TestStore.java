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
     * gets the tests which are ready for the results to be added
     * @return list of ready for the results to be added
     */
    public List<Test> getTestsReadyForResults() {
        List<Test> listTestsReadyForResults = new ArrayList<>();

        for (Test test : testList) {
            if (test.hasSamples() && !test.hasResults())
                listTestsReadyForResults.add(test);
        }
        return listTestsReadyForResults;
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
     * Gets a list of test parameters of a test
     * @param tst test to retrieve list
     * @return list of test parameters
     */
    public List<TestParameter> getTestParameters(Test tst) {
        return new ArrayList<>(tst.getParameters());
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

    /**
     * Saves the Tests info to be considered when analysing the company performance on an ArrayList
     * @return an ArrayList with all the Tests info to be considered when analysing the company performance
     */
    public ArrayList<Integer> getTestsInfo(){
        ArrayList<Integer> testsInfo = new ArrayList<Integer>();
        int waitingForResults = getTestsReadyForResults().size();
        testsInfo.add(waitingForResults);
        int waitingForDiagnosis = getTestsReadyToDiagnose().size();
        testsInfo.add(waitingForDiagnosis);
        //falta fazer daqui:
        int processedEachDay = 0;
        testsInfo.add(processedEachDay);
        int processedEachWeek = 0;
        testsInfo.add(processedEachWeek);
        int processedEachMonth = 0;
        testsInfo.add(processedEachMonth);
        int processedEachYear = 0;
        testsInfo.add(processedEachYear);
        //até aqui
        return testsInfo;
    }

    /*
    to be used in US19
    WARNING: - Confirm if it's tests with results OR validated tests;
            - Confirm if the client wishes the date of test registration or date of results
     */
    public List < List<String> > getTestsWithResultsDataForTableOfValues(int numberOfObservations,
                                                                         Date currentDate) throws ParseException {
        List< List<String> > tableOfValues = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        List<String> observedPositives = new ArrayList<>();

        addDatesColumnToTableOfValues(numberOfObservations, currentDate, dates);
        addObservedPositivesToTableOfValues(numberOfObservations, dates, observedPositives);

        tableOfValues.add(dates);
        tableOfValues.add(observedPositives);

        return tableOfValues;
    }

    public void addObservedPositivesToTableOfValues(int numberOfObservations,
                                                    List<String> dates,
                                                    List<String> observedPositives) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int[] observedPositivesInt = new int[numberOfObservations];
        int numberOfPositivesPerDay = 0, indexDate = 0;

        for (Test test : testList) {
            if(test.hasPositiveResultForCovid()) {
                for (int i = 0; i < dates.size(); i++) {
                    Date dateToBeCompared = sdf.parse(dates.get(i));
                    if(checkIfDatesAreEqual(test.getDateOfChemicalAnalysis(), dateToBeCompared)) {
                        numberOfPositivesPerDay++;
                        indexDate = i;
                    }
                }
            }
            observedPositivesInt[indexDate] = numberOfPositivesPerDay;
            numberOfPositivesPerDay = 0;
        }
        convertIntegerListToString(observedPositives, observedPositivesInt);
    }

    public void convertIntegerListToString(List<String> observedPositives, int[] observedPositivesInt) {
        for (int i = 0; i < observedPositivesInt.length; i++) {
            observedPositives.add(String.valueOf(observedPositivesInt[i]));
        }


    }

    /*
    WARNING - ter em atenção que ao converter a String selecionada pelo administrador
    ou a que está definida na configuration file,
     */
    public void addDatesColumnToTableOfValues(int numberOfObservations,
                                                      Date currentDate,
                                                      List<String> dates) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);

        for (int i = 0; i < numberOfObservations; i++) {
            dates.add(sdf.format(currentDate));
            cal.add(Calendar.DAY_OF_MONTH,-1);
            currentDate = cal.getTime();
        }
    }

    public boolean checkIfDatesAreEqual(Date date, Date otherDate) {
        Calendar cal = Calendar.getInstance();
        Calendar otherCal = Calendar.getInstance();
        cal.setTime(date);
        otherCal.setTime(otherDate);
        return cal.get(Calendar.DAY_OF_MONTH) == otherCal.get(Calendar.DAY_OF_MONTH) &&
                cal.get(Calendar.MONTH) == otherCal.get(Calendar.MONTH) &&
                cal.get(Calendar.YEAR) == otherCal.get(Calendar.YEAR);
    }



}
