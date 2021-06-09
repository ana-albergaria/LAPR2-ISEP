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
                         List<Double> estimatedPositives,
                         List<ConfidenceInterval> confidenceIntervals) {
        this.dates = dates;
        this.observedPositives = observedPositives;
        this.estimatedPositives = estimatedPositives;
        this.confidenceIntervals = confidenceIntervals;


    }

    public String getConfidenceLevelInPercentage() {
        double confidenceLevel = confidenceIntervals.get(0).getConfidenceLevel() * 100;
        return String.valueOf(confidenceLevel);
    }



    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append(String.format("%-20s%-20s%-20s%-20s%n", "Date", "Number of OBSERVED positive cases", "Number of ESTIMATED/EXPECTED positive cases", getConfidenceLevelInPercentage() + "intervals"));
        for (int i = 0; i < myRegressionModel.getNumberOfObservations(); i++) {
            text.append(String.format("%-35s%-35d%-35f%-35s%n", dates.get(i), observedPositives[i], estimatedPositives.get(i), confidenceIntervals.get(i).toString()));
        }
        return text.toString();
    }
}
