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


    /*
    public MyRegressionModel getMyRegressionModel() throws IllegalAccessException, InstantiationException, ClassNotFoundException, ParseException {
        List<Date> intervalDates = this.company.getDateInterval();
        TestStore testStore = this.company.getTestStore();
        Date beginDate = intervalDates.get(0), endDate = intervalDates.get(1);
        double[] covidTestArray = testStore.getCovidTestListDataFromDateInterval(beginDate, endDate);
        RegressionModel regressionModel = this.company.getRegressionModel();
        MyRegressionModel myRegressionModel = regressionModel.getRegressionModel()




    }

     */


}
