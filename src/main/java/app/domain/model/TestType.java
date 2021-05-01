package app.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestType {

    private String code;
    private String description;
    private String collectingMethod;
    private List<ParameterCategory> selectedCategories;

    private static final int DESCRIPTION_MAXLENGTH = 15;
    private static final int CODE_MAXLENGTH = 5;
    private static final int COLLECTING_METHOD_MAXLENGTH = 20;

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
    public String getCode() {
        return code;
    }

    private void checkCode(String code){
        if (StringUtils.isBlank(code))
            throw new IllegalArgumentException("Code cannot be blank.");
        if ((code.length() != CODE_MAXLENGTH))
            throw new IllegalArgumentException("Code must hold 5 alphanumeric characters");
        if(!StringUtils.isAlphanumeric(code))
            throw new IllegalArgumentException("Code must only have alphanumeric characters.");
    }

    private void checkDescription(String description){
        if (StringUtils.isBlank(description))
            throw new IllegalArgumentException("Description cannot be blank.");
        if ((description.length() > DESCRIPTION_MAXLENGTH))
            throw new IllegalArgumentException("Description cannot have more than 15 characters.");
    }

    private void checkCollectingMethod(String atributte){
        if (StringUtils.isBlank(atributte))
            throw new IllegalArgumentException("Collecting method cannot be blank.");
        if ((atributte.length() >= COLLECTING_METHOD_MAXLENGTH))
            throw new IllegalArgumentException("Collecting method cannot have more than 20 alphanumeric characters.");
    }

    @Override
    public boolean equals(Object testTypeObject) {
        if (this == testTypeObject) return true;
        if (testTypeObject == null || getClass() != testTypeObject.getClass()) return false;
        TestType testTypeToCompare = (TestType) testTypeObject;
        return testTypeToCompare.getCode().equals(this.getCode());
    }

    @Override
    public String toString() {
        return "TestType{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", collectingMethod='" + collectingMethod + '\'' +
                ", selectedCategories=" + selectedCategories +
                '}';
    }
}
