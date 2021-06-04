package app.domain.model;

public class NHSDailyReport {
    private MyRegressionModel myRegressionModel;
    private HypothesisTest hypothesisTest;

    public NHSDailyReport(MyRegressionModel myRegressionModel, HypothesisTest hypothesisTest) {
        this.myRegressionModel = myRegressionModel;
        this.hypothesisTest = hypothesisTest;
    }

    public String toString() {
        return String.format("%s%s", myRegressionModel, hypothesisTest);
    }
}
