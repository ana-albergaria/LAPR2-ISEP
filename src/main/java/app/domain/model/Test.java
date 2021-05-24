package app.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Class of the Test to be performed to a client
 *
 * @author João Wolff
 */
public class Test {

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
    public Test(String nhsCode, Client client, TestType testType, List<Parameter> parameters) {
        checkNhsCode(nhsCode);
        totalTests++;
        this.code = generateCode();
        this.nhsCode = nhsCode;
        this.client = client;
        this.testType = testType;
        this.parameters = parameters;
        this.samples = new ArrayList<>();
    }

    public String getCode(){
        return code;
    }

    public List<Sample> getSamples() {
        return new ArrayList<>(samples);
    }

    public String getNhsCode() {
        return nhsCode;
    }

    public Client getClient() {
        return client;
    }

    public TestType getTestType() {
        return testType;
    }

    public List<Parameter> getParameters() {
        return new ArrayList<>(parameters);
    }

    //Como mudar o estado do teste? Responsabilidade do test? (if samples.size() == x)
    //Como mudar o estado do teste? Responsabilidade do test? sim
    public boolean addSample(Sample sample) {
        if(sample != null)
            return this.samples.add(sample);
        return false;
    }

    /**
     * Generates a sequencial code based on the number of existing tests
     * @return 12 digits sequencial number of current test.
     */
    public String generateCode(){
        return String.format("%12d", totalTests);
    }

    /**
     * Nhs code attribute validation for having non alphanumeric characters, more or less then 12 characters or blank
     *
     * @param code Test type's code
     */
    private void checkNhsCode(String code) {
        if (StringUtils.isBlank(code))
            throw new IllegalArgumentException("Nhs code cannot be blank.");
        if ((code.length() != NHS_CODE_MAX_LENGTH))
            throw new IllegalArgumentException("Nhs code must hold 12 alphanumeric characters");
        if (!StringUtils.isAlphanumeric(code))
            throw new IllegalArgumentException("Nhs code must only have alphanumeric characters.");
    }

}
