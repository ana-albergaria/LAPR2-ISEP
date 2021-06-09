package app.domain.adapters;

import app.domain.interfaces.RegressionModel;
import app.domain.model.*;
import app.domain.model.US19.LinearRegression;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SimpleLinearRegressionAdapter implements RegressionModel {

    @Override
    public MyRegressionModel getRegressionModel(double[] x1, double[] x2, double[] y, int historicalPoints) {
        LinearRegression simpleLRx1 = new LinearRegression(x1, y);
        LinearRegression simpleLRx2 = new LinearRegression(x2, y);

        double[] bestX = (simpleLRx1.R2() >= simpleLRx2.R2()) ? x1 : x2;
        LinearRegression bestModel = new LinearRegression(bestX, y);

        return new MyRegressionModel(bestX, y, bestModel.intercept(), bestModel.slope(),
                Math.sqrt(bestModel.R2()), bestModel.R2(), bestModel.getR2Adjusted(), historicalPoints, bestModel);
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
    public List<Double> getEstimatedPositives(MyRegressionModel myRegressionModel) {
        LinearRegression simpleLR = (LinearRegression) myRegressionModel.getRegressionModel();
        double[] x = myRegressionModel.getX1();
        List<Double> estimatedPositives = new ArrayList<>();

        for (int i = 0; i < x.length; i++) {
            double estimatedValue = simpleLR.predict(x[i]);
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
        double auxDelta = s * Math.sqrt((1 + (1.0/n) + (Math.pow((x0-xbar),2) / xxbar)));

        return new ConfidenceInterval(myRegressionModel, y0, auxDelta, confidenceLevel);
    }

    @Override
    public List<ConfidenceInterval> getConfidenceIntervalList(MyRegressionModel myRegressionModel, double confidenceLevel) {
        int numberOfObservations = myRegressionModel.getNumberOfObservations();
        double[] x = myRegressionModel.getX1();
        List<ConfidenceInterval> confidenceIntervals = new ArrayList<>();

        for (int i = 0; i < numberOfObservations; i++) {
            ConfidenceInterval confidenceInterval = getConfidenceInterval(myRegressionModel, x[i], confidenceLevel);
            confidenceIntervals.add(confidenceInterval);
        }
        return confidenceIntervals;
    }
}

