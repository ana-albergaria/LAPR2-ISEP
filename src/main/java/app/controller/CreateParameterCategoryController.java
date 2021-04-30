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

    //US11 SD: 4, 5, 6, 7 and 8
    public boolean createParameterCategory(String code, String name){
        this.pc = this.company.getParameterCategoryStore().createParameterCategory(code, name);
        return this.company.getParameterCategoryStore().validateParameterCategory(pc);
    }

    //US11 SD: 12, 13, 14 and 15
    public boolean saveParameterCategory(){
        return this.company.getParameterCategoryStore().saveParameterCategory(pc);
    }

}
