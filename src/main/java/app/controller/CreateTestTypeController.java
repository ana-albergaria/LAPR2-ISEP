package app.controller;

import app.mappers.CategoriesMapper;
import app.mappers.dto.CategoriesDTO;
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

    public boolean createTestType(String code, String description, String collectingMethod, List<String> selectedCategoriesCodes){
        ParameterCategoryStore parameterCategoryStore = this.company.getParameterCategoryStore();
        List<ParameterCategory> selectedCategories = parameterCategoryStore.getCategoriesByCode(selectedCategoriesCodes);
        this.testType = this.company.getTestTypeStore().createTestType(code, description, collectingMethod, selectedCategories);
        return this.company.getTestTypeStore().validateTestType(testType);
    }

    public boolean saveTestType(){
        return this.company.getTestTypeStore().saveTestType(testType);
    }

    public List<ParameterCategory> getParameterCategories() {
        ParameterCategoryStore parameterCategoryStore = this.company.getParameterCategoryStore();
        return parameterCategoryStore.getParameterCategoriesStore();
    }

    public List<CategoriesDTO> getParameterCategoriesDTO (){
        CategoriesMapper mapper = new CategoriesMapper();
        return mapper.toDTO(getParameterCategories());
    }

}
