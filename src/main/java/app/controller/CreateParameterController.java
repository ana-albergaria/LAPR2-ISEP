package app.controller;

import app.domain.model.Company;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;

import java.util.List;

public class CreateParameterController {

    private Company company;
    private ParameterCategory pc;
    private Parameter prm;

    public CreateParameterController(){
        this(App.getInstance().getCompany());
    }

    public CreateParameterController(Company company){
        this.company = company;
        this.pc = null;
        this.prm = null;
    }

//= +createParameter(parameterCode, shortName, description, pcCode) =======

    public boolean createParameter(String parameterCode, String shortName, String description, String pcCode){
        ParameterCategory cat = this.company.getParameterCategoryStore().getCategoryByCode(pcCode); //precisamos do code para usar aqui |
        this.prm = this.company.createParameter(parameterCode, shortName, description, cat); //o createParameter está na company
        return this.company.validateParameter(prm); //o validateParameter está na company
    }

//========================= +saveParameter() ====================================

    public boolean saveParameter(){
        return this.company.saveParameter(prm); //o saveParameter está na company
    }

}
