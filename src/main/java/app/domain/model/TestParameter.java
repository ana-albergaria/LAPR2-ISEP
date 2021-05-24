package app.domain.model;


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
    private TestParameterResult testParameterResult;

    /**
     * Constructor for a test parameter only contains the parameter cause the result of the testing
     * is never added in the same time as the parameter.
     * @param parameter parameter to be tested.
     */
    public TestParameter(Parameter parameter){
        this.parameter = parameter;
    }

    public Parameter getParameter() {
        return parameter;
    }

    //addResult(result, metric, refValue)

}
