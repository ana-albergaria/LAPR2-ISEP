package app.domain.model;

import org.apache.commons.math3.distribution.FDistribution;

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

    public Double getSecondIndVariable() {
        return secondIndVariable;
    }

    public Object getRegressionModel() {
        return regressionModel;
    }

    public double calculateCriticalValFSnedecor(int numeratorDegreesOfFreedom,
                                                int denominatorDegreesOfFreedom,
                                                double levelOfSignificance) {
        FDistribution fd = new FDistribution(numeratorDegreesOfFreedom,denominatorDegreesOfFreedom);
        double alphaFD = levelOfSignificance;
        double critFD = fd.inverseCumulativeProbability(1- alphaFD);
        return critFD;
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
        text.append("//\n");

        return text.toString();
    }



    /*
    return String.format("The regression model fitted using data from the interval%n" +
                "^y=%fx + %f%n//%nOther statistics%nR2 = %f%nR2 adjusted = %f%nR = %f%n//",
                slope, intercept, r2, r2Adjusted, r);
     */
}
