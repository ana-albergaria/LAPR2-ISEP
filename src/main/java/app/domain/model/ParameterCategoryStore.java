package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class ParameterCategoryStore {

    private List<ParameterCategory> parameterCategoriesStore;

    public ParameterCategory createParameterCategory(String code, String description, String nhsId){
        return new ParameterCategory(code, description, nhsId);
    }

    public List<ParameterCategory> getParameterCategoriesStore() {
        return parameterCategoriesStore;
    }

    public boolean validateParameterCategory(ParameterCategory pc){
        if (pc == null)
            return false;
        return !this.parameterCategoriesStore.contains(pc);
    }

    public boolean saveParameterCategory(ParameterCategory pc){
        if (!validateParameterCategory(pc))
            return false;
        return this.parameterCategoriesStore.add(pc);
    }

    public List<ParameterCategory> getCategoriesByCode(List<String> paramenterCategoryCodes) {
        List<ParameterCategory> selectedCategories = null;
        for (String item : paramenterCategoryCodes) {
            selectedCategories.add(getCategoryByCode(item));
        }
        if(selectedCategories != null) {
            return selectedCategories;
        }else{
            throw new UnsupportedOperationException("No parameter categories found!");
        }
    }

    public ParameterCategory getCategoryByCode(String code) {
        for (ParameterCategory pc : parameterCategoriesStore) {
            if(pc.getCode().equalsIgnoreCase(code)){
                return pc;
            }
        }
        throw new UnsupportedOperationException("Parameter Category not found!");
    }

}
