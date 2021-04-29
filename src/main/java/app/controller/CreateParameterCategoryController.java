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

//============== +createParameterCategory(code, name, nhsId) ==============

    public boolean createParameterCategory(String code, String name, String nhsId){
        this.pc = this.company.createParameterCategory(code, name, nhsId);
        return this.company.validateParameterCategory(pc);

    }

//============== +saveParameterCategory() =================================

    public boolean saveParameterCategory(){
        return this.company.getParameterCategoryStore().saveParameterCategory(pc);
    }

}
