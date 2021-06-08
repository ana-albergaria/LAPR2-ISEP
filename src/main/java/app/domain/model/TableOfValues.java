package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class TableOfValues {
    private MyRegressionModel myRegressionModel;
    private List<String> dates;
    private int[] observedPositives;
    List<Double> estimatedPositives;
    private List<ConfidenceInterval> confidenceIntervals;

    public TableOfValues(MyRegressionModel myRegressionModel,
                         List<String> dates,
                         int[] observedPositives,
                         List<Double> estimatedPositives) {
        this.dates = dates;
        this.observedPositives = observedPositives;
        this.estimatedPositives = estimatedPositives;
        this.confidenceIntervals = new ArrayList<>();
    }
}
