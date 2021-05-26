package app.domain.model;


import java.util.List;

/**
 * Container of one of the test parameters and its test result
 *
 * @author João Wolff
 */
public class TestParameter {

    /**
     * Parameter to be evaluated for the a test.
     */
    private Parameter parameter;
    /**
     * Result of the parameter testing evaluation.
     */
    private TestParameterResult testResult;

    /**
     * Constructor for a test parameter only contains the parameter cause the result of the testing
     * is never added in the same time as the parameter.
     * @param parameter parameter to be tested.
     */
    public TestParameter(Parameter parameter){
        this.parameter = parameter;
        testResult = null;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void addResult(Double result, String metric, MyReferenceValue refValue) {
        this.testResult = new TestParameterResult(result, metric, refValue);
    }

    //addResult(result, metric, refValue)

    /*public List<TestParameterResult> getParameterResult(List<TestParameter> listTestParams){

    }*/

}
