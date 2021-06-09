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
        List<List<Double>> dataList = getDataListToFitTheModel();

        NHSReportStore nhsReportStore = this.company.getNhsReportStore();
        MyRegressionModel myRegressionModel = nhsReportStore.createMyRegressionModel(regressionModel, historicalPoints, dataList);
        HypothesisTest hypothesisTest = nhsReportStore.createHypothesisTest(regressionModel, myRegressionModel);
        SignificanceModelAnova modelAnova = nhsReportStore.createSignificanceModelAnova(regressionModel, myRegressionModel);
        //CORRIGIR MÉTODO getStartDate() para obter a data de início a começar do fim
        Date startDate = nhsReportStore.getStartDate();
        TableOfValues tableOfValues = getTableOfValues(myRegressionModel, historicalPoints, startDate);

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



    public TableOfValues getTableOfValues(MyRegressionModel myRegressionModel,
                                          int historicalPoints,
                                          Date startDate) throws ParseException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        NHSReportStore nhsReportStore = this.company.getNhsReportStore();
        List<String> dates = nhsReportStore.getDatesColumnToTableOfValues(historicalPoints, startDate);
        TestStore testStore = new TestStore();
        int[] observedPositives = testStore.getObservedPositivesToTableOfValues(historicalPoints, dates);
        RegressionModel regressionModel = this.company.getRegressionModel();
        List<Double> estimatedPositives = regressionModel.getEstimatedPositives(myRegressionModel);
        List<ConfidenceInterval> confidenceIntervals = getConfidenceIntervalListForTableOfValues(myRegressionModel, regressionModel);

        TableOfValues tableOfValues = nhsReportStore.createTableOfValues(myRegressionModel, dates, observedPositives, estimatedPositives, confidenceIntervals);
        return tableOfValues;
    }

    public List<ConfidenceInterval> getConfidenceIntervalListForTableOfValues(MyRegressionModel myRegressionModel,
                                                                              RegressionModel regressionModel) {
        double confidenceLevel = this.company.getConfidenceLevel();
        List<ConfidenceInterval> confidenceIntervalList = regressionModel.getConfidenceIntervalList(myRegressionModel, confidenceLevel);
        return confidenceIntervalList;
    }





}









