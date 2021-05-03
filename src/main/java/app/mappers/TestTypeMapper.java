package app.mappers;

import app.mappers.dto.TestTypeDTO;
import app.domain.model.TestType;

import java.util.ArrayList;
import java.util.List;

public class TestTypeMapper {

    public TestTypeDTO toDTO(TestType tt) {
        return new TestTypeDTO(tt.getCode(), tt.getDescription(),
                                tt.getCollectingMethod(), tt.getSelectedCategories());
    }

    public List<TestTypeDTO> toDTO(List<TestType> testTypes) {
        List<TestTypeDTO> testTypesDTOS = new ArrayList<>();
        for (TestType testType : testTypes) {
            testTypesDTOS.add(this.toDTO(testType));
        }
        return testTypesDTOS;
    }

}
