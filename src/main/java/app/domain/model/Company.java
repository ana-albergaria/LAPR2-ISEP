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
    private ParameterCategoryStore parameterCategoryStore;
    private TestTypeStore testTypeStore; //Company uses TestTypeStore
    private List<Laboratory> laboratories; //Company owns Laboratory

    private ClientSore clientSore;

    private ParameterStore parameterStore;


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

    public ParameterCategoryStore getParameterCategoryStore() {
        return parameterCategoryStore;
    }

    //to be used in US8
    public TestTypeStore getTestTypeStore() {
        return testTypeStore;
    }

    public ParameterStore getParameterStore() {
        return parameterStore;
    }


    public ClientSore getClientSore(){
        return clientSore;
    }



    /*public boolean addParameterCategory(ParameterCategory pc){

    }*/

    //US10

    /*public Parameter createParameter(String parameterCode, String shortName, String description, ParameterCategory cat){

    }*/

    /*public boolean validateParameter(Parameter prm){

    }*/

    /*public boolean saveParameter(Parameter prm){

    }*/

    /*public boolean addParameter(Parameter prm){

    }*/

}



