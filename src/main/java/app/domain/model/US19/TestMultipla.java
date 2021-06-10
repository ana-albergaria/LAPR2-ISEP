package app.domain.model.US19;

public class TestMultipla {
    public static void main(String[] args) {

        //MATCP - Teórica Regressão Linear Múltipla
        double[] x1 = {80.0, 93.0, 100.0, 82.0, 90.0, 99.0, 81.0, 96.0, 94.0, 93.0, 97.0, 95.0};
        double[] x2 = {8.0, 9.0, 10.0, 12.0, 11.0, 8.0, 8.0, 10.0, 12.0, 11.0, 13.0, 11.0};
        double[] y = {2256.0, 2340.0, 2426.0, 2293.0, 2330.0, 2368.0, 2250.0, 2409.0, 2364.0, 2379.0, 2440.0, 2364.0};

        MultipleLinearRegression mlr = new MultipleLinearRegression(x1, x2, y);
        System.out.println(mlr);


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
