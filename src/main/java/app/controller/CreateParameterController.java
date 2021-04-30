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

    public boolean createParameter(String parameterCode, String shortName, String description, String pcCode){
        ParameterCategory cat = this.company.getParameterCategoryStore().getCategoryByCode(pcCode); //seleciona a cat | o getCategoryByCode está na store
        this.prm = this.prm.createParameter(parameterCode, shortName, description, cat);
        return this.prm.validateParameter(prm);
    }

    public boolean saveParameter(){
        return this.prm.saveParameter(prm);
    }

}