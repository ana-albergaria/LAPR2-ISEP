package app.domain.interfaces;

import app.domain.model.ConfidenceInterval;
import app.domain.model.HypothesisTest;
import app.domain.model.MyRegressionModel;
import app.domain.model.SignificanceModelAnova;

import java.util.Date;
import java.util.List;

public interface RegressionModel {
    public abstract MyRegressionModel getRegressionModel(double[] x1, double[] x2, double[] y, int historicalPoints);
    public abstract HypothesisTest getHypothesisTest(MyRegressionModel myRegressionModel);
    public abstract SignificanceModelAnova getSignificanceModelAnova(MyRegressionModel myRegressionModel);
    public abstract List<Double> getEstimatedPositives(MyRegressionModel myRegressionModel);
    public abstract ConfidenceInterval getConfidenceInterval(MyRegressionModel myRegressionModel, double x0);
}

