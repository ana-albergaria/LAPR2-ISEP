package app.mappers;

import app.domain.model.Test;
import app.domain.model.TestType;
import app.mappers.dto.TestDTO;
import app.mappers.dto.TestTypeDTO;

import java.util.ArrayList;
import java.util.List;

public class TestMapper {
    public TestDTO toDTO(Test test) {
        return new TestDTO(test.getCode(), test.getNhsCode(), test.getClient(),
                test.getTestType(), test.getParameters(), test.getSamples(),
                test.getDiagnosisReport(), test.getDateOfTestRegistration(),
                test.getDateOfSamplesCollection(), test.getDateOfChemicalAnalysis(),
                test.getDateOfDiagnosis());
    }

    public List<TestDTO> toDTO(List<Test> tests) {
        List<TestDTO> testDTOS = new ArrayList<>();
        for (Test test : tests) {
            testDTOS.add(this.toDTO(test));
        }
        return testDTOS;
    }
}
