package app.domain.model;

import org.apache.commons.math3.distribution.TDistribution;

public class HypothesisTest {
    private MyRegressionModel myRegressionModel;
    private double a;
    private double b;
    private double tObsA;
    private double tObsB;

    private static final int DEGREES_OF_FREEDOM_TSTUDENT = 2;
    private static final double LEVEL_OF_SIGNIFICANCE_1 = 0.01;
    private static final double LEVEL_OF_SIGNIFICANCE_5 = 0.05;

    public HypothesisTest(MyRegressionModel myRegressionModel,
                          double a,
                          double b,
                          double tObsB,
                          double tObsA) {
        this.myRegressionModel = myRegressionModel;
        this.a = a;
        this.b = b;
        this.tObsA = tObsA;
        this.tObsB = tObsB;
    }

    //CORRIGIR!
    public double calculateCriticalValTStudent(double levelOfSignificance) {
        double critTD;
        TDistribution td = new TDistribution(DEGREES_OF_FREEDOM_TSTUDENT);
        double alphaTD = levelOfSignificance;
        if(alphaTD> 0.5) {
            critTD = td.inverseCumulativeProbability(alphaTD);
            //System.out.println("t-student critical value: " + critTD);
        }
        else {
            critTD = td.inverseCumulativeProbability(1 - alphaTD);
            //System.out.println("t-student critical value: " + critTD);
        }
        return critTD;
    }

    public double getTObsB() {
        return tObsB;
    }

    public String getDecision(double tObs, double critTD) {
        return (Math.abs(tObs) > critTD) ? "Reject H0" : "No Reject H0";
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("Hypothesis tests for regression coefficients\n\n");
        text.append(String.format("--> Level of Significance: %.2f%n%n", LEVEL_OF_SIGNIFICANCE_1));
        text.append("H0:a=0 H1:a<>0\n");
        text.append(String.format("t_obs=%f%n", tObsA));
        double critTdA = calculateCriticalValTStudent(LEVEL_OF_SIGNIFICANCE_1);
        text.append(String.format("Decision: %n%s%n", getDecision(tObsA, critTdA)));
        text.append("//\n");
        text.append("H0:b=0 H1:b<>0\n");
        text.append(String.format("t_obs=%f%n", tObsB));
        double critTdB = calculateCriticalValTStudent(LEVEL_OF_SIGNIFICANCE_1);
        text.append(String.format("Decision: %n%s%n", getDecision(tObsB, critTdB)));
        text.append("//\n");
        return text.toString();
    }
}
