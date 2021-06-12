package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class SignificanceModelAnova {
    private MyRegressionModel myRegressionModel;
    private HypothesisTest testRegSignificance;
    private double significanceLevel;

    private final double sr;
    private final double se;
    private final double st;
    private final double msr;
    private final double mse;
    private final double f;
    private final int deg_freedom_sr;
    private final int deg_freedom_se;
    private final int deg_freedom_st;

    private static final int NUM_REG_COEFFICIENTS_SLR = 1; //sem contar com o da var independente
    private static final int NUM_REG_COEFFICIENTS_MLR = 2; //sem contar com o da var independente

    public SignificanceModelAnova(MyRegressionModel myRegressionModel,
                                  double sr,
                                  double se,
                                  double significanceLevel) {
        this.myRegressionModel = myRegressionModel;
        //k - coefficients of regression but without the first one - B0
        int k = (myRegressionModel.getSecondIndVariable() == null) ? NUM_REG_COEFFICIENTS_SLR : NUM_REG_COEFFICIENTS_MLR;
        this.deg_freedom_sr = k;
        this.deg_freedom_se = myRegressionModel.getNumberOfObservations() - (k + 1);
        this.deg_freedom_st = myRegressionModel.getNumberOfObservations() - 1;
        this.sr = sr;
        this.se = se;
        this.st = sr + se;
        this.msr = sr / deg_freedom_sr;
        this.mse = se / deg_freedom_se;
        this.f = msr / mse;
        this.significanceLevel = significanceLevel;
        this.testRegSignificance = new HypothesisTest(myRegressionModel, f, deg_freedom_sr, deg_freedom_se, significanceLevel);
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("Significance model with Anova\n");
        text.append("H0: b=0  H1:b<>0\n");
        text.append(String.format("%-20s%-20s%-20s%-20s%-20s%n", "", "df", "SS", "MS","F"));
        text.append(String.format("%-20s%-20d%-20f%-20f%-20f%n", "Regression", deg_freedom_sr, sr, msr,f));
        text.append(String.format("%-20s%-20d%-20f%-20f%n", "Residual", deg_freedom_se, se, mse));
        text.append(String.format("%-20s%-20d%-20f%n%n", "Total", deg_freedom_st, st));
        text.append(testRegSignificance.toStringForAnova());
        return text.toString();
    }
}
