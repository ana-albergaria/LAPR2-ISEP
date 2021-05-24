package app.domain.store;

import app.domain.model.Client;
import app.domain.model.Parameter;
import app.domain.model.Test;
import app.domain.model.TestType;

import java.util.ArrayList;
import java.util.List;

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

    private List<Test> testsReadyToDiagnose = new ArrayList<>();

    /**
     * Method for creating an instance of Test
     * @param nhsCode National health system code of a given test
     * @param associatedClient client object which has solicited a test
     * @param testType Type of test to be conduted
     * @param parameters List of parameters to be measured of a given test
     */
    public Test createTest(String nhsCode, Client associatedClient, TestType testType, List<Parameter> parameters){
        return new Test(nhsCode, associatedClient, testType, parameters);
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

    public List<Test> getTestsReadyToDiagnose(){
        return testsReadyToDiagnose;
    }


    public Test getTestByCode(String code){
        for (Test tst : testsReadyToDiagnose) {
            if (tst.getCode().equalsIgnoreCase(code)){
                return tst;
            }
        }
        throw new UnsupportedOperationException("Parameter Category not found!");
    }


    /*public boolean addReportToTest(Test selectedTest){

    }*/

    //to be used in US5
    public List<Test> getTestsWithNoSamples() {
        List<Test> listTestsNoSamples = new ArrayList<>();

        for (Test test : testList) {
            if(test.getSamples().size() > 0)
                listTestsNoSamples.add(test);
        }
        return listTestsNoSamples;
    }

    public List<Parameter> getTotalTestParameters() {
        List<Parameter> listTotalTestParameters = new ArrayList<>();

    }




}
