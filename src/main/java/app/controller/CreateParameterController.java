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

    //US10 SD: 4, 5 and 6
    public List<ParameterCategory> getParameterCategories() {
        List<ParameterCategory> categoriesList = this.company.getParameterCategoryStore().getParameterCategoriesStore();
        return categoriesList;
    }

    //US10 SD: 9, 10, 11, 12 and 13
    public boolean createParameter(String parameterCode, String shortName, String description, String pcCode){
        ParameterCategory pc = this.company.getParameterCategoryStore().getCategoryByCode(pcCode); //seleciona a cat através do code | o getCategoryByCode está na store
        this.prm = this.prm.createParameter(parameterCode, shortName, description, pc);
        return this.company.validateParameter(prm);
    }

    //US10 SD: 17, 18, 19 and 20
    public boolean saveParameter(){
        return this.company.saveParameter(prm);
    }

}