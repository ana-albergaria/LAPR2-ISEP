package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class TestTypeStore {
    List<TestType> totalTT = new ArrayList<>();

    public boolean add(TestType tt) {
        totalTT.add(tt);
        return true;
    }

    public TestType get(int index) {
        return totalTT.get(index);
    }

    public List<TestType> getTestTypes() {
        return totalTT;
    }

    public List<TestType> getTestTypesByDesignation(List<String> testTypesDesignations) {
        List<TestType> selectedTT = new ArrayList<>();

        for (String item : testTypesDesignations) {
            for (TestType tt : totalTT) {
                if(item.equalsIgnoreCase(tt.getDesignation()))
                    selectedTT.add(tt);
            }
        }
        return selectedTT;
    }


}
