package app.domain.model.US19;


import app.domain.interfaces.ExternalAPI;
import app.domain.interfaces.MathCalculus;
import app.domain.model.MyRegressionModel;
import com.nhs.report.Report2NHS;

import java.io.File;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        double[] x = {825.0, 215.0, 1070.0, 550.0, 480.0, 920.0, 1350.0, 325.0, 670.0, 1215.0};
        double[] y = {3.5, 1.0, 4.0, 2.0, 1.0, 3.0, 4.5, 1.5, 3.0, 5.0};

        LinearRegression simpleLR = new LinearRegression(x, y);

        Class<?> oClass = Class.forName("app.domain.adapters.SimpleLinearRegressionAdapter");
        MathCalculus calculus = (MathCalculus) oClass.newInstance();
        MyRegressionModel myRegressionModel = calculus.getRegressionModel(x, y);

        System.out.println(simpleLR);

        /*
        Double ma = 3.0;
        System.out.println(ma.doubleValue());
         */

        File path = new File("./NHSReport/");
        if(!path.exists())
            path.mkdir();

        Report2NHS.writeUsingFileWriter(myRegressionModel.toString());


    }






}
