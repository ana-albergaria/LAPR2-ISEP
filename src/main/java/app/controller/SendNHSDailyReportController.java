package app.controller;

import app.domain.interfaces.RegressionModel;
import app.domain.model.*;
import app.domain.store.NHSReportStore;
import app.domain.store.TestStore;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class SendNHSDailyReportController {
    private Company company;
    private NHSDailyReport nhsDailyReport;

    public SendNHSDailyReportController() {
        this(App.getInstance().getCompany());
    }

    public SendNHSDailyReportController(Company company) {
        this.company = company;
        this.nhsDailyReport = null;
    }


    public boolean createNHSDailyReport() throws ClassNotFoundException, InstantiationException, ParseException, IllegalAccessException {
        RegressionModel regressionModel = this.company.getRegressionModel();
        int historicalPoints = this.company.getHistoricalPoints();
        double significanceLevel = this.company.getSignificanceLevel();

        List<List<Double>> dataList = getDataListToFitTheModel();
        NHSReportStore nhsReportStore = this.company.getNhsReportStore();
        double[] covidTestsArray = nhsReportStore.getDoubleArrayWithData(dataList, 0);
        double[] meanAgeArray = nhsReportStore.getDoubleArrayWithData(dataList, 1);
        double[] observedPositives = nhsReportStore.getDoubleArrayWithData(dataList, 2);
        int bestXIndex = nhsReportStore.getBestXIndex(regressionModel, covidTestsArray, meanAgeArray, observedPositives);

        MyRegressionModel myRegressionModel = getMyRegressionModel(regressionModel, bestXIndex, covidTestsArray, meanAgeArray, observedPositives, historicalPoints);
        HypothesisTest hypothesisTest = nhsReportStore.createHypothesisTest(regressionModel, myRegressionModel, significanceLevel);
        SignificanceModelAnova modelAnova = nhsReportStore.createSignificanceModelAnova(regressionModel, myRegressionModel);

        Date startDate = nhsReportStore.getStartDate();
        TableOfValues tableOfValues = getTableOfValues(myRegressionModel, bestXIndex, historicalPoints, startDate);

        this.nhsDailyReport = nhsReportStore.createNHSDailyReport(myRegressionModel,hypothesisTest,modelAnova,tableOfValues);
        return nhsReportStore.validateNHSDailyReport(nhsDailyReport);
    }




    public List<List<Double>> getDataListToFitTheModel() throws ParseException {
        List<Date> intervalDates = this.company.getDateInterval();
        Date beginDate = intervalDates.get(0), endDate = intervalDates.get(1);
        TestStore testStore = this.company.getTestStore();
        List<List<Double>> dataList = testStore.getAllDataToFitTheModel(beginDate, endDate);
        return dataList;
    }

    public MyRegressionModel getMyRegressionModel(RegressionModel regressionModel,
                                                  Integer bestXIndex,
                                                  double[] covidTestsArray,
                                                  double[] meanAgeArray,
                                                  double[] observedPositives,
                                                  int historicalPoints) {
        NHSReportStore nhsReportStore = new NHSReportStore();
        MyRegressionModel myRegressionModel = (bestXIndex == null) ? nhsReportStore.createMyRegressionModel(regressionModel, covidTestsArray, meanAgeArray, observedPositives, historicalPoints) :
                ((bestXIndex == 1) ? nhsReportStore.createMyBestRegressionModel(regressionModel, covidTestsArray, observedPositives, historicalPoints) : nhsReportStore.createMyBestRegressionModel(regressionModel, meanAgeArray, observedPositives, historicalPoints));
        return myRegressionModel;
    }



    public TableOfValues getTableOfValues(MyRegressionModel myRegressionModel,
                                          Integer bestXIndex,
                                          int historicalPoints,
                                          Date startDate) throws ParseException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        NHSReportStore nhsReportStore = this.company.getNhsReportStore();
        List<String> dates = nhsReportStore.getDatesColumnToTableOfValues(historicalPoints, startDate);
        TestStore testStore = new TestStore();
        int[] observedPositives = testStore.getObservedPositivesToTableOfValues(historicalPoints, dates);
        RegressionModel regressionModel = this.company.getRegressionModel();

        double[] bestXInHistoricalPoints = new double[historicalPoints];
        if(bestXIndex != null) {
            if(bestXIndex == 1)
                bestXInHistoricalPoints = testStore.getNumberOfCovidTestsInHistoricalPoints(dates);
            else
                bestXInHistoricalPoints = testStore.getMeanAgeInHistoricalPoints(dates);
        }
        //FALTA ACRESCENTAR ELSE PARA A REGRESSÃO MÚLTIPLA!!!

        List<Double> estimatedPositives = regressionModel.getEstimatedPositives(myRegressionModel, bestXInHistoricalPoints);
        List<ConfidenceInterval> confidenceIntervals = getConfidenceIntervalListForTableOfValues(myRegressionModel, regressionModel, bestXInHistoricalPoints);

        TableOfValues tableOfValues = nhsReportStore.createTableOfValues(myRegressionModel, dates, observedPositives, estimatedPositives, confidenceIntervals);
        return tableOfValues;
    }

    public List<ConfidenceInterval> getConfidenceIntervalListForTableOfValues(MyRegressionModel myRegressionModel,
                                                                              RegressionModel regressionModel,
                                                                              double[] xInHistoricalPoints) {
        double confidenceLevel = this.company.getConfidenceLevel();
        List<ConfidenceInterval> confidenceIntervalList = regressionModel.getConfidenceIntervalList(myRegressionModel, xInHistoricalPoints, confidenceLevel);
        return confidenceIntervalList;
    }





}









