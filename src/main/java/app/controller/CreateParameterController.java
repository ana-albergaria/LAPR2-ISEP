package app.controller;

import app.domain.model.Company;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.store.ParameterCategoryStore;
import app.domain.store.ParameterStore;
import app.mappers.CategoriesMapper;
import app.mappers.dto.CategoriesDTO;

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

    public Parameter getPrm(){
        return prm;
    }

    //US10 SD: 4-8
    public List<ParameterCategory> getParameterCategories() {
        ParameterCategoryStore parameterCategoryStore = this.company.getParameterCategoryStore();
        List<ParameterCategory> categoriesList = parameterCategoryStore.getParameterCategoriesStore();
        return categoriesList;
    }

    //US10 SD: 9-15
    public List<CategoriesDTO> getParameterCategoriesDTO(){
        CategoriesMapper mapper = new CategoriesMapper();
        return mapper.toDTO(getParameterCategories());
    }

    //US10 SD: 19-25
    public boolean createParameter(String parameterCode, String shortName, String description, String pcCode){
        ParameterCategoryStore parameterCategoryStore = this.company.getParameterCategoryStore();
        ParameterCategory pc = parameterCategoryStore.getCategoryByCode(pcCode); //vai buscar a cat através do code | o getCategoryByCode está na store
        ParameterStore parameterStore = this.company.getParameterStore();
        this.prm = parameterStore.createParameter(parameterCode, shortName, description, pc);
        return parameterStore.validateParameter(prm);
    }

    //US10 SD: 30-32
    public boolean saveParameter(){
        return this.company.getParameterStore().saveParameter(prm);
    }

}