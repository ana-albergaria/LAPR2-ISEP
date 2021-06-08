package app.controller;

import app.domain.interfaces.RegressionModel;
import app.domain.model.*;
import app.domain.store.NHSReportStore;
import app.domain.store.TestStore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

/*
    public boolean createNHSDailyReport() throws ClassNotFoundException, InstantiationException, ParseException, IllegalAccessException {
        RegressionModel regressionModel = this.company.getRegressionModel();
        int historicalPoints = this.company.getHistoricalPoints();
        List<List<Double>> dataList = getDataListToFitTheModel();

        NHSReportStore nhsReportStore = this.company.getNhsReportStore();
        MyRegressionModel myRegressionModel = nhsReportStore.createMyRegressionModel(regressionModel, historicalPoints, dataList);
        HypothesisTest hypothesisTest = nhsReportStore.createHypothesisTest(regressionModel, myRegressionModel);
        SignificanceModelAnova modelAnova = nhsReportStore.createSignificanceModelAnova(regressionModel, myRegressionModel);

    }

 */


    public List<List<Double>> getDataListToFitTheModel() throws ParseException {
        List<Date> intervalDates = this.company.getDateInterval();
        Date beginDate = intervalDates.get(0), endDate = intervalDates.get(1);
        TestStore testStore = this.company.getTestStore();
        List<List<Double>> dataList = testStore.getAllDataToFitTheModel(beginDate, endDate);
        return dataList;
    }


    /*
    public TableOfValues getTableOfValues(int historicalPoints, Date currentDate) throws ParseException {
        NHSReportStore nhsReportStore = this.company.getNhsReportStore();
        List<String> dates = nhsReportStore.getDatesColumnToTableOfValues(historicalPoints, currentDate);
        TestStore testStore = new TestStore();
        int[] observedPositives = testStore.getObservedPositivesToTableOfValues(historicalPoints, dates);
    }

     */



}









