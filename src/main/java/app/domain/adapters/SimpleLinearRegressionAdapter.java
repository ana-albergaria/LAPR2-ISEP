package app.domain.adapters;

import app.domain.interfaces.MathCalculus;
import app.domain.model.MyRegressionModel;
import app.domain.model.US19.LinearRegression;

public class SimpleLinearRegressionAdapter implements MathCalculus {

    @Override
    public MyRegressionModel getRegressionModel(double[] x1, double[] x2, double[] y) {
        LinearRegression simpleLRx1 = new LinearRegression(x1, y);
        LinearRegression simpleLRx2 = new LinearRegression(x2, y);

        LinearRegression bestModel = (simpleLRx1.R2() >= simpleLRx2.R2()) ? new LinearRegression(x1, y) : new LinearRegression(x2, y);

        return new MyRegressionModel(bestModel.intercept(), bestModel.slope(), null,
                Math.sqrt(bestModel.R2()), bestModel.R2(), null, bestModel);
    }

    /*
    if(simpleLRx1.R2() >= simpleLRx2.R2())
            bestModel = new LinearRegression(x1, y);
        else
            bestModel = new LinearRegression(x2, y);
     */



}
