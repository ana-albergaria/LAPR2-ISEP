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
        NHSReportStore nhsReportStore = this.company.getNhsReportStore();

        MyRegressionModel myRegressionModel = getMyRegressionModel(regressionModel, historicalPoints);
        HypothesisTest hypothesisTest = nhsReportStore.createHypothesisTest(regressionModel, myRegressionModel);
        SignificanceModelAnova modelAnova = nhsReportStore.createSignificanceModelAnova(regressionModel, myRegressionModel);
        
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

    public MyRegressionModel getMyRegressionModel(RegressionModel regressionModel,
                                                  int historicalPoints) throws ParseException {
        List<List<Double>> dataList = getDataListToFitTheModel();
        NHSReportStore nhsReportStore = this.company.getNhsReportStore();
        double[] covidTestsArray = nhsReportStore.getDoubleArrayWithData(dataList, 0);
        double[] meanAgeArray = nhsReportStore.getDoubleArrayWithData(dataList, 1);
        double[] observedPositives = nhsReportStore.getDoubleArrayWithData(dataList, 2);
        double[] bestX = nhsReportStore.getBestX(regressionModel, covidTestsArray, meanAgeArray, observedPositives);
        MyRegressionModel myRegressionModel = (bestX != null) ? nhsReportStore.createMyBestRegressionModel(regressionModel, bestX, observedPositives, historicalPoints) :
                nhsReportStore.createMyRegressionModel(regressionModel, covidTestsArray, meanAgeArray, observedPositives, historicalPoints);
        return myRegressionModel;
    }



    public TableOfValues getTableOfValues(MyRegressionModel myRegressionModel,
                                          int historicalPoints,
                                          Date startDate) throws ParseException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        NHSReportStore nhsReportStore = this.company.getNhsReportStore();
        List<String> dates = nhsReportStore.getDatesColumnToTableOfValues(historicalPoints, startDate);
        TestStore testStore = new TestStore();
        int[] observedPositives = testStore.getObservedPositivesToTableOfValues(historicalPoints, dates);
        RegressionModel regressionModel = this.company.getRegressionModel();
        //FALTA ARRANJAR FORMA DE IR BUSCAR A MELHOR VARIÁVEL
        double[] covidTestsInHistoricalPoints = testStore.getNumberOfCovidTestsInHistoricalPoints(dates);
        List<Double> estimatedPositives = regressionModel.getEstimatedPositives(myRegressionModel, covidTestsInHistoricalPoints);
        List<ConfidenceInterval> confidenceIntervals = getConfidenceIntervalListForTableOfValues(myRegressionModel, regressionModel, covidTestsInHistoricalPoints);

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









