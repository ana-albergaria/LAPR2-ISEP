package app.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Parameter {

    private String parameterCode;
    private String shortName;
    private String description;
    private ParameterCategory pc; //Parameter is presented under 1 ParameterCategory

    public Parameter(String parameterCode, String shortName, String description, ParameterCategory pc){
        checkParameterCodeRules(parameterCode);
        checkShortNameRules(shortName);
        checkDescriptionRules(description);
        this.parameterCode = parameterCode;
        this.shortName = shortName;
        this.description = description;
        this.pc = pc;
    }

    /*public ParameterCategory getPc() {
        return pc;
    }*/

    private void checkParameterCodeRules(String parameterCode){
        if (StringUtils.isBlank(parameterCode))
            throw new IllegalArgumentException("Code cannot be blank.");
        if (parameterCode.length()!=5)
            throw new IllegalArgumentException("Code must have 5 chars.");
    }

    private void checkShortNameRules(String shortName){
        if (StringUtils.isBlank(shortName))
            throw new IllegalArgumentException("Name cannot be blank.");
        if (shortName.length()>8)
            throw new IllegalArgumentException("Name must have up to 8 chars.");
    }

    private void checkDescriptionRules(String description){
        if (StringUtils.isBlank(description))
            throw new IllegalArgumentException("Description cannot be blank.");
        if (description.length()>20)
            throw new IllegalArgumentException("Description must have up to 20 chars.");
    }

}
