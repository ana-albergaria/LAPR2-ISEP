package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class TestTypeStore {

    private List<TestType> testTypeList = new ArrayList<>();

    public TestType createTestType(String code, String description, String collectingMethod, List<ParameterCategory> selectedCategories){
        return new TestType(code, description, collectingMethod, selectedCategories);
    }

    public List<TestType> getTestTypes() {
        return testTypeList;
    }

    public boolean validateTestType(TestType testType){
        if (testType == null)
            return false;
        return !this.testTypeList.contains(testType);
    }

    public boolean saveTestType(TestType testType){
        if (!validateTestType(testType))
            return false;
        return this.testTypeList.add(testType);
    }

    public List<TestType> getTestTypesByCode(List<String> testTypeCodes) {
        List<TestType> selectedTT = new ArrayList<>();
        for (String item : testTypeCodes) {
            selectedTT.add(getSingleTestTypeByCode(item));
        }
        return selectedTT;
    }

    private TestType getSingleTestTypeByCode(String code) {
        for (TestType tt : testTypeList) {
            if(tt.getCode().equalsIgnoreCase(code)){
                return tt;
            }
        }
        throw new UnsupportedOperationException("Test Type not found with given code: " + code);
    }

}
