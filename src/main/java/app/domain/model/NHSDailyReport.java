package app.domain.model;

public class NHSDailyReport {
    private MyRegressionModel myRegressionModel;
    private HypothesisTest hypothesisTest;
    private SignificanceModelAnova modelAnova;

    public NHSDailyReport(MyRegressionModel myRegressionModel,
                          HypothesisTest hypothesisTest,
                          SignificanceModelAnova modelAnova) {
        this.myRegressionModel = myRegressionModel;
        this.hypothesisTest = hypothesisTest;
        this.modelAnova = modelAnova;
    }

    public String toString() {
        return String.format("%s%n%s%n%s",
                myRegressionModel, hypothesisTest, modelAnova);
    }
}
