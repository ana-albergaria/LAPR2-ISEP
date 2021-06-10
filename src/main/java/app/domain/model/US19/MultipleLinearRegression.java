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
            x[i][0] = 1;
            x[i][1] = x1[i];
            x[i][2] = x2[i];
        }

        System.out.println("X:");
        imprimir(x);
        System.out.println();

        //determine xT (transpose of the matrix X)
        int rowX = x.length, columnX = x[0].length;
        double[][] xT = new double[columnX][rowX];
        for (int i = 0; i < rowX; i++) {
            for (int j = 0; j < columnX; j++) {
                xT[j][i] = x[i][j];
            }
        }

        System.out.println("xT:");
        imprimir(xT);
        System.out.println();




    }
    /*
    private double[][] matrixMultiplication(double[][] matrix, double[][] otherMatrix) {

    }
     */

    public static void imprimir(double[][] array) {
        for (int linha = 0; linha < array.length; linha++) {
            for (int coluna = 0; coluna < array[linha].length; coluna++) {
                System.out.print(array[linha][coluna] + " ");
            }
            System.out.println();
        }
    }
}
