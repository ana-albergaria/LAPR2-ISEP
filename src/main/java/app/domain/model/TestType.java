package app.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class TestType {

    /**
     * Max lenght of description field
     */
    private static final int DESCRIPTION_MAXLENGTH = 15;
    /**
     * Max lenght of code field
     */
    private static final int CODE_MAXLENGTH = 5;
    /**
     * Max lenght of collecting method field
     */
    private static final int COLLECTING_METHOD_MAXLENGTH = 20;
    /**
     * Test Type unique code
     */
    private final String code;
    /**
     * Test Type description
     */
    private final String description;
    /**
     * Test Type collecting method
     */
    private final String collectingMethod;
    /**
     * List of Categories assigned to test type
     */
    private final List<ParameterCategory> selectedCategories;

    /**
     * Full constructor of test type
     *
     * @param code Test type's code
     * @param description Test type's description
     * @param collectingMethod Test type's collecting methods
     * @param selectedCategories Test type's categories list
     */
    public TestType(String code, String description, String collectingMethod, List<ParameterCategory> selectedCategories) {
        checkCode(code);
        checkDescription(description);
        checkCollectingMethod(collectingMethod);
        this.code = code;
        this.description = description;
        this.collectingMethod = collectingMethod;
        this.selectedCategories = new ArrayList<>(selectedCategories);
    }

    //to be used for US8

    /**
     * Returns the code of the Test Type
     *
     * @return code of the Test Type
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the description of the Test Type
     *
     * @return description of the Test Type
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the collecting method of the Test Type
     *
     * @return collecting method of the Test Type
     */
    public String getCollectingMethod() {
        return collectingMethod;
    }

    /**
     * Returns the parameter categories of the Test Type
     *
     * @return parameter categories of the Test Type
     */
    public List<ParameterCategory> getSelectedCategories() {
        return selectedCategories;
    }

    /**
     * It returns the textual description of the Test Type.
     *
     * @return characteristics of the Test Type
     */
    @Override
    public String toString() {
        return "TestType{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", collectingMethod='" + collectingMethod + '\'' +
                ", selectedCategories=" + selectedCategories +
                '}';
    }

    /**
     * Code attribute validation for having non alphanumeric characters, more or less then 5 characters or blank
     *
     * @param code Test type's code
     */
    private void checkCode(String code) {
        if (StringUtils.isBlank(code))
            throw new IllegalArgumentException("Code cannot be blank.");
        if ((code.length() != CODE_MAXLENGTH))
            throw new IllegalArgumentException("Code must hold 5 alphanumeric characters");
        if (!StringUtils.isAlphanumeric(code))
            throw new IllegalArgumentException("Code must only have alphanumeric characters.");
    }

    /**
     * Decription attribute validation for having more then 15 characters or being blank
     *
     * @param description Test type's description
     */
    private void checkDescription(String description) {
        if (StringUtils.isBlank(description))
            throw new IllegalArgumentException("Description cannot be blank.");
        if ((description.length() > DESCRIPTION_MAXLENGTH))
            throw new IllegalArgumentException("Description cannot have more than 15 characters.");
    }

    /**
     * Collecting method attribute validation for having more then 20 characters or being blank
     *
     * @param collectingMethod Test type's collecting methods
     */
    private void checkCollectingMethod(String collectingMethod) {
        if (StringUtils.isBlank(collectingMethod))
            throw new IllegalArgumentException("Collecting method cannot be blank.");
        if ((collectingMethod.length() > COLLECTING_METHOD_MAXLENGTH))
            throw new IllegalArgumentException("Collecting method cannot have more than 20 alphanumeric characters.");
    }

    @Override
    public boolean equals(Object testTypeObject) {
        if (this == testTypeObject) return true;
        if (testTypeObject == null || getClass() != testTypeObject.getClass()) return false;
        TestType testTypeToCompare = (TestType) testTypeObject;
        return testTypeToCompare.getCode().equals(this.getCode());
    }

}
