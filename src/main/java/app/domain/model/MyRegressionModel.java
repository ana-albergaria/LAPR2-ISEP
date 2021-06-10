package app.domain.model;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;

import java.util.Arrays;

public class MyRegressionModel {
    private final double intercept; //intercept - y, a
    private final double slope; //slope - x, b
    private Double secondIndVariable;
    private final double r;
    private final double r2;
    private final double r2Adjusted;
    private final int numberOfObservations;
    private final Object regressionModel;

    private static final int DEGREES_OF_FREEDOM_TSTUDENT = 2;

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

    public MyRegressionModel(double intercept,
                             double slope,
                             double r,
                             double r2,
                             double r2Adjusted,
                             int numberOfObservations,
                             Object regressionModel) {
        this.intercept = intercept;
        this.slope = slope;
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
                                                double significanceLevel) {
        FDistribution fd = new FDistribution(numeratorDegreesOfFreedom,denominatorDegreesOfFreedom);
        double alphaFD = significanceLevel;
        double critFD = fd.inverseCumulativeProbability(1- alphaFD);
        return critFD;
    }

    public double calculateCriticalValTStudent(double levelOfSignificance) {
        int n = numberOfObservations;
        double critTD;
        TDistribution td = new TDistribution(n - DEGREES_OF_FREEDOM_TSTUDENT);
        double alphaTD = 1 - (levelOfSignificance / 2);
        if(alphaTD > 0.5) {
            critTD = td.inverseCumulativeProbability(alphaTD);
        }
        else {
            critTD = td.inverseCumulativeProbability(1 - alphaTD);
        }
        return critTD;
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

    @Override
    public boolean equals(Object otherObject) {
        if(this == otherObject)
            return true;

        if(otherObject == null || this.getClass() != otherObject.getClass())
            return false;

        MyRegressionModel otherMyRegressionModel = (MyRegressionModel) otherObject;

        return this.intercept == otherMyRegressionModel.intercept &&
                this.slope == otherMyRegressionModel.slope &&
                this.secondIndVariable == otherMyRegressionModel.secondIndVariable &&
                this.r == otherMyRegressionModel.r &&
                this.r2 == otherMyRegressionModel.r2 &&
                this.r2Adjusted == otherMyRegressionModel.r2Adjusted &&
                this.numberOfObservations == otherMyRegressionModel.numberOfObservations;
    }
}
