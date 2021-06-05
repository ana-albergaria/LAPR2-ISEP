package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class SignificanceModelAnova {
    private MyRegressionModel myRegressionModel;
    private double sr;
    private double se;
    private double st;
    private double msr;
    private double mse;
    private double f;
    private int deg_freedom_sr;
    private int deg_freedom_se;
    private int deg_freedom_st;

    public SignificanceModelAnova(MyRegressionModel myRegressionModel,
                                  double sr,
                                  double se) {
        this.myRegressionModel = myRegressionModel;
        if(myRegressionModel.getSecondIndVariable() == null) {
            deg_freedom_sr = 1;
            deg_freedom_se = myRegressionModel.getNumberOfObservations() - 2;
            deg_freedom_st = myRegressionModel.getNumberOfObservations() - 1;
        }
        this.sr = sr;
        this.se = se;
        this.st = sr + se;
        this.msr = sr / deg_freedom_sr;
        this.mse = se / deg_freedom_se;
        this.f = msr / mse;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("Significance model with Anova\n");
        text.append("H0: b=0  H1:b<>0\n");
        text.append(String.format("%-20s%-20s%-20s%-20s%-20s\n", "", "df", "SS", "MS","F"));
        text.append(String.format("%-20s%-20d%-20f%-20f%-20f\n", "Regression", deg_freedom_sr, sr, msr,f));
        text.append(String.format("%-20s%-20d%-20f%-20f\n", "Residual", deg_freedom_se, se, mse));
        text.append(String.format("%-20s%-20d%-20f\n", "Total", deg_freedom_st, st));

        return text.toString();
    }
}
