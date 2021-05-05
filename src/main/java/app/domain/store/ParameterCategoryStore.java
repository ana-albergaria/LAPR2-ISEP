package app.domain.store;

import app.domain.model.ParameterCategory;
import app.mappers.CategoriesMapper;
import app.mappers.dto.CategoriesDTO;

import java.util.ArrayList;
import java.util.List;

public class ParameterCategoryStore {

    private List<ParameterCategory> parameterCategoriesList = new ArrayList<>();

    //US10 SD: 6
    public List<ParameterCategory> getParameterCategoriesStore() {
        return parameterCategoriesList;
    }

    public ParameterCategory createParameterCategory(String code, String name){
        return new ParameterCategory(code, name);
    }

    public boolean validateParameterCategory(ParameterCategory pc){
        if (pc == null)
            return false;
        return !this.parameterCategoriesList.contains(pc);
    }

    public boolean saveParameterCategory(ParameterCategory pc){
        if (!validateParameterCategory(pc))
            return false;
        return this.parameterCategoriesList.add(pc);
    }

    public List<ParameterCategory> getCategoriesByCode(List<String> parameterCategoryCodes) {
        List<ParameterCategory> selectedCategories = new ArrayList<>();
        for (String item : parameterCategoryCodes) {
            selectedCategories.add(getCategoryByCode(item));
        }
        return selectedCategories;
    }

    public ParameterCategory getCategoryByCode(String code) {
        for (ParameterCategory pc : parameterCategoriesList) {
            if(pc.getCode().equalsIgnoreCase(code)){
                return pc;
            }
        }
        throw new UnsupportedOperationException("Parameter Category not found!");
    }

}