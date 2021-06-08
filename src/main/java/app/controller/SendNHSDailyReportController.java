package app.controller;

import app.domain.interfaces.RegressionModel;
import app.domain.model.Company;
import app.domain.model.MyRegressionModel;
import app.domain.model.NHSDailyReport;
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
    public boolean createNHSDailyReport() {

    }
     */




    public MyRegressionModel getMyRegressionModel() throws IllegalAccessException, InstantiationException, ClassNotFoundException, ParseException {
        List<Date> intervalDates = this.company.getDateInterval();
        Date beginDate = intervalDates.get(0), endDate = intervalDates.get(1);
        TestStore testStore = this.company.getTestStore();
        List< List<Double> > covidTestAndMeanAgeList = testStore.getCovidTestAndMeanAgeListDataFromDateInterval(beginDate, endDate);

        double[] covidTestsArray = getDoubleArrayWithData(covidTestAndMeanAgeList, 0);
        double[] meanAgeArray = getDoubleArrayWithData(covidTestAndMeanAgeList, 1);
        double[] observedPositives = getDoubleArrayWithData(covidTestAndMeanAgeList, 2);

        RegressionModel regressionModel = this.company.getRegressionModel();
        MyRegressionModel myRegressionModel = regressionModel.getRegressionModel(covidTestsArray, meanAgeArray, observedPositives);

        return myRegressionModel;
    }

    public double[] getDoubleArrayWithData(List< List<Double> > covidTestAndMeanAgeList, int index) {
        Double[] extractedArray = new Double[covidTestAndMeanAgeList.get(index).size()];
        double[] wishedArray = new double[covidTestAndMeanAgeList.get(index).size()];
        for (int i = 0; i < wishedArray.length; i++) {
            wishedArray[i] = extractedArray[i];
        }
        return wishedArray;
    }








}
