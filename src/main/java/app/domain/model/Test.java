package app.domain.model;

import app.domain.interfaces.ExternalModule;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private final String code;

    /**
     * National health system code of a given test
     */
    private final String nhsCode;

    /**
     * Client object which has solicited a test
     */
    private final Client client;

    /**
     * Type of test to be conduted
     */
    private final TestType testType;

    /**
     * Report of test results
     */
    private Report diagnosisReport;

    /**
     * Samples which the test contains
     */
    private List<Sample> samples;

    /**
     * List of parameters to be measured of a given test
     */
    private final List<TestParameter> testParameters;

    /**
     * Date of test registration
     */
    private final String dateOfTestRegistration;

    /**
     * Date of samples collection
     */
    private String dateOfSamplesCollection;

    /**
     * Date of chemical analysis
     */
    private String dateOfChemicalAnalysis;

    /**
     * Date of diagnosis
     */
    private String dateOfDiagnosis;

    /**
     * Number of existing tests.
     */
    private static int totalTests = 0;

    /**
     * Constructor only without samples, since in the business process they are added later on,
     * also generates the attribute code, test registration time and makes the list of parameter into test parameters
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
        this.testParameters = addTestParameters(parameters);
        this.dateOfTestRegistration = generateNowDateAndTime();
        this.diagnosisReport = null;
        this.samples = new ArrayList<>();
        this.dateOfSamplesCollection = "n/a";
        this.dateOfChemicalAnalysis = "n/a";
        this.dateOfDiagnosis = "n/a";
    }

    public String getCode(){
        return code;
    }

    public List<Sample> getSamples() {
        return new ArrayList<>(samples);
    }

    public Report getDiagnosisReport(){
        return diagnosisReport;
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

    public List<TestParameter> getParameters() {
        return new ArrayList<>(testParameters);
    }

    public boolean addSample(Sample sample) {
        if(sample != null)
            return this.samples.add(sample);
        return false;
    }

    /**
     * Adds the Sample Collecting Date to the Test for which the samples where collected.
     */
    public void addSampleCollectionDate(){
        this.dateOfSamplesCollection = generateNowDateAndTime();
    }

    public void addChemicalAnalysisDate(){
        this.dateOfChemicalAnalysis = generateNowDateAndTime();
    }

    /**
     * Generates a sequencial code based on the number of existing tests
     * @return 12 digits sequencial number of current test.
     */
    private String generateCode(){
        return String.format("%012d", totalTests);
    }

    private String generateNowDateAndTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return simpleDateFormat.format(new Date());
    }

    /**
     * Method for turning a list of Parameter objects into test parameters object which are stored into the test
     * @param parameters parameters to be tested
     * @return list of test parameters objects
     */
    private List<TestParameter> addTestParameters(List<Parameter> parameters){
        List<TestParameter> testParameters = new ArrayList<>();
        for(Parameter parameter : parameters){
            testParameters.add(new TestParameter(parameter));
        }
        return testParameters;
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

    /**
     * Method to verify if the test has Samples collected.
     *
     * @return true if the test has samples collected.
     *          Otherwise, it returns false.
     */
    public boolean hasSamples() {
        return this.samples.size() > 0;
    }

    /**
     * Method to verify if the test has Samples analysed.
     *
     * @return true if the test has samples analysed,
     * otherwise return false.
     */
    public boolean hasSamplesAnalysed(){
        int f = 0;
        List<TestParameter> testParameters = this.getParameters();
        for (TestParameter testParameter : testParameters){
            if (testParameter.getTestParameterResult() == null){
                f++;
            }
        }
        return f == 0;
    }

    /**
     * Method to store the test report object into the test.
     *
     * @param report the test report.
     */
    public void addReport(Report report) {
        this.diagnosisReport = report;
        this.dateOfDiagnosis = generateNowDateAndTime();
    }

    public void addTestResult(String parameterCode, Double result, String metric) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        TestParameter testParameter = getTestParameterFor(parameterCode);
        Parameter selectedParameter = testParameter.getParameter();
        ExternalModule api = this.testType.getExternalModule();
        MyReferenceValue refValue = api.getReferenceValue(selectedParameter);
        testParameter.addResult(result, metric, refValue);
    }

    private TestParameter getTestParameterFor(String parameterCode) {
        for (TestParameter testParameter : this.testParameters) {
            if(parameterCode.equals(testParameter.getParameter().getPrmCode())) {
                return testParameter;
            }
        }
        throw new UnsupportedOperationException("Test Parameter not found!");
    }

    @Override
    public String toString() {
        return String.format("TEST CODE %s%n* NHS Code: %s%n* Client name: %s%n* Test Type: %s%n* Collection Method: %s%n* " +
                        "Parameters: %s%nDate of test registration:%s%n Date of sample collection:%s%nDate of chemical analysis: %s%nDate of diagnosis: %s%n",
                code, nhsCode, client.getName(), testType, testType.getCollectingMethod(), testParameters,
                dateOfTestRegistration, dateOfSamplesCollection, dateOfChemicalAnalysis, dateOfDiagnosis);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Test)) return false;
        Test test = (Test) o;
        return code.equalsIgnoreCase(test.code) &&
                nhsCode.equalsIgnoreCase(test.nhsCode) &&
                client.equals(test.client) &&
                testType.equals(test.testType) &&
                dateOfTestRegistration.equals(test.dateOfTestRegistration);
    }
}
