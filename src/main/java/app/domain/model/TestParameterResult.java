package app.domain.model;

public class TestParameterResult {
    private Double value;
    private String metric;
    private MyReferenceValue myReferenceValue;

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

}
