package app.domain.store;

import app.domain.interfaces.RegressionModel;
import app.domain.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NHSReportStore {

    private List<NHSDailyReport> nhsDailyReportList = new ArrayList<>();

    public NHSDailyReport createNHSDailyReport(MyRegressionModel myRegressionModel, HypothesisTest hypothesisTest, SignificanceModelAnova modelAnova, TableOfValues tableOfValues) {
        return new NHSDailyReport(myRegressionModel, hypothesisTest, modelAnova, tableOfValues);
    }

    public boolean validateNHSDailyReport(NHSDailyReport nhsDailyReport) {
        return nhsDailyReport != null && nhsDailyReport.getMyRegressionModel() != null &&
                nhsDailyReport.getHypothesisTest() != null && nhsDailyReport.getModelAnova() != null && nhsDailyReport.getTableOfValues() != null;
    }

    public int getBestXIndex(RegressionModel regressionModel,
                             double[] x1,
                             double[] x2,
                             double[] y) {

        return regressionModel.getBestXIndex(x1, x2, y);
    }

    public MyRegressionModel createMyBestRegressionModel(RegressionModel regressionModel,
                                                         double[] bestX,
                                                         double[] y,
                                                         int historicalPoints) {
        MyRegressionModel myRegressionModel = regressionModel.getRegressionModel(bestX, null, y, historicalPoints);
        return myRegressionModel;
    }

    public MyRegressionModel createMyRegressionModel(RegressionModel regressionModel,
                                                     double[] x1,
                                                     double[] x2,
                                                     double[] y,
                                                     int historicalPoints) {

        MyRegressionModel myRegressionModel = regressionModel.getRegressionModel(x1,
                x2, y, historicalPoints);
        return myRegressionModel;
    }

    public HypothesisTest createHypothesisTest(RegressionModel regressionModel, MyRegressionModel myRegressionModel) {
        return regressionModel.getHypothesisTest(myRegressionModel);
    }

    public SignificanceModelAnova createSignificanceModelAnova(RegressionModel regressionModel, MyRegressionModel myRegressionModel) {
        return regressionModel.getSignificanceModelAnova(myRegressionModel);
    }

    public TableOfValues createTableOfValues(MyRegressionModel myRegressionModel, List<String> dates, int[] observedPositives, List<Double> estimatedPositives, List<ConfidenceInterval> confidenceIntervals) {
        return new TableOfValues(myRegressionModel, dates, observedPositives, estimatedPositives, confidenceIntervals);
    }

    public double[] getDoubleArrayWithData(List<List<Double>> covidTestAndMeanAgeList, int index) {
        Double[] extractedArray = new Double[covidTestAndMeanAgeList.get(index).size()];
        double[] wishedArray = new double[covidTestAndMeanAgeList.get(index).size()];
        for (int i = 0; i < wishedArray.length; i++) {
            wishedArray[i] = extractedArray[i];
        }
        return wishedArray;
    }

    /*
    WARNING - ter em atenção que ao converter a String selecionada pelo administrador
    ou a que está definida na configuration file,
     */
    public List<String> getDatesColumnToTableOfValues(int numberOfObservations,
                                              Date currentDate) {
        List<String> dates = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);

        for (int i = 0; i < numberOfObservations; i++) {
            dates.add(sdf.format(currentDate));
            cal.add(Calendar.DAY_OF_MONTH,-1);
            currentDate = cal.getTime();
        }
        return dates;
    }

    public Date getStartDate() {
        Date currentDate = new Date();
        Calendar oneDayBefore = Calendar.getInstance();
        oneDayBefore.setTime(currentDate);
        if ((oneDayBefore.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY))
            oneDayBefore.add(Calendar.DAY_OF_MONTH, -2);
        else
            oneDayBefore.add(Calendar.DAY_OF_MONTH, -1);

        return oneDayBefore.getTime();
    }
}
