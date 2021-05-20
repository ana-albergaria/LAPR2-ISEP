package app.domain.store;

import app.domain.model.Test;

import java.util.ArrayList;
import java.util.List;

public class TestStore {

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

    /*public addReport(Test selectedTest) {

    }*/

}
