package app.domain.adapters;

import app.domain.interfaces.RegressionModel;
import app.domain.model.*;
import app.domain.model.US19.LinearRegression;

import java.util.ArrayList;
import java.util.List;

public class SimpleLinearRegressionAdapter implements RegressionModel {

    @Override
    public Integer getBestXIndex(double[] x1, double[] x2, double[] y) {
        LinearRegression simpleLRx1 = new LinearRegression(x1, y);
        LinearRegression simpleLRx2 = new LinearRegression(x2, y);
        //FALTA FAZER O SIGNIFICANCE MODEL ANOVA
        int x1Index = 1, x2Index = 2;

        return (simpleLRx1.R2() >= simpleLRx2.R2()) ? x1Index : x2Index;
    }

    @Override
    public MyRegressionModel getRegressionModel(double[] x1, double[] x2, double[] y, int historicalPoints) {
        LinearRegression simpleLRx1 = new LinearRegression(x1, y);

        return new MyRegressionModel(simpleLRx1.intercept(), simpleLRx1.slope(),
                simpleLRx1.R2(), simpleLRx1.getR2Adjusted(), historicalPoints, simpleLRx1);
    }

    @Override
    public HypothesisTest getHypothesisTest(MyRegressionModel myRegressionModel, double significanceLevel) {
        LinearRegression simpleLR = (LinearRegression) myRegressionModel.getRegressionModel();
        //for a, for b
        double tObsA = simpleLR.calculatetObsA(), tObsB = simpleLR.calculateTObsB();
        return new HypothesisTest(myRegressionModel, tObsA, tObsB, significanceLevel);
    }

    @Override
    public SignificanceModelAnova getSignificanceModelAnova(MyRegressionModel myRegressionModel, double significanceLevel) {
        LinearRegression simpleLR = (LinearRegression) myRegressionModel.getRegressionModel();
        double sr = simpleLR.getSSR(), se = simpleLR.getRSS();
        return new SignificanceModelAnova(myRegressionModel, sr, se, significanceLevel);
    }

    @Override
    public List<Double> getEstimatedPositives(MyRegressionModel myRegressionModel, Double[] x1InHistoricalPoints, Double[] x2InHistoricalPoints) {
        LinearRegression simpleLR = (LinearRegression) myRegressionModel.getRegressionModel();

        List<Double> estimatedPositives = new ArrayList<>();

        for (int i = 0; i < x1InHistoricalPoints.length; i++) {
            double estimatedValue = simpleLR.predict(x1InHistoricalPoints[i]);
            estimatedPositives.add(estimatedValue);
        }
        return estimatedPositives;
    }

    @Override
    public ConfidenceInterval getConfidenceInterval(MyRegressionModel myRegressionModel, Double x1, Double x2, double confidenceLevel) {
        //x1 is x0
        int n = myRegressionModel.getNumberOfObservations();
        LinearRegression simpleLR = (LinearRegression) myRegressionModel.getRegressionModel();
        double y0 = simpleLR.predict(x1);
        double auxDelta = simpleLR.calculateAuxDelta(x1);

        return new ConfidenceInterval(myRegressionModel, y0, auxDelta, confidenceLevel);
    }

    @Override
    public List<ConfidenceInterval> getConfidenceIntervalList(MyRegressionModel myRegressionModel, Double[] x1InHistoricalPoints, Double[] x2InHistoricalPoints, double confidenceLevel) {
        int numberOfObservations = myRegressionModel.getNumberOfObservations();
        List<ConfidenceInterval> confidenceIntervals = new ArrayList<>();

        for (int i = 0; i < numberOfObservations; i++) {
            ConfidenceInterval confidenceInterval = getConfidenceInterval(myRegressionModel, x1InHistoricalPoints[i], null, confidenceLevel);
            confidenceIntervals.add(confidenceInterval);
        }
        return confidenceIntervals;
    }
}

