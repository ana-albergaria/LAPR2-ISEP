package app.domain.store;

import app.domain.model.Test;
import app.domain.model.TestType;

import java.util.ArrayList;
import java.util.List;

public class TestStore {

    private List<Test> testList = new ArrayList<>();

    private List<Test> testsReadyToDiagnose = new ArrayList<>();


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




}
