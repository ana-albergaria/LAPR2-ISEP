package app.domain.interfaces;

import app.domain.model.ConfidenceInterval;
import app.domain.model.HypothesisTest;
import app.domain.model.MyRegressionModel;
import app.domain.model.SignificanceModelAnova;

import java.util.Date;
import java.util.List;

public interface RegressionModel {
    public abstract Integer getBestXIndex(double[] x1, double[] x2, double[] y);
    public abstract MyRegressionModel getRegressionModel(double[] x1, double[] x2, double[] y, int historicalPoints);
    public abstract HypothesisTest getHypothesisTest(MyRegressionModel myRegressionModel, double significanceLevel);
    public abstract SignificanceModelAnova getSignificanceModelAnova(MyRegressionModel myRegressionModel, double significanceLevel);
    public abstract List<Double> getEstimatedPositives(MyRegressionModel myRegressionModel, Double[] x1InHistoricalPoints, Double[] x2InHistoricalPoints);
    public abstract ConfidenceInterval getConfidenceInterval(MyRegressionModel myRegressionModel, double x0, double confidenceLevel);
    public abstract List<ConfidenceInterval> getConfidenceIntervalList(MyRegressionModel myRegressionModel, Double[] xInHistoricalPoints, double confidenceLevel);
}

