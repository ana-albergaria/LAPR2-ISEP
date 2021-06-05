package app.domain.model;

public class MyRegressionModel {
    private double intercept; //intercept - y, a
    private double slope; //slope - x, b
    private Double secondIndVariable;
    private double r;
    private double r2;
    private double r2Adjusted;
    private int numberOfObservations;
    private Object regressionModel;

    public MyRegressionModel(double intercept,
                             double slope,
                             Double secondIndVariable,
                             double r,
                             double r2,
                             double r2Adjusted,
                             int numberOfObservations,
                             Object regressionModel) {
        this.intercept = intercept;
        this.slope = slope;
        this.secondIndVariable = secondIndVariable;
        this.r = r;
        this.r2 = r2;
        this.r2Adjusted = r2Adjusted;
        this.numberOfObservations = numberOfObservations;
        this.regressionModel = regressionModel;
    }

    public double getSlope() {
        return slope;
    }

    public double getIntercept() {
        return intercept;
    }

    public int getNumberOfObservations() {
        return numberOfObservations;
    }

    public Object getRegressionModel() {
        return regressionModel;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("The regression model fitted using data from the interval\n");
        if(secondIndVariable == null)
            text.append(String.format("^y=%fx + %f%n//%n", slope, intercept));
        else
            text.append(String.format("^y=%fx + %f%n//%n")); //corrigir - RLMúltipla
        text.append(String.format("Other statistics%nR2 = %f%nR2 adjusted = %f%n", r2, r2Adjusted));
        if(secondIndVariable == null)
            text.append(String.format("R = %f%n", r));
        text.append("//\n\n");

        return text.toString();
    }



    /*
    return String.format("The regression model fitted using data from the interval%n" +
                "^y=%fx + %f%n//%nOther statistics%nR2 = %f%nR2 adjusted = %f%nR = %f%n//",
                slope, intercept, r2, r2Adjusted, r);
     */
}
