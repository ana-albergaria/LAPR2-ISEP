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

    public List<TestType> getTestTypesByCode(List<String> testTypesCodes) {
        List<TestType> selectedTT = new ArrayList<>();

        for (String item : testTypesCodes) {
            for (TestType tt : totalTT) {
                if(item.equalsIgnoreCase(tt.getCode())
                    selectedTT.add(tt);
            }
        }
        return selectedTT;
    }


}
