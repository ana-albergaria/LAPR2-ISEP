package app.domain.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ConfidenceInterval {
    private MyRegressionModel myRegressionModel;
    private double y0;
    private double auxDelta;
    private double confidenceLevel;
    private double delta;
    private double limInf;
    private double limSup;


    public ConfidenceInterval(MyRegressionModel myRegressionModel,
                              double y0,
                              double auxDelta,
                              double confidenceLevel) {
        this.myRegressionModel = myRegressionModel;
        this.y0 = y0;
        this.auxDelta = auxDelta;
        this.confidenceLevel = confidenceLevel;
        double critTD = myRegressionModel.calculateCriticalValTStudent(1.0 - confidenceLevel);
        this.delta = critTD * auxDelta;
        this.limInf = y0 - delta;
        this.limSup = y0 + delta;
    }

    public double getConfidenceLevel() {
        return confidenceLevel;
    }

    @Override
    public String toString() {
        return String.format("%f-%f", limInf, limSup);
    }

    @Override
    public boolean equals(Object otherObject) {
        if(this == otherObject)
            return true;

        if(otherObject == null || this.getClass() != otherObject.getClass())
            return false;

        ConfidenceInterval otherConfidenceInterval = (ConfidenceInterval) otherObject;

        DecimalFormat df = new DecimalFormat("#.######");

        return df.format(this.limInf).equals(df.format(otherConfidenceInterval.limInf)) &&
                df.format(this.limSup).equals(df.format(otherConfidenceInterval.limSup));
    }
}




