package app.domain.model;

public class MyRegressionModel {
    private double intercept;
    private double slope;
    private Double secondIndVariable;
    private double r;
    private double r2;
    private Double r2Adjusted;
    private Object regressionModel;

    public MyRegressionModel(double intercept,
                             double slope,
                             Double secondIndVariable,
                             double r,
                             double r2,
                             Double r2Adjusted,
                             Object regressionModel) {
        this.intercept = intercept;
        this.slope = slope;
        this.secondIndVariable = secondIndVariable;
        this.r = r;
        this.r2 = r2;
        this.r2Adjusted = r2Adjusted;
        this.regressionModel = regressionModel;
    }


    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("The regression model fitted using data from the interval\n");
        if(secondIndVariable == null)
            text.append(String.format("^y=%fx + %f%n//%n", slope, intercept));
        else
            text.append(String.format("^y=%fx + %f%n//%n")); //corrigir - RLMúltipla
        text.append(String.format("Other statistics%nR2 = %f%n", r2));
        if(secondIndVariable == null)
            text.append(String.format("R = %f%n", r));
        else
            text.append(String.format("R2 adjusted = %f%n", r2Adjusted));
        text.append("//");

        return text.toString();
    }

    /*
    return String.format("The regression model fitted using data from the interval%n" +
                "^y=%fx + %f%n//%nOther statistics%nR2 = %f%nR2 adjusted = %f%nR = %f%n//",
                slope, intercept, r2, r2Adjusted, r);
     */
}
