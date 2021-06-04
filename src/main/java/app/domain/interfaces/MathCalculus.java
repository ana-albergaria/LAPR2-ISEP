package app.domain.interfaces;

import app.domain.model.HypothesisTest;
import app.domain.model.MyRegressionModel;

public interface MathCalculus {
    //-> Simple Linear Regression
    public static final double B0 = 0;
    public static final double A0 = 0;

    public abstract MyRegressionModel getRegressionModel(double[] x1, double[] x2, double[] y);
    public abstract HypothesisTest getHypothesisTest(MyRegressionModel myRegressionModel);
}

