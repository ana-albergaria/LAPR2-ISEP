package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class TestType {

    private String code;
    private String description;
    private String collectingMethod;
    private List<ParameterCategory> selectedCategories;

    public TestType(String code, String description, String collectingMethod, List<ParameterCategory> selectedCategories) {
        this.code = code;
        this.description = description;
        this.collectingMethod = collectingMethod;
        this.selectedCategories = new ArrayList<>(selectedCategories);
    }
    //to be used for US8
    public String getCode() {
        return code;
    }
}
