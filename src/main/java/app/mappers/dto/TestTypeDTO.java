package app.mappers.dto;

import app.domain.model.ParameterCategory;

import java.util.ArrayList;
import java.util.List;

public class TestTypeDTO {
    /**
     * Test Type unique code
     */
    private String code;
    /**
     * Test Type description
     */
    private String description;
    /**
     * Test Type collecting method
     */
    private String collectingMethod;
    /**
     * List of Categories assigned to test type
     */
    private List<ParameterCategory> selectedCategories;

    /**
     * Full constructor of test type
     * @param code
     * @param description
     * @param collectingMethod
     * @param selectedCategories
     */
    public TestTypeDTO(String code, String description, String collectingMethod, List<ParameterCategory> selectedCategories) {
        this.code = code;
        this.description = description;
        this.collectingMethod = collectingMethod;
        this.selectedCategories = new ArrayList<>(selectedCategories);
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "TestTypeDTO{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", collectingMethod='" + collectingMethod + '\'' +
                ", selectedCategories=" + selectedCategories +
                '}';
    }
}

