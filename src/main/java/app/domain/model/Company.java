package app.domain.model;

import auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {

    private String designation;
    private AuthFacade authFacade;

    public Company(String designation)
    {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();
    }

    public String getDesignation() {
        return designation;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    private List<ParameterCategory> parameterCategoryList;

    public ParameterCategory createParameterCategory(String code, String description, String nhsId){
        return new ParameterCategory(code, description, nhsId);
    }

    public boolean validateParameterCategory(ParameterCategory pc){
        if (pc == null)
            return false;
        return ! this.parameterCategoryList.contains(pc);
    }

    public boolean saveParameterCategory(ParameterCategory pc){
        if (!validateParameterCategory(pc))
            return false;
        return this.parameterCategoryList.add(pc);
    }

    //US8 - ANA
    private List<TestType> totalTT;

    public List<TestType> getTestTypes() {
        return totalTT;
    }

    //COMO PASSAR O SELECTEDTT PARA O LABORATORY?
    public List<TestType> getTestTypesByDesignation(List<String> testTypesDesignations) {
        List<TestType> selectedTT = new ArrayList<>();

        for (String item : testTypesDesignations) {
            for (TestType tt : totalTT) {
                if(item.equalsIgnoreCase(tt.getDesignation()))
                    selectedTT.add(tt);
            }
        }
        return selectedTT;
    }


    private List<Laboratory> laboratories; //Company owns Laboratory

    //FIM US8 - ANA)
}
