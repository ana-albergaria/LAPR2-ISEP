package app.domain.model;

public class NHSDailyReport {
    private MyRegressionModel myRegressionModel;
    private HypothesisTest hypothesisTest;
    private SignificanceModelAnova modelAnova;
    private TableOfValues tableOfValues;

    public NHSDailyReport(MyRegressionModel myRegressionModel,
                          HypothesisTest hypothesisTest,
                          SignificanceModelAnova modelAnova,
                          TableOfValues tableOfValues) {
        this.myRegressionModel = myRegressionModel;
        this.hypothesisTest = hypothesisTest;
        this.modelAnova = modelAnova;
        this.tableOfValues = tableOfValues;
    }

    public MyRegressionModel getMyRegressionModel() {
        return myRegressionModel;
    }

    public HypothesisTest getHypothesisTest() {
        return hypothesisTest;
    }

    public SignificanceModelAnova getModelAnova() {
        return modelAnova;
    }

    public TableOfValues getTableOfValues() {
        return tableOfValues;
    }

    @Override
    public String toString() {
        return String.format("%s%n%s%n%s%n%s",
                myRegressionModel, hypothesisTest, modelAnova, tableOfValues);
    }
}
