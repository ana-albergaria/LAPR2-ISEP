package app.mappers.dto;

import app.domain.model.Client;
import app.domain.model.Parameter;
import app.domain.model.Sample;
import app.domain.model.TestType;

import java.util.List;

public class TestDTO {
    /**
     * Max lenght of nhs code field
     */
    private static final int NHS_CODE_MAX_LENGTH = 12;

    /**
     * Auto generated sequencial number with 12 digits
     */
    private String code;

    /**
     * National health system code of a given test
     */
    private String nhsCode;

    /**
     * Client object which has solicited a test
     */
    private Client client;

    /**
     * Type of test to be conduted
     */
    private TestType testType;

    /**
     * Samples which the test contains
     */
    private List<Sample> samples;

    /**
     * List of parameters to be measured of a given test
     */
    private List<Parameter> parameters;

    /**
     * Number of existing tests.
     */
    private static int totalTests = 0;

    /**
     * Constructor only without samples, since in the business process they are added later on
     * @param nhsCode National health system code of a given test
     * @param client Client object which has solicited a test
     * @param testType Type of test to be conduted
     * @param parameters List of parameters to be measured of a given test
     */
    public TestDTO(String code, String nhsCode, Client client, TestType testType, List<Parameter> parameters, List<Sample> samples) {
        totalTests++;
        this.code = code;
        this.nhsCode = nhsCode;
        this.client = client;
        this.testType = testType;
        this.parameters = parameters;
        this.samples = samples;
    }

    public String getCode() {
        return code;
    }
}
