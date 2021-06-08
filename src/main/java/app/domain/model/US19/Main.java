package app.domain.model.US19;


import app.domain.interfaces.RegressionModel;
import app.domain.model.*;
import app.domain.store.TestStore;
import com.nhs.report.Report2NHS;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, ParseException {

        //Ex. 1 TP PL7 MATCP
        double[] x = {825.0, 215.0, 1070.0, 550.0, 480.0, 920.0, 1350.0, 325.0, 670.0, 1215.0};
        double[] y = {3.5, 1.0, 4.0, 2.0, 1.0, 3.0, 4.5, 1.5, 3.0, 5.0};

        //somente para teste
        double[] x0 = {8.0, 9.0, 10.0, 12.0, 11.0, 8.0, 8.0, 10.0, 12.0, 11.0};

        //Teórica 8 MATCP - Ex Regressão Múltipla
        double[] x1 = {80.0, 93.0, 100.0, 82.0, 90.0, 99.0, 81.0, 96.0, 94.0, 93.0, 97.0, 95.0};
        double[] x2 = {8.0, 9.0, 10.0, 12.0, 11.0, 8.0, 8.0, 10.0, 12.0, 11.0, 13.0, 11.0};
        //double[] y = {2256.0, 2340.0, 2426.0, 2293.0, 2330.0, 2368.0, 2250.0, 2409.0, 2364.0, 2379.0, 2440.0, 2364.0};

        //Ex 3 f) TP PL7
        double[] xa = {20.0, 16.0, 34.0, 23.0, 27.0, 32.0, 18.0, 22.0};
        //teste
        double[] xb = {8.0, 9.0, 10.0, 12.0, 11.0, 8.0, 8.0, 10.0};

        double[] y1 = {128.0, 122.0, 168.0, 140.0, 176.0, 184.0, 144.0, 154.0};

        LinearRegression simpleLR = new LinearRegression(x, y);

        Class<?> oClass = Class.forName("app.domain.adapters.SimpleLinearRegressionAdapter");
        RegressionModel calculus = (RegressionModel) oClass.newInstance();
        MyRegressionModel myRegressionModel = calculus.getRegressionModel(x, x0, y);
        //MyRegressionModel myRegressionModel = calculus.getRegressionModel(xa, xb, y1);
        System.out.println(myRegressionModel);
        HypothesisTest hypothesisTest = calculus.getHypothesisTest(myRegressionModel);
        System.out.println(hypothesisTest);
        SignificanceModelAnova modelAnova = calculus.getSignificanceModelAnova(myRegressionModel);

        NHSDailyReport report = new NHSDailyReport(myRegressionModel, hypothesisTest, modelAnova);
        System.out.println(report);

        /*
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,2002);
        cal.set(Calendar.MONTH,1);
        cal.set(Calendar.DAY_OF_MONTH,2);
        Date currentDate = cal.getTime();
        TestStore testStore = new TestStore();
        List<String> dates = new ArrayList<>();
        testStore.addDatesColumnToTableOfValues(3,currentDate,dates);
         */
        //testStore.getTestsWithResultsDataForTableOfValues(3, currentDate);
        /*
        System.out.println(dates);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dateToBeCompared = sdf.parse(dates.get(0));
        System.out.println("Date to be compared: " + dateToBeCompared);
        if(testStore.checkIfDatesAreEqual(currentDate, dateToBeCompared))
            System.out.println("True");
        else
            System.out.println("False");
         */

        /*
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,1998);
        cal.set(Calendar.MONTH,7);
        cal.set(Calendar.DAY_OF_MONTH,6);
        Date d1 = cal.getTime();
        Client c1 = new Client("1234567890123456","1234567890",d1,"1234567890","carlos@gmail.com","Carlos","12345678901");
        System.out.println(c1.getAge());
         */




        //System.out.println(simpleLR);

        /*
        TDistribution td= new TDistribution(7); //n-2
        double alphaTD =0.975;
        if(alphaTD> 0.5) {
            double critTD = td.inverseCumulativeProbability(alphaTD);
            System.out.println("t-student critical value: " + critTD);
        }
        else {
            double critTD = td.inverseCumulativeProbability(1 - alphaTD);
            System.out.println("t-student critical value: " + critTD);
        }
         */

        /*
        TDistribution td= new TDistribution(8); //n-2
        double alphaTD =0.95;
        if(alphaTD> 0.5) {
            double critTD = td.inverseCumulativeProbability(alphaTD);
            System.out.println("t-student critical value: " + critTD);
        }
        else {
            double critTD = td.inverseCumulativeProbability(1 - alphaTD);
            System.out.println("t-student critical value: " + critTD);
        }
         */


        /*
        Double ma = 3.0;
        System.out.println(ma.doubleValue());
         */


        File path = new File("./NHSReport/");
        if(!path.exists())
            path.mkdir();

        Report2NHS.writeUsingFileWriter(report.toString());




    }
}






