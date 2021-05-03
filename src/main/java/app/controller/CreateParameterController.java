package app.controller;

import app.domain.model.Company;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;

import java.util.List;

public class CreateParameterController {

    private Company company;
    private Parameter prm;

    public CreateParameterController(){
        this(App.getInstance().getCompany());
    }

    public CreateParameterController(Company company){
        this.company = company;
        this.prm = null;
    }

    public List<ParameterCategory> getParameterCategories() {
        List<ParameterCategory> categoriesList = this.company.getParameterCategoryStore().getParameterCategoriesStore();
        return categoriesList;
    }

    public boolean createParameter(String parameterCode, String shortName, String description, String pcCode){
        ParameterCategory pc = this.company.getParameterCategoryStore().getCategoryByCode(pcCode); //seleciona a cat através do code | o getCategoryByCode está na store
        this.prm = this.company.getParameterStore().createParameter(parameterCode, shortName, description, pc);
        return this.company.getParameterStore().validateParameter(prm);
    }

    public boolean saveParameter(){
        return this.company.getParameterStore().saveParameter(prm);
    }

}