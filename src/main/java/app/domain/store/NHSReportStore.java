package app.domain.store;

import app.domain.interfaces.RegressionModel;
import app.domain.model.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Represents the NHS Report Store
 *
 * @author Ana Albergaria
 */
public class NHSReportStore {

    /**
     * Method which returns the NHS Report to be sent, receiving:
     * the myRegressionModel, the hypothesisTest,
     * the modelAnova and the TableOfValues
     *
     *
     * @param myRegressionModel the Regression Model used to compute the data
     * @param hypothesisTest the Hypothesis Test of the Regression Model
     * @param modelAnova the Anova Significance Model of the Regression Model
     * @param tableOfValues the Table Of Values of the Regression Model
     *
     * @return the NHS Report to be sent
     */
    public NHSReport createNHSDailyReport(MyRegressionModel myRegressionModel, HypothesisTest hypothesisTest, SignificanceModelAnova modelAnova, TableOfValues tableOfValues) {
        return new NHSReport(myRegressionModel, hypothesisTest, modelAnova, tableOfValues);
    }

    /**
     * Method which validates if the NHS Report
     *
     * @param nhsReport the NHS Report to be validated
     *
     * @return true if the new NHS Report was successfully validated.
     *          Otherwise, returns false.
     */
    public boolean validateNHSDailyReport(NHSReport nhsReport) {
        return nhsReport != null && nhsReport.getMyRegressionModel() != null &&
                nhsReport.getHypothesisTest() != null && nhsReport.getModelAnova() != null && nhsReport.getTableOfValues() != null;
    }

    /**
     * Returns the index of the variable of the best Regression Model.
     *
     * @param regressionModel the class of the Regression Model who will obtain the result
     * @param x1 the values of the first independent variable
     * @param x2 the values of the second independent variable
     * @param y the values of the dependent variable
     *
     * @return index of the varibale of the best Regression Model.
     *          Returns null if the Regression Model is Multiple Linear Regression.
     */
    public Integer getBestXIndex(RegressionModel regressionModel,
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

    public HypothesisTest createHypothesisTest(RegressionModel regressionModel, MyRegressionModel myRegressionModel, double significanceLevel) {
        return regressionModel.getHypothesisTest(myRegressionModel, significanceLevel);
    }

    public SignificanceModelAnova createSignificanceModelAnova(RegressionModel regressionModel, MyRegressionModel myRegressionModel, double significanceLevel) {
        return regressionModel.getSignificanceModelAnova(myRegressionModel, significanceLevel);
    }

    public TableOfValues createTableOfValues(MyRegressionModel myRegressionModel, List<String> dates, int[] observedPositives, List<Double> estimatedPositives, List<ConfidenceInterval> confidenceIntervals) {
        return new TableOfValues(myRegressionModel, dates, observedPositives, estimatedPositives, confidenceIntervals);
    }

    public double[] getDoubleArrayWithData(List<List<Double>> covidTestAndMeanAgeList, int index) {
        Double[] extractedArray = new Double[covidTestAndMeanAgeList.get(index).size()];
        double[] wishedArray = new double[covidTestAndMeanAgeList.get(index).size()];
        for (int i = 0; i < wishedArray.length; i++) {
            wishedArray[i] = covidTestAndMeanAgeList.get(index).get(i);
            //wishedArray[i] = extractedArray[i];
        }
        return wishedArray;
    }

    public Double[] copyArray(Double[] array) {
        Double[] wishedArray = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            wishedArray[i] = array[i];
        }
        return wishedArray;
    }


    /*
    WARNING - ter em atenção que ao converter a String selecionada pelo administrador
    ou a que está definida na configuration file,
     */
    public List<String> getDatesColumnToTableOfValues(int numberOfObservations,
                                                        Date currentDate) {
        //VERIFICAR SE O MÉTODO RETIRA BEM AS DATAS POR CAUSA DO DOMINGO!!!
        List<String> dates = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);

        for (int i = 0; i < numberOfObservations; i++) {
            dates.add(sdf.format(currentDate));
            cal.add(Calendar.DAY_OF_MONTH,-1);
            if ((cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY))
                cal.add(Calendar.DAY_OF_MONTH,-1);
            currentDate = cal.getTime();
        }
        return dates;
    }

    public List<String> getWeeksColumnToTableOfValues(int numberOfObservations,
                                                      Date currentDate) {
        List<String> dates = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        while ((cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY)) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        Date endDate = cal.getTime();
        for (int i = 0; i < numberOfObservations; i++) {
            cal.add(Calendar.DAY_OF_MONTH, -5);
            Date initialDate = cal.getTime();
            dates.add(getWeek(initialDate, endDate));
            cal.add(Calendar.DAY_OF_MONTH, -2);
            endDate = cal.getTime();
        }

        return dates;
    }

    public String getWeek(Date initialDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("%s-%s", sdf.format(initialDate), sdf.format(endDate));
    }

    public Date getStartDate() {
        Date currentDate = new Date();
        Calendar oneDayBefore = Calendar.getInstance();
        oneDayBefore.setTime(currentDate);
        oneDayBefore.add(Calendar.DAY_OF_MONTH, -1);
        if ((oneDayBefore.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY))
            oneDayBefore.add(Calendar.DAY_OF_MONTH, -1);

        return oneDayBefore.getTime();
    }

    public Date getStartDateForSelectedDate(Date currentDate) {
        Calendar oneDayBefore = Calendar.getInstance();
        oneDayBefore.setTime(currentDate);
        oneDayBefore.add(Calendar.DAY_OF_MONTH, -1);
        if ((oneDayBefore.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY))
            oneDayBefore.add(Calendar.DAY_OF_MONTH, -1);

        return oneDayBefore.getTime();
    }
}
