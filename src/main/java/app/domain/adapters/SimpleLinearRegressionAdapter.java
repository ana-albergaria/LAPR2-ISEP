package app.domain.adapters;

import app.domain.interfaces.RegressionModel;
import app.domain.model.*;
import app.domain.model.US19.LinearRegression;

import java.util.ArrayList;
import java.util.List;

public class SimpleLinearRegressionAdapter implements RegressionModel {

    @Override
    public double[] getBestX(double[] x1, double[] x2, double[] y) {
        LinearRegression simpleLRx1 = new LinearRegression(x1, y);
        LinearRegression simpleLRx2 = new LinearRegression(x2, y);
        //FALTA FAZER O SIGNIFICANCE MODEL ANOVA
        return (simpleLRx1.R2() >= simpleLRx2.R2()) ? x1 : x2;
    }

    @Override
    public MyRegressionModel getRegressionModel(double[] x1, double[] x2, double[] y, int historicalPoints) {
        LinearRegression simpleLRx1 = new LinearRegression(x1, y);

        return new MyRegressionModel(simpleLRx1.intercept(), simpleLRx1.slope(),
                Math.sqrt(simpleLRx1.R2()), simpleLRx1.R2(), simpleLRx1.getR2Adjusted(), historicalPoints, simpleLRx1);
    }

    @Override
    public HypothesisTest getHypothesisTest(MyRegressionModel myRegressionModel) {
        LinearRegression simpleLR = (LinearRegression) myRegressionModel.getRegressionModel();
        //for a, for b
        double tObsA = simpleLR.calculatetObsA(), tObsB = simpleLR.calculateTObsB();
        return new HypothesisTest(myRegressionModel, tObsA, tObsB);
    }

    @Override
    public SignificanceModelAnova getSignificanceModelAnova(MyRegressionModel myRegressionModel) {
        LinearRegression simpleLR = (LinearRegression) myRegressionModel.getRegressionModel();
        double sr = simpleLR.getSSR(), se = simpleLR.getRSS();
        return new SignificanceModelAnova(myRegressionModel, sr, se);
    }

    @Override
    public List<Double> getEstimatedPositives(MyRegressionModel myRegressionModel, double[] xInHistoricalPoints) {
        LinearRegression simpleLR = (LinearRegression) myRegressionModel.getRegressionModel();

        List<Double> estimatedPositives = new ArrayList<>();

        for (int i = 0; i < xInHistoricalPoints.length; i++) {
            double estimatedValue = simpleLR.predict(xInHistoricalPoints[i]);
            estimatedPositives.add(estimatedValue);
        }
        return estimatedPositives;
    }

    @Override
    public ConfidenceInterval getConfidenceInterval(MyRegressionModel myRegressionModel, double x0, double confidenceLevel) {
        int n = myRegressionModel.getNumberOfObservations();
        LinearRegression simpleLR = (LinearRegression) myRegressionModel.getRegressionModel();
        double y0 = simpleLR.predict(x0), s = simpleLR.getS();
        double xbar = simpleLR.getXbar(), xxbar = simpleLR.getXXbar();

        double auxDelta = s * Math.sqrt(1 + (1.0/n) + (Math.pow((x0-xbar),2) / xxbar));

        return new ConfidenceInterval(myRegressionModel, y0, auxDelta, confidenceLevel);
    }

    @Override
    public List<ConfidenceInterval> getConfidenceIntervalList(MyRegressionModel myRegressionModel, double[] xInHistoricalPoints, double confidenceLevel) {
        int numberOfObservations = myRegressionModel.getNumberOfObservations();
        List<ConfidenceInterval> confidenceIntervals = new ArrayList<>();

        for (int i = 0; i < numberOfObservations; i++) {
            ConfidenceInterval confidenceInterval = getConfidenceInterval(myRegressionModel, xInHistoricalPoints[i], confidenceLevel);
            confidenceIntervals.add(confidenceInterval);
        }
        return confidenceIntervals;
    }
}

