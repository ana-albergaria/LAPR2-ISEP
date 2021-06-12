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

    private Date dateOfTestRegistration;

    private Date dateOfChemicalAnalysis;

    private Date dateOfDiagnosis;

    private String labId;

    public TestFileDTO(ClientDTO clientDTO, String nhsCode, String testTypeCode, List<String> testParameterCodes, List<Double> testParameterResults,
                       Date dateOfTestRegistration, Date dateOfChemicalAnalysis, Date dateOfDiagnosis, String labId) {
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

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public String getNhsCode() {
        return nhsCode;
    }

    public String getTestTypeCode() {
        return testTypeCode;
    }

    public List<String> getTestParameterCodes() {
        return testParameterCodes;
    }

    public List<Double> getTestParameterResults() {
        return testParameterResults;
    }

    public Date getDateOfTestRegistration() {
        return dateOfTestRegistration;
    }

    public Date getDateOfChemicalAnalysis() {
        return dateOfChemicalAnalysis;
    }

    public Date getDateOfDiagnosis() {
        return dateOfDiagnosis;
    }

    public String getLabId() {
        return labId;
    }

    @Override
    public String toString() {
        return String.format("TEST FILE DTO:%n Client:%s%nNhsCode:%s%nTestParameters:%s%nTestParameterResults:%s%nTestType:%s%nDates:%s%n%s%n%s%n",
                clientDTO, nhsCode, testParameterCodes, testParameterResults, testTypeCode, dateOfTestRegistration, dateOfChemicalAnalysis, dateOfDiagnosis);
    }
}
