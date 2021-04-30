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
        ParameterCategory cat = this.company.getParameterCategoryStore().getCategoryByCode(pcCode); //precisamos do code para usar aqui | o getCategoryByCode está na store
        this.prm = this.company.getParameterStore().createParameter(parameterCode, shortName, description, cat); //o createParameter está na store
        return this.company.getParameterStore().validateParameter(prm); //o validateParameter está na store
    }

//========================= +saveParameter() ====================================

    public boolean saveParameter(){
        return this.company.getParameterStore().saveParameter(prm); //o saveParameter está na store
    }

}
