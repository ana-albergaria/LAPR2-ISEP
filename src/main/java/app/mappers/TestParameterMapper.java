package app.mappers;

import app.domain.model.TestParameter;
import app.mappers.dto.TestParametersDTO;

import java.util.ArrayList;
import java.util.List;

public class TestParameterMapper {

    public TestParametersDTO toDTO(TestParameter testParameter){
        return new TestParametersDTO(testParameter.getParameter(),testParameter.getTestParameterResult());
    }

    public List<TestParametersDTO> toDTO(List<TestParameter> testParameters){
        List<TestParametersDTO> testParametersDTO = new ArrayList<>();
        for (TestParameter testParameter : testParameters){
            testParametersDTO.add(this.toDTO(testParameter));
        }
        return testParametersDTO;
    }

}
