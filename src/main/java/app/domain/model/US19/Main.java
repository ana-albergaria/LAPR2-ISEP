package app.domain.model.US19;


import app.domain.interfaces.RegressionModel;
import app.domain.model.*;
import app.domain.store.NHSReportStore;
import app.thirdparty.SimpleLinearRegression;
import com.nhs.report.Report2NHS;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, ParseException {

        //Ex. 1 TP PL7 MATCP
        double[] x = {825.0, 215.0, 1070.0, 550.0, 480.0, 920.0, 1350.0, 325.0, 670.0, 1215.0};
        double[] y = {3.5, 1.0, 4.0, 2.0, 1.0, 3.0, 4.5, 1.5, 3.0, 5.0};

        //somente para teste os dois arrays seguintes
        double[] x0 = {8.0, 9.0, 10.0, 12.0, 11.0, 8.0, 8.0, 10.0, 12.0, 11.0};
        int[] observedPositives = {3, 1, 4, 2, 1, 3, 4, 1, 3, 5};

        //Teórica 8 MATCP - Ex Regressão Múltipla
        double[] x1 = {80.0, 93.0, 100.0, 82.0, 90.0, 99.0, 81.0, 96.0, 94.0, 93.0, 97.0, 95.0};
        double[] x2 = {8.0, 9.0, 10.0, 12.0, 11.0, 8.0, 8.0, 10.0, 12.0, 11.0, 13.0, 11.0};
        //double[] y = {2256.0, 2340.0, 2426.0, 2293.0, 2330.0, 2368.0, 2250.0, 2409.0, 2364.0, 2379.0, 2440.0, 2364.0};

        //Ex 3 f) TP PL7
        double[] xa = {20.0, 16.0, 34.0, 23.0, 27.0, 32.0, 18.0, 22.0};
        //teste
        double[] xb = {8.0, 9.0, 10.0, 12.0, 11.0, 8.0, 8.0, 10.0};

        double[] y1 = {128.0, 122.0, 168.0, 140.0, 176.0, 184.0, 144.0, 154.0};

        //TESTE EM COMPARAÇÃO COM APP
        double[] covidTests = {12.0, 12.0, 18.0, 15.0, 15.0, 20.0, 24.0, 22.0, 23.0};
        double[] observPositives = {8.0, 8.0, 12.0, 10.0, 9.0, 14.0, 18.0, 16.0, 16.0};

        SimpleLinearRegression simpleLR = new SimpleLinearRegression(x, y);

        Class<?> oClass = Class.forName("app.domain.adapters.SimpleLinearRegressionAdapter");
        RegressionModel calculus = (RegressionModel) oClass.newInstance();
        //MyRegressionModel myRegressionModel = calculus.getRegressionModel(x, x0, y, x.length);
        //MyRegressionModel myRegressionModel = calculus.getRegressionModel(xa, xb, y1);
        MyRegressionModel myRegressionModel = calculus.getRegressionModel(covidTests, x0, observPositives, 10);
        System.out.println(myRegressionModel);
        HypothesisTest hypothesisTest = calculus.getHypothesisTest(myRegressionModel, 0.05);
        System.out.println(hypothesisTest);
        SignificanceModelAnova modelAnova = calculus.getSignificanceModelAnova(myRegressionModel, 0.05);

        NHSReportStore nhsReportStore = new NHSReportStore();
        Date date = new Date();
        List<String> dates = nhsReportStore.getDatesColumnToTableOfValues(10, date);
        //observedPositives = y
        Double[] xDouble = turnPrimitiveIntoDouble(x);
        List<Double> estimatedPositives = calculus.getEstimatedPositives(myRegressionModel, xDouble, null);
        List<ConfidenceInterval> confidenceIntervalList = calculus.getConfidenceIntervalList(myRegressionModel, xDouble, null, 0.95);

        TableOfValues tableOfValues = new TableOfValues(myRegressionModel, dates, observedPositives, estimatedPositives, confidenceIntervalList);



        NHSReport report = new NHSReport(myRegressionModel, hypothesisTest, modelAnova, tableOfValues);
        System.out.println(report);

        File path = new File("./NHSReport/");
        if(!path.exists())
            path.mkdir();

        Report2NHS.writeUsingFileWriter(report.toString());




    }
    public static Double[] turnPrimitiveIntoDouble(double[] array) {
        Double[] wishedArray = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            wishedArray[i] = array[i];
        }
        return wishedArray;
    }
}








