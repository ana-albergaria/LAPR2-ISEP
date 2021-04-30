package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<ParameterCategory> pcList = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("CODE1","Description");
        ParameterCategory p2 = new ParameterCategory("CODE2","Description");
        pcList.add(p1);
        pcList.add(p2);
        TestType t1 = new TestType("CODE3","Description","swab",pcList);
        TestType t2 = new TestType("CODE4","Description","swab",pcList);
        List<TestType> selectedTT = new ArrayList<>();
        selectedTT.add(t1);
        selectedTT.add(t2);

        ClinicalAnalysisLaboratory instance4 = new ClinicalAnalysisLaboratory("CAL12",
                "CAL23","Lisboa","91841378811","1234567890",selectedTT);

        System.out.println(instance4);

    }
}
