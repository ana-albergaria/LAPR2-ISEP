package app.domain.model;

public class Parameter {

    private String parameterCode;
    private String shortName;
    private String description;

    public Parameter(String parameterCode, String shortName, String description){
        checkParameterCodeRules(parameterCode);
        checkShortNameRules(shortName);
        checkDescriptionRules(description);
        this.parameterCode = parameterCode;
        this.shortName = shortName;
        this.description = description;
    }
    //+cat

    private void checkParameterCodeRules(String parameterCode){

    }

    private void checkShortNameRules(String shortName){

    }

    private void checkDescriptionRules(String description){

    }

}
