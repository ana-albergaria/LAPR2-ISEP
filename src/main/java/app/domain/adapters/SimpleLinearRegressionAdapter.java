package app.domain.adapters;

import app.domain.interfaces.MathCalculus;
import app.domain.model.MyRegressionModel;
import app.domain.model.US19.LinearRegression;

public class SimpleLinearRegressionAdapter implements MathCalculus {

    @Override
    public MyRegressionModel getRegressionModel(double[] x, double[] y) {
        LinearRegression simpleLR = new LinearRegression(x, y);
        return new MyRegressionModel(simpleLR.intercept(), simpleLR.slope(), null,
                Math.sqrt(simpleLR.R2()), simpleLR.R2(), null, simpleLR);
    }



}
