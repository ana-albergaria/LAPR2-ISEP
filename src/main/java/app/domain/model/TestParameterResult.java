package app.domain.model;

/**
 * Class of a Test parameter result which stores also the metric used and the reference value for the parameter
 */
public class TestParameterResult {

    /**
     * result value of a given parameter of test
     */
    private Double value;

    /**
     * metric being used in current parameter testing
     */
    private String metric;

    /**
     * Reference value of the analysed parameter
     */
    private MyReferenceValue myReferenceValue;

    /**
     * Constructor of the object
     * @param value Result value of a given parameter of test
     * @param metric Metric being used in current parameter testing
     * @param myReferenceValue Reference value of the analysed parameter
     */
    public TestParameterResult(Double value, String metric, MyReferenceValue myReferenceValue) {
        this.value = value;
        this.metric = metric;
        this.myReferenceValue = myReferenceValue;
    }

    public Double getResultValue(){
        return value;
    }

    public String getResultMetric(){
        return metric;
    }

    public MyReferenceValue getResultReferenceValue(){
        return myReferenceValue;
    }

    @Override
    public String toString() {
        return String.format("TEST RESULT:%nValue: %s%nMetric: %s%nRef.Value: %s%n", value, metric, myReferenceValue);
    }
}
