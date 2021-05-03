package app.domain.mappers.dto;

import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import org.apache.commons.lang3.StringUtils;

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

}
