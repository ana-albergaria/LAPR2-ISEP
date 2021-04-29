package app.controller;

import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;

import java.util.List;

public class CreateTestTypeController {

    private Company company;
    private TestType testType;

    public CreateTestTypeController(){
        this(App.getInstance().getCompany());
    }

    public CreateTestTypeController(Company company){
        this.company = company;
        this.testType = null;
    }

    public boolean createTestType(String code, String description, String collectingMethod, List<ParameterCategory> selectedCategories){
        this.testType = this.company.getTestTypeStore().createTestType(code, description, collectingMethod, selectedCategories);
        return this.company.getTestTypeStore().validateTestType(testType);
    }

    public boolean saveParameterCategory(){
        return this.company.getTestTypeStore().saveTestType(testType);
    }


}
