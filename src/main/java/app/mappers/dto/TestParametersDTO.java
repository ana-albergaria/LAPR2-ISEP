package app.mappers.dto;

import app.domain.model.Parameter;
import app.domain.model.TestParameterResult;

public class TestParametersDTO {

    private Parameter parameter;
    private TestParameterResult testResult;

    public TestParametersDTO(Parameter parameter, TestParameterResult testResult){
        this.parameter = parameter;
        this.testResult = testResult;
    }

    public Parameter getParameter(){
        return parameter;
    }

    public TestParameterResult getTestParameterResult(){
        return testResult;
    }

    //get result

    //get ref value

    /*@Override
    public String toString(){
        return String.format("Parameter:%s%nResult: %s%nReference Value: %s%n");
    }*/

}
