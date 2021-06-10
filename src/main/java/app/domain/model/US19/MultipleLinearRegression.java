package app.domain.model.US19;

public class MultipleLinearRegression {
    private double[] xTx;
    private double[] xTxInverse;
    private double[] xTy;
    private double[] regressionCoefficients;
    private int n;

    public MultipleLinearRegression(double[] x1, double[] x2, double[] y) {
        if (x1.length != x2.length || x1.length != y.length) {
            throw new IllegalArgumentException("Array lengths are not equal!");
        }
        n = x1.length;

        //determine X
        double[][] x = new double[x1.length][3];
        for (int i = 0; i < x.length; i++) {


        }

    }
}
