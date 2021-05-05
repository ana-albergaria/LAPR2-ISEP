package app.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Represents a Parameter through:
 * a code, a name, a description and a parameter category.
 *
 * @author Marta Ribeiro 1201592
 */

public class Parameter {

    /**
     * The parameter code.
     */
    private String parameterCode;

    /**
     * The parameter name.
     */
    private String shortName;

    /**
     * The parameter description.
     */
    private String description;

    /**
     * The parameter category of the parameter.
     */
    private ParameterCategory pc; //Parameter is presented under 1 ParameterCategory

    /**
     * Builds a parameter instance receiving:
     * the parameter code, name, description and parameter category.
     *
     * @param parameterCode the parameter code.
     * @param shortName the parameter name.
     * @param description the parameter description.
     * @param pc the parameter category of the parameter.
     */
    public Parameter(String parameterCode, String shortName, String description, ParameterCategory pc){
        checkParameterCodeRules(parameterCode);
        checkShortNameRules(shortName);
        checkDescriptionRules(description);
        checkParameterCategory(pc);
        this.parameterCode = parameterCode;
        this.shortName = shortName;
        this.description = description;
        this.pc = pc;
    }

    private void checkParameterCategory(ParameterCategory pc){
        if (pc == null)
            throw new IllegalArgumentException("The inserted parameter category code doesn't exist.");
    }

    /*public ParameterCategory getPc() {
        return pc;
    }*/

    /**
     * Checks if the parameter code received respects all the rules.
     *
     * @param parameterCode the parameter code.
     *
     * @return true if the parameter code received respects all the rules
     * (it isn't blank and it has a length of 5),
     * otherwise returns false.
     */
    private void checkParameterCodeRules(String parameterCode){
        if (StringUtils.isBlank(parameterCode))
            throw new IllegalArgumentException("Code cannot be blank.");
        if (parameterCode.length()!=5)
            throw new IllegalArgumentException("Code must have 5 chars.");
    }

    /**
     * Checks if the parameter name received respects all the rules.
     *
     * @param shortName the parameter name.
     *
     * @return true if the parameter name received respects all the rules
     * (it isn't blank and it hasn't a length longer than 8),
     * otherwise returns false.
     */
    private void checkShortNameRules(String shortName){
        if (StringUtils.isBlank(shortName))
            throw new IllegalArgumentException("Name cannot be blank.");
        if (shortName.length()>8)
            throw new IllegalArgumentException("Name must have up to 8 chars.");
    }

    /**
     * Checks if the parameter description respects all the rules.
     *
     * @param description the parameter description.
     *
     * @return true if the parameter description received respects all the rules
     * (it isn't blank and it hasn't a length longer than 20),
     * otherwise returns false.
     */
    private void checkDescriptionRules(String description){
        if (StringUtils.isBlank(description))
            throw new IllegalArgumentException("Description cannot be blank.");
        if (description.length()>20)
            throw new IllegalArgumentException("Description must have up to 20 chars.");
    }

}