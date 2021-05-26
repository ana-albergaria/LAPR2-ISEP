package app.domain.model;

public class MyReferenceValue {
    private Double minValue;
    private Double maxValue;

    public MyReferenceValue(Double minValue, Double maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public Double getMinValue() {
        return minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }
}
