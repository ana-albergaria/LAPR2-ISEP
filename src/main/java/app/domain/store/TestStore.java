package app.domain.store;

import java.util.ArrayList;

public class TestStore {

    private List<Test> testsReadyToDiagnose = new ArrayList<>();

    public List<Test> getTestsReadyToDiagnose(){
        return testsReadyToDiagnose;
    }

    public Test getTestByCode(String code){
        for (Test tst : testsReadyToDiagnose) {
            if (tst.getCode().equalsIgnoreCase){
                return tst
            }
        }
    }

    public addReport(Test selectedTest) {

    }

}
