package app.controller;

import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.store.ParameterCategoryStore;

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

    public boolean createParameterCategory(String code, String name){
        ParameterCategoryStore store = this.company.getParameterCategoryStore();
        this.pc = store.createParameterCategory(code, name);
        return store.validateParameterCategory(pc);
    }

    public boolean saveParameterCategory(){
        ParameterCategoryStore store = this.company.getParameterCategoryStore();
        return store.saveParameterCategory(pc);
    }

}
