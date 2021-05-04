package app.domain.store;

import app.domain.model.ParameterCategory;
import app.domain.model.TestType;

import java.util.ArrayList;
import java.util.List;

public class TestTypeStore {
    /**
     * List of test types performed by the company
     */
    private List<TestType> testTypeList = new ArrayList<>();

    /**
     * Test type class creator with full constructor
     * @param code
     * @param description
     * @param collectingMethod
     * @param selectedCategories
     * @return created test type reference
     */
    public TestType createTestType(String code, String description, String collectingMethod, List<ParameterCategory> selectedCategories){
        return new TestType(code, description, collectingMethod, selectedCategories);
    }

    public List<TestType> getTestTypes() {
        return new ArrayList<>(testTypeList);
    }

    /**
     * Validation of test type instance relative to list of test types. checking for null and duplicity
     * @param testType
     * @return true for success and false for fail
     */
    public boolean validateTestType(TestType testType){
        if (testType == null)
            return false;
        return !this.testTypeList.contains(testType);
    }

    /**
     * Save of test type instance inside the list of test type store, checking for validation before
     * @param testType
     * @return true for success and false for fail
     */
    public boolean saveTestType(TestType testType){
        if (!validateTestType(testType))
            return false;
        return this.testTypeList.add(testType);
    }

    /**
     * Method for geting test types in the store list relatively to list of test type codes
     * @param testTypeCodes
     * @return List of test types
     */
    public List<TestType> getTestTypesByCode(List<String> testTypeCodes) {
        List<TestType> selectedTT = new ArrayList<>();
        for (String item : testTypeCodes) {
            selectedTT.add(getSingleTestTypeByCode(item));
        }
        return selectedTT;
    }

    /**
     * Get test acording to code of test type
     * @param code
     * @return test type reference
     */
    private TestType getSingleTestTypeByCode(String code) {
        for (TestType tt : testTypeList) {
            if(tt.getCode().equalsIgnoreCase(code)){
                return tt;
            }
        }
        throw new UnsupportedOperationException("Test Type not found with given code: " + code);
    }
}