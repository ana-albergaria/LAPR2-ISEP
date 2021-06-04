/*
package app.domain.model.US19;


import app.domain.interfaces.ExternalAPI;
import app.domain.interfaces.MathCalculus;
import app.domain.model.MyRegressionModel;
import com.nhs.report.Report2NHS;

import java.io.File;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        //Ex. 1 TP PL7 MATCP
        double[] x = {825.0, 215.0, 1070.0, 550.0, 480.0, 920.0, 1350.0, 325.0, 670.0, 1215.0};
        double[] y = {3.5, 1.0, 4.0, 2.0, 1.0, 3.0, 4.5, 1.5, 3.0, 5.0};

        double[] x0 = {8.0, 9.0, 10.0, 12.0, 11.0, 8.0, 8.0, 10.0, 12.0, 11.0};

        //Teórica 8 MATCP - Ex Regressão Múltipla
        double[] x1 = {80.0, 93.0, 100.0, 82.0, 90.0, 99.0, 81.0, 96.0, 94.0, 93.0, 97.0, 95.0};
        double[] x2 = {8.0, 9.0, 10.0, 12.0, 11.0, 8.0, 8.0, 10.0, 12.0, 11.0, 13.0, 11.0};
        //double[] y = {2256.0, 2340.0, 2426.0, 2293.0, 2330.0, 2368.0, 2250.0, 2409.0, 2364.0, 2379.0, 2440.0, 2364.0};

        //LinearRegression simpleLR = new LinearRegression(x, y);

        Class<?> oClass = Class.forName("app.domain.adapters.SimpleLinearRegressionAdapter");
        MathCalculus calculus = (MathCalculus) oClass.newInstance();
        MyRegressionModel myRegressionModel = calculus.getRegressionModel(x, x0, y);
        System.out.println(myRegressionModel);

        //System.out.println(simpleLR);


        */
/*
        Double ma = 3.0;
        System.out.println(ma.doubleValue());
         *//*


        */
/*
        File path = new File("./NHSReport/");
        if(!path.exists())
            path.mkdir();

        Report2NHS.writeUsingFileWriter(myRegressionModel.toString());
         *//*



    }






}
*/
