package app.domain.store;

import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;

import java.util.List;

public class ParameterStore {

    private List<Parameter> prmList;

    public Parameter createParameter(String parameterCode, String shortName, String description, ParameterCategory pc){
        return new Parameter(parameterCode, shortName, description, pc);
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

    public Parameter[] toArray() {
        Parameter[] array = new Parameter[this.prmList.size()];
        return this.prmList.toArray(array);
    }
}
