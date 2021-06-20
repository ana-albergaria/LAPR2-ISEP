/*package app.domain.model.US19;

import app.domain.interfaces.RegressionModel;
import app.domain.model.HypothesisTest;
import app.domain.model.MyRegressionModel;
import app.domain.model.SignificanceModelAnova;
import app.thirdparty.regressionModels.MultipleLinearRegression;

public class TestMultipla {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        //MATCP - Teórica Regressão Linear Múltipla
        double[] x1 = {80.0, 93.0, 100.0, 82.0, 90.0, 99.0, 81.0, 96.0, 94.0, 93.0, 97.0, 95.0};
        double[] x2 = {8.0, 9.0, 10.0, 12.0, 11.0, 8.0, 8.0, 10.0, 12.0, 11.0, 13.0, 11.0};
        double[] y = {2256.0, 2340.0, 2426.0, 2293.0, 2330.0, 2368.0, 2250.0, 2409.0, 2364.0, 2379.0, 2440.0, 2364.0};

        //MATCP - TP PL8 ex 1
        double[] xa = {4.0, 5.0, 5.5, 7.0, 6.0, 5.0, 7.0, 8.0, 8.5, 9.0};
        double[] xb = {36.0, 33.0, 37.0, 37.0, 34.0, 32.0, 36.0, 35.0, 38.0, 39.0};
        double[] ya = {4.0, 4.5, 5.0, 6.5, 7.0, 7.8, 7.5, 8.0, 8.0, 8.5};


        MultipleLinearRegression mlr = new MultipleLinearRegression(x1, x2, y);
        //System.out.println(mlr);
        double[] xT0 = {1.0, 80.0, 8.0};
        System.out.println("AQUI!!!!!!!");
        mlr.calculateAuxDelta(xT0);


        Class<?> oClass = Class.forName("app.domain.adapters.MultipleLinearRegressionAdapter");
        RegressionModel calculus = (RegressionModel) oClass.newInstance();
        MyRegressionModel myRegressionModel = calculus.getRegressionModel(x1, x2, y, 12);

        System.out.println(myRegressionModel);

        HypothesisTest hypothesisTest = calculus.getHypothesisTest(myRegressionModel, 0.05);
        System.out.println(hypothesisTest);

        SignificanceModelAnova modelAnova = new SignificanceModelAnova(myRegressionModel, mlr.getSR(),mlr.getSE(), 0.05);
        System.out.println(modelAnova);









    }
    public static void imprimir(double[][] array) {
        for (int linha = 0; linha < array.length; linha++) {
            for (int coluna = 0; coluna < array[linha].length; coluna++) {
                System.out.print(array[linha][coluna] + " ");
            }
            System.out.println();
        }
    }
}

 */
