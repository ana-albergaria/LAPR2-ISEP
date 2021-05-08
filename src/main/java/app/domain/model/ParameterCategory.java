package app.domain.model;

import org.apache.commons.lang3.StringUtils;

public class ParameterCategory {
    private String code;
    private String name;

    public ParameterCategory(String code, String name){
        checkCodeRules(code);
        checkNameRules(name);
        this.code = code;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return String.format("Code: %s%nName: %s%n", code, name);
    }

    private void checkCodeRules(String code){
        if (StringUtils.isBlank(code))
            throw new IllegalArgumentException("Code cannot be blank.");
        if ((code.length() != 5 || !StringUtils.isAlphanumeric(code)))
            throw new IllegalArgumentException("Code must have 5 alphanumeric characters.");
    }

    public void checkNameRules(String name){
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be blank.");
        if (name.length() > 10)
            throw new IllegalArgumentException("Name cannot have more then 10 characters.");
    }

    @Override
    public boolean equals(Object otherObject) {
        if(this == otherObject)
            return true;

        if(otherObject == null || this.getClass() != otherObject.getClass())
            return false;

        ParameterCategory otherParameterCategory = (ParameterCategory) otherObject;

        return  this.code.equalsIgnoreCase(otherParameterCategory.code) &&
                this.name.equalsIgnoreCase(otherParameterCategory.name);
    }

}
