package app.domain.adapters;

import app.domain.interfaces.RegressionModel;
import app.domain.model.ConfidenceInterval;
import app.domain.model.HypothesisTest;
import app.domain.model.MyRegressionModel;
import app.domain.model.SignificanceModelAnova;
import app.domain.model.US19.LinearRegression;
import app.domain.model.US19.MultipleLinearRegression;

import java.util.ArrayList;
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
        MultipleLinearRegression multipleLR = (MultipleLinearRegression) myRegressionModel.getRegressionModel();
        double tObsB0 = multipleLR.calculateTObsBj(0);
        double tObsB1 = multipleLR.calculateTObsBj(1);
        double tObsB2 = multipleLR.calculateTObsBj(2);

        return new HypothesisTest(myRegressionModel, tObsB0, tObsB1, tObsB2, significanceLevel);
    }

    @Override
    public SignificanceModelAnova getSignificanceModelAnova(MyRegressionModel myRegressionModel, double significanceLevel) {
        MultipleLinearRegression multipleLR = (MultipleLinearRegression) myRegressionModel.getRegressionModel();
        double sr = multipleLR.getSR(), se = multipleLR.getSE();

        return new SignificanceModelAnova(myRegressionModel, sr, se, significanceLevel);
    }

    @Override
    public List<Double> getEstimatedPositives(MyRegressionModel myRegressionModel, Double[] x1InHistoricalPoints, Double[] x2InHistoricalPoints) {
        MultipleLinearRegression multipleLR = (MultipleLinearRegression) myRegressionModel.getRegressionModel();

        List<Double> estimatedPositives = new ArrayList<>();

        for (int i = 0; i < x1InHistoricalPoints.length; i++) {
            double estimatedValue = multipleLR.predict(x1InHistoricalPoints[i], x2InHistoricalPoints[i]);
            estimatedPositives.add(estimatedValue);
        }
        return estimatedPositives;
    }

    @Override
    public ConfidenceInterval getConfidenceInterval(MyRegressionModel myRegressionModel, double x0, double confidenceLevel) {
        return null;
    }

    @Override
    public List<ConfidenceInterval> getConfidenceIntervalList(MyRegressionModel myRegressionModel, Double[] xInHistoricalPoints, double confidenceLevel) {
        return null;
    }
}
