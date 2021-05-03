package app.controller;

import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.store.ParameterCategoryStore;
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

    public TestType getTestType() {
        return testType;
    }

    public List<ParameterCategory> getParameterCategories() {
        ParameterCategoryStore parameterCategoryStore = this.company.getParameterCategoryStore();
        /*List<ParameterCategory> categoriesList = new ArrayList<>(parameterCategoryStore.getParameterCategoriesStore());*/
        return parameterCategoryStore.getParameterCategoriesStore();
    }

    public boolean createTestType(String code, String description, String collectingMethod, List<String> selectedCategoriesCodes){
        ParameterCategoryStore parameterCategoryStore = this.company.getParameterCategoryStore();
        List<ParameterCategory> selectedCategories = parameterCategoryStore.getCategoriesByCode(selectedCategoriesCodes);
        this.testType = this.company.getTestTypeStore().createTestType(code, description, collectingMethod, selectedCategories);
        return this.company.getTestTypeStore().validateTestType(testType);
    }

    public boolean saveTestType(){
        return this.company.getTestTypeStore().saveTestType(testType);
    }


}
