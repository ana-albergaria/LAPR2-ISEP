package app.domain.model;

import org.apache.commons.math3.distribution.TDistribution;

import java.util.ArrayList;
import java.util.List;

public class HypothesisTest {
    private MyRegressionModel myRegressionModel;
    private double tObsA;
    private double tObsB;
    private double tObsC;
    private double significanceLevel;
    private double critTD;

    private double f;
    private int numeratorDegreesOfFreedom;
    private int denominatorDegreesOfFreedom;

    public HypothesisTest(MyRegressionModel myRegressionModel,
                          double tObsA,
                          double tObsB,
                          double significanceLevel) {
        this.myRegressionModel = myRegressionModel;
        this.tObsA = tObsA;
        this.tObsB = tObsB;
        this.significanceLevel = significanceLevel;
        this.critTD = myRegressionModel.calculateCriticalValTStudent(significanceLevel);
    }

    public HypothesisTest(MyRegressionModel myRegressionModel,
                          double tObsA,
                          double tObsB,
                          double tObsC,
                          double significanceLevel) {
        this.myRegressionModel = myRegressionModel;
        this.tObsA = tObsA;
        this.tObsB = tObsB;
        this.tObsC = tObsC;
        this.significanceLevel = significanceLevel;
        this.critTD = myRegressionModel.calculateCriticalValTStudent(significanceLevel);
    }

    //for Anova Significance Model
    public HypothesisTest(MyRegressionModel myRegressionModel,
                          double f,
                          int numeratorDegreesOfFreedom,
                          int denominatorDegreesOfFreedom,
                          double significanceLevel) {
        this.myRegressionModel = myRegressionModel;
        this.f = f;
        this.numeratorDegreesOfFreedom = numeratorDegreesOfFreedom;
        this.denominatorDegreesOfFreedom = denominatorDegreesOfFreedom;
        this.significanceLevel = significanceLevel;
    }


    public String getDecision(double tObs, double critTD) {
        return (Math.abs(tObs) > critTD) ? "Reject H0" : "No Reject H0";
    }


    public String getDecisionForAnova(double critFD) {
        return (f > critFD) ? String.format("%f > f%.2f,(%d,%d)=%f%nReject H0\nThe regression model is significant.", f, significanceLevel, numeratorDegreesOfFreedom, denominatorDegreesOfFreedom, critFD) :
                String.format("%f <= f%.2f,(%d,%d)=%f%nNo Reject H0\nThe regression model is not significant.", f, significanceLevel, numeratorDegreesOfFreedom, denominatorDegreesOfFreedom, critFD);
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("Hypothesis tests for regression coefficients\n\n");
        text.append(String.format("--> Significance Level: %.2f%n%n", significanceLevel));
        text.append("H0:a=0 H1:a<>0\n");
        text.append(String.format("t_obs=%f%n", tObsA));
        text.append(String.format("Decision: %n%s%n", getDecision(tObsA, critTD)));
        text.append("//\n");
        text.append("H0:b=0 H1:b<>0\n");
        text.append(String.format("t_obs=%f%n", tObsB));
        text.append(String.format("Decision: %n%s%n", getDecision(tObsB, critTD)));
        text.append("//\n");
        if(myRegressionModel.getSecondIndVariable() != null) {
            text.append("H0:c=0 H1:c<>0\n");
            text.append(String.format("t_obs=%f%n", tObsC));
            text.append(String.format("Decision: %n%s%n", getDecision(tObsC, critTD)));
            text.append("//\n");
        }
        text.append("\n");

        return text.toString();
    }

    public String toStringForAnova() {
        StringBuilder text = new StringBuilder();
        text.append(String.format("Decision: f%n"));
        double critFD = myRegressionModel.calculateCriticalValFSnedecor(numeratorDegreesOfFreedom,
                denominatorDegreesOfFreedom, significanceLevel);
        text.append(String.format("%s%n", getDecisionForAnova(critFD)));
        return text.toString();
    }
}
