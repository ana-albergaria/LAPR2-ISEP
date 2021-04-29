package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class TestTypeStore {

    private List<TestType> testTypeStore;

    public TestType createTestType(String code, String description, String collectingMethod, List<ParameterCategory> selectedCategories){
        return new TestType(code, description, collectingMethod, selectedCategories);
    }

    public List<TestType> getTestTypes() {
        return testTypeStore;
    }

    public boolean validateTestType(TestType testType){
        if (testType == null)
            return false;
        return !this.testTypeStore.contains(testType);
    }

    public boolean saveTestType(TestType testType){
        if (!validateTestType(testType))
            return false;
        return this.testTypeStore.add(testType);
    }
    
    public List<TestType> getTestTypesByCode(List<String> testTypeCodes) {
        List<TestType> selectedTT = new ArrayList<>();

        for (String item : testTypeCodes) {
            for (TestType tt : testTypeStore) {
                if(item.equalsIgnoreCase(tt.getCode()))
                    selectedTT.add(tt);
            }
        }
        return selectedTT;
    }


}
