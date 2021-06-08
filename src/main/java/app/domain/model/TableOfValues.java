package app.domain.model;

import app.domain.interfaces.RegressionModel;

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

    

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append(String.format("%-20s%-20s%n", "Date", "Number of OBSERVED positive cases"));
        for (int i = 0; i < myRegressionModel.getNumberOfObservations(); i++) {
            //text.append(String.format("%-35s%-35s%n", tableOfValues.get(0).get(i), tableOfValues.get(1).get(i)));
        }
        return text.toString();
    }
}
