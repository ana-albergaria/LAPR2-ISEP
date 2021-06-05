package app.domain.adapters;

import app.domain.interfaces.MathCalculus;
import app.domain.model.HypothesisTest;
import app.domain.model.MyRegressionModel;
import app.domain.model.US19.LinearRegression;
import app.domain.shared.Constants;

public class SimpleLinearRegressionAdapter implements MathCalculus {

    @Override
    public MyRegressionModel getRegressionModel(double[] x1, double[] x2, double[] y) {
        LinearRegression simpleLRx1 = new LinearRegression(x1, y);
        LinearRegression simpleLRx2 = new LinearRegression(x2, y);

        LinearRegression bestModel = (simpleLRx1.R2() >= simpleLRx2.R2()) ? new LinearRegression(x1, y) : new LinearRegression(x2, y);

        //FALTA OBTER O R2 AJUSTADO

        return new MyRegressionModel(bestModel.intercept(), bestModel.slope(), null,
                Math.sqrt(bestModel.R2()), bestModel.R2(), -1, bestModel.getN(), bestModel);
    }

    @Override
    public HypothesisTest getHypothesisTest(MyRegressionModel myRegressionModel) {
        LinearRegression simpleLR = (LinearRegression) myRegressionModel.getRegressionModel();
        double a = myRegressionModel.getIntercept(), b = myRegressionModel.getSlope();
        double xxbar = simpleLR.getXXbar(), yybar = simpleLR.getYYbar();
        int n = myRegressionModel.getNumberOfObservations();
        double se = yybar - (Math.pow(b,2) * xxbar), s = Math.sqrt(se / (n-2));
        double xbar = simpleLR.getXbar();

        //for a
        final double a0 = MathCalculus.A0;
        double tObsA = (a-a0) / (s * Math.sqrt((1.0/n) + (Math.pow(xbar,2) / xxbar)));

        //for b
        final double b0 = MathCalculus.B0;
        double tObsB = (b-b0) / (s * Math.sqrt(1/xxbar));

        return new HypothesisTest(myRegressionModel, tObsA, tObsB);
    }
}

