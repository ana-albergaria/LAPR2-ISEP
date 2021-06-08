package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class ConfidenceInterval {
    private double y0;
    private double auxDelta;
    private double delta;
    private List<Double> confidenceLevels;

    private static final double CONDIFENCE_LEVEL_90 = 0.9;
    private static final double CONFIDENCE_LEVEL_95 = 0.95;

    public ConfidenceInterval(double y0,
                              double auxDelta) {
        this.y0 = y0;
        this.auxDelta = auxDelta;

        this.confidenceLevels = new ArrayList<>();
        confidenceLevels.add(CONDIFENCE_LEVEL_90);
        confidenceLevels.add(CONFIDENCE_LEVEL_95);
    }
}
