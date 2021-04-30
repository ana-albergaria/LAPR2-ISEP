package app.domain.model;

import java.util.List;

public class ParameterStore {

    private List<Parameter> prmList;

    public Parameter createParameter(String parameterCode, String shortName, String description, ParameterCategory cat){
        return new Parameter(parameterCode, shortName, description, cat);
    }

    public boolean validateParameter(Parameter prm){
        if (prm == null)
            return false;
        return ! this.prmList.contains(prm);
    }

    public boolean saveParameter(Parameter prm){
        if (!validateParameter(prm))
            return false;
        return this.prmList.add(prm);
    }

}