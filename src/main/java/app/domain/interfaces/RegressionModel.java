package app.domain.interfaces;

import app.domain.model.HypothesisTest;
import app.domain.model.MyRegressionModel;
import app.domain.model.SignificanceModelAnova;

public interface RegressionModel {
    public abstract MyRegressionModel getRegressionModel(double[] x1, double[] x2, double[] y);
    public abstract HypothesisTest getHypothesisTest(MyRegressionModel myRegressionModel);
    public abstract SignificanceModelAnova getSignificanceModelAnova(MyRegressionModel myRegressionModel);
}

