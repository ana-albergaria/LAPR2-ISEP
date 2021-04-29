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
    //private List<TestType> totalTT; //Company conducts TestType
    //private List<Laboratory> laboratories; //Company owns Laboratory
    private TestTypeStore storeTest; //Company uses TestTypeStore

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

    public ParameterCategory createParameterCategory(String code, String name, String nhsId){
        return new ParameterCategory(code, name, nhsId);
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
    public TestTypeStore getTestTypeStore() {
        return storeTest;
    }

    //FIM US8 - ANA)
}
