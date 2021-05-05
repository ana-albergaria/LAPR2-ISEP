package app.domain.store;

import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;

import java.util.List;

/**
 * Takes some of the company responsabilities regarding parameters,
 * in order to achieve high cohesion and low coupling.
 *
 * @author Marta Ribeiro 1201592
 */
public class ParameterStore {

    /**
     * List of existing parameters.
     */
    private List<Parameter> prmList;

    /**
     * Creates parameter reference.
     *
     * @param parameterCode the parameter code.
     * @param shortName the parameter name.
     * @param description the parameter description.
     * @param pc the parameter category of the parameter.
     *
     * @return created parameter reference.
     */
    public Parameter createParameter(String parameterCode, String shortName, String description, ParameterCategory pc){
        return new Parameter(parameterCode, shortName, description, pc);
    }

    /**
     * Validates parameter instance globally,
     * checking if the parameter is null or duplicated.
     *
     * @param prm the parameter.
     *
     * @return true if the parameter isn't null or duplicated,
     * otherwise returns false.
     */
    public boolean validateParameter(Parameter prm){
        if (prm == null)
            return false;
        return ! this.prmList.contains(prm);
    }

    /**
     * Saves parameter instance the parameter store list,
     * validating the parameter before doing so.
     *
     * @param prm the parameter.
     *
     * @return true if the parameter is successfully validated,
     * otherwise return false.
     */
    public boolean saveParameter(Parameter prm){
        if (!validateParameter(prm))
            return false;
        return this.prmList.add(prm);
    }

    /*public Parameter[] toArray() {
        Parameter[] array = new Parameter[this.prmList.size()];
        return this.prmList.toArray(array);
    }*/
}
