package app.domain.adapters;

import app.domain.interfaces.RegressionModel;
import app.domain.model.ConfidenceInterval;
import app.domain.model.HypothesisTest;
import app.domain.model.MyRegressionModel;
import app.domain.model.SignificanceModelAnova;
import app.domain.model.US19.MultipleLinearRegression;

import java.util.List;

public class MultipleLinearRegressionAdapter implements RegressionModel {

    @Override
    public Integer getBestXIndex(double[] x1, double[] x2, double[] y) {
        return null;
    }

    @Override
    public MyRegressionModel getRegressionModel(double[] x1, double[] x2, double[] y, int historicalPoints) {
        MultipleLinearRegression multipleLR = new MultipleLinearRegression(x1, x2, y);
;
        return new MyRegressionModel(multipleLR.getB0(), multipleLR.getB1(), multipleLR.getB2(), multipleLR.getR2(), multipleLR.getR2Adjusted(), multipleLR.getN(), multipleLR);
    }

    @Override
    public HypothesisTest getHypothesisTest(MyRegressionModel myRegressionModel, double significanceLevel) {
        //double tObsB0

        return null;
    }

    @Override
    public SignificanceModelAnova getSignificanceModelAnova(MyRegressionModel myRegressionModel, double significanceLevel) {
        return null;
    }

    @Override
    public List<Double> getEstimatedPositives(MyRegressionModel myRegressionModel, double[] xInHistoricalPoints) {
        return null;
    }

    @Override
    public ConfidenceInterval getConfidenceInterval(MyRegressionModel myRegressionModel, double x0, double confidenceLevel) {
        return null;
    }

    @Override
    public List<ConfidenceInterval> getConfidenceIntervalList(MyRegressionModel myRegressionModel, double[] xInHistoricalPoints, double confidenceLevel) {
        return null;
    }
}
