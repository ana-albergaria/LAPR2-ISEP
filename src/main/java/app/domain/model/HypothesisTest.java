package app.domain.model;

import org.apache.commons.math3.distribution.TDistribution;

import java.util.ArrayList;
import java.util.List;

public class HypothesisTest {
    private MyRegressionModel myRegressionModel;
    private double tObsA;
    private double tObsB;
    private List<Double> significanceLevels;

    private double f;
    private int numeratorDegreesOfFreedom;
    private int denominatorDegreesOfFreedom;

    private static final int DEGREES_OF_FREEDOM_TSTUDENT = 2;
    private static final double SIGNIFICANCE_LEVEL_1 = 0.01;
    private static final double SIGNIFICANCE_LEVEL_5 = 0.05;

    public HypothesisTest(MyRegressionModel myRegressionModel,
                          double tObsA,
                          double tObsB) {
        this.myRegressionModel = myRegressionModel;
        this.tObsA = tObsA;
        this.tObsB = tObsB;
        this.significanceLevels = new ArrayList<>();
        this.significanceLevels.add(SIGNIFICANCE_LEVEL_1);
        this.significanceLevels.add(SIGNIFICANCE_LEVEL_5);
    }

    //for Anova Significance Model
    public HypothesisTest(MyRegressionModel myRegressionModel,
                          double f,
                          int numeratorDegreesOfFreedom,
                          int denominatorDegreesOfFreedom) {
        this.myRegressionModel = myRegressionModel;
        this.f = f;
        this.numeratorDegreesOfFreedom = numeratorDegreesOfFreedom;
        this.denominatorDegreesOfFreedom = denominatorDegreesOfFreedom;
    }


    public String getDecision(double tObs, double critTD) {
        return (Math.abs(tObs) > critTD) ? "Reject H0" : "No Reject H0";
    }


    public String getDecisionForAnova(double critFD) {
        return (f > critFD) ? String.format("%f > f%.2f,(%d,%d)=%f%nReject H0\nThe regression model is significant.", f, SIGNIFICANCE_LEVEL_5, numeratorDegreesOfFreedom, denominatorDegreesOfFreedom, critFD) :
                String.format("%f <= f%.2f,(%d,%d)=%f%nNo Reject H0\nThe regression model is not significant.", f, SIGNIFICANCE_LEVEL_5, numeratorDegreesOfFreedom, denominatorDegreesOfFreedom, critFD);
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("Hypothesis tests for regression coefficients\n\n");
        for (Double significanceLevel : significanceLevels) {
            text.append(String.format("--> Significance Level: %.2f%n%n", significanceLevel));
            text.append("H0:a=0 H1:a<>0\n");
            text.append(String.format("t_obs=%f%n", tObsA));
            double critTD1 = myRegressionModel.calculateCriticalValTStudent(significanceLevel);
            text.append(String.format("Decision: %n%s%n", getDecision(tObsA, critTD1)));
            text.append("//\n");
            text.append("H0:b=0 H1:b<>0\n");
            text.append(String.format("t_obs=%f%n", tObsB));
            text.append(String.format("Decision: %n%s%n", getDecision(tObsB, critTD1)));
            text.append("//\n\n");
        }
        return text.toString();
    }

    public String toStringForAnova() {
        StringBuilder text = new StringBuilder();
        text.append(String.format("Decision: f%n"));
        double critFD = myRegressionModel.calculateCriticalValFSnedecor(numeratorDegreesOfFreedom,
                denominatorDegreesOfFreedom, SIGNIFICANCE_LEVEL_5);
        text.append(String.format("%s%n", getDecisionForAnova(critFD)));
        return text.toString();
    }
}
