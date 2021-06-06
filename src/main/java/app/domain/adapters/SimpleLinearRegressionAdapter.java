package app.domain.adapters;

import app.domain.interfaces.MathCalculus;
import app.domain.model.HypothesisTest;
import app.domain.model.MyRegressionModel;
import app.domain.model.SignificanceModelAnova;
import app.domain.model.US19.LinearRegression;

public class SimpleLinearRegressionAdapter implements MathCalculus {

    @Override
    public MyRegressionModel getRegressionModel(double[] x1, double[] x2, double[] y) {
        LinearRegression simpleLRx1 = new LinearRegression(x1, y);
        LinearRegression simpleLRx2 = new LinearRegression(x2, y);

        LinearRegression bestModel = (simpleLRx1.R2() >= simpleLRx2.R2()) ? new LinearRegression(x1, y) : new LinearRegression(x2, y);

        return new MyRegressionModel(bestModel.intercept(), bestModel.slope(), null,
                Math.sqrt(bestModel.R2()), bestModel.R2(), bestModel.getR2Adjusted(), bestModel.getN(), bestModel);
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
}

