package app.controller;

import app.domain.model.Company;
import app.domain.model.ParameterCategory;

public class CreateParameterCategoryController {

    private Company company;
    private ParameterCategory pc;

    public CreateParameterCategoryController(){
        this(App.getInstance().getCompany());
    }

    public CreateParameterCategoryController(Company company){
        this.company = company;
        this.pc = null;
    }

    public boolean createParameterCategory(String code, String description, String nhsId){
        this.pc = this.company.getParameterCategoryStore().createParameterCategory(code, description, nhsId);
        return this.company.getParameterCategoryStore().validateParameterCategory(pc);
    }

    public boolean saveParameterCategory(){
        return this.company.getParameterCategoryStore().saveParameterCategory(pc);
    }

}
