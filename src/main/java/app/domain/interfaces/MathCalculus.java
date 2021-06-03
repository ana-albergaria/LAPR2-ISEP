package app.domain.interfaces;

import app.domain.model.MyRegressionModel;

public interface MathCalculus {
    public abstract MyRegressionModel getRegressionModel(double[] x, double[] y);
}
