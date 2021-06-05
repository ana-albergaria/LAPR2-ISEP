package app.domain.model;

import org.apache.commons.math3.distribution.TDistribution;

import java.util.ArrayList;
import java.util.List;

public class HypothesisTest {
    private MyRegressionModel myRegressionModel;
    private double tObsA;
    private double tObsB;
    private List<Double> significanceLevels;

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
    
    public double calculateCriticalValTStudent(double levelOfSignificance) {
        int n = myRegressionModel.getNumberOfObservations();
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

    public String getDecision(double tObs, double critTD) {
        return (Math.abs(tObs) > critTD) ? "Reject H0" : "No Reject H0";
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("Hypothesis tests for regression coefficients\n\n");
        for (Double significanceLevel : significanceLevels) {
            text.append(String.format("--> Significance Level: %.2f%n%n", significanceLevel));
            text.append("H0:a=0 H1:a<>0\n");
            text.append(String.format("t_obs=%f%n", tObsA));
            double critTD1 = calculateCriticalValTStudent(significanceLevel);
            text.append(String.format("Decision: %n%s%n", getDecision(tObsA, critTD1)));
            text.append("//\n");
            text.append("H0:b=0 H1:b<>0\n");
            text.append(String.format("t_obs=%f%n", tObsB));
            text.append(String.format("Decision: %n%s%n", getDecision(tObsB, critTD1)));
            text.append("//\n\n");
        }
        return text.toString();
    }
}
