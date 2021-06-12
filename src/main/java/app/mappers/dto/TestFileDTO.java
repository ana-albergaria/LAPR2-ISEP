package app.mappers.dto;

import app.domain.model.*;

import java.util.Date;
import java.util.List;

public class TestFileDTO {

    ClientDTO clientDTO;

    private String nhsCode;

    private String testTypeCode;

    private List<String> testParameterCodes;

    private List<Double> testParameterResults;

    private String dateOfTestRegistration;

    private String dateOfChemicalAnalysis;

    private String dateOfDiagnosis;

    private String labId;

    public TestFileDTO(ClientDTO clientDTO, String nhsCode, String testTypeCode, List<String> testParameterCodes, List<Double> testParameterResults,
                       String dateOfTestRegistration, String dateOfChemicalAnalysis, String dateOfDiagnosis, String labId) {
        this.clientDTO = clientDTO;
        this.nhsCode = nhsCode;
        this.testTypeCode = testTypeCode;
        this.testParameterCodes = testParameterCodes;
        this.testParameterResults = testParameterResults;
        this.dateOfTestRegistration = dateOfTestRegistration;
        this.dateOfChemicalAnalysis = dateOfChemicalAnalysis;
        this.dateOfDiagnosis = dateOfDiagnosis;
        this.labId = labId;
    }

    @Override
    public String toString() {
        return String.format("TEST FILE DTO:%n Client:%s%nNhsCode:%s%nTestParameters:%s%nTestParameterResults:%s%nTestType:%s%nDates:%s%n%s%n%s%n",
                clientDTO, nhsCode, testParameterCodes, testParameterResults, testTypeCode, dateOfTestRegistration, dateOfChemicalAnalysis, dateOfDiagnosis);
    }
}
