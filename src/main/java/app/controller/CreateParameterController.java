package app.controller;

import app.domain.model.Company;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;

public class CreateParameterController {

    private Company company;
    private ParameterCategory pc;
    private Parameter prm;

    /*public CreateParameterController(){
        this(App.getInstance().getCompany());
    }*/

    public CreateParameterController(Company company, ParameterCategory pc){
        this.company = company;
        this.pc = pc;
        this.prm = null;
    }

//= +createParameter(parameterCode, shortName, description, categoryCode) =======

    /*public boolean createParameter(String parameterCode, String shortName, String description){
        this.prm = this.company.createParameter(parameterCode, shortName, description);
        return this.company.validateParameter(prm);
    }*/
    //+ ParameterCategory cat + String categoryCode

//========================= +saveParameter() ====================================

    /*public boolean saveParameter(){
        return this.company.getParameterCategoryStore().getParameterCategory.saveParameter(prm);
    }*/

}
