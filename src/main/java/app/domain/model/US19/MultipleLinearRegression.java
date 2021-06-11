package app.domain.model.US19;

public class MultipleLinearRegression {
    private double b0;
    private double b1;
    private double b2;
    private double[] regressionCoefficients;
    private int n;
    private double r2;
    private double r2Adjusted;
    private double sr;
    private double se;

    private static final int NUM_REG_COEFFICIENTS = 2;

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
        double[][] xT = transposeMatrix(x);

        System.out.println("xT:");
        imprimir(xT);
        System.out.println();

        //determine xTx
        double[][] xTx = matrixMultiplication(xT, x);

        System.out.println("xTx:");
        imprimir(xTx);
        System.out.println();

        //determine inverse of xTx
        double[][] xTxInverse = invert(xTx);

        System.out.println("xTxInverse: ");
        imprimir(xTxInverse);
        System.out.println();

        //determine xTy
        double[] xTy = matrixWithVectorMultiplication(xT, y);

        System.out.println("xTy: ");
        imprimir(xTy);
        System.out.println();

        //determine the vector containing the regression coefficients
        regressionCoefficients = matrixWithVectorMultiplication(xTxInverse, xTy);

        System.out.println("Regression Coefficients Vector: ");
        imprimir(regressionCoefficients);
        System.out.println();

        b0 = regressionCoefficients[0];
        b1 = regressionCoefficients[1];
        b2 = regressionCoefficients[2];

        double ybar = mean(y);
        

        //regressionCoefficientsT = regressionCoefficients (in Code!)
        //determine ^BTxTy
        double regressionsCoefficientsTxTy = vectorWithVectorMultiplication(regressionCoefficients, xTy);
        System.out.println("^BTxTy = " + regressionsCoefficientsTxTy);

        //y = yT in Code!
        //determine yTy
        double yTy = vectorWithVectorMultiplication(y, y);
        System.out.println("yTy = " + yTy);

        //determine SQr
        sr = regressionsCoefficientsTxTy - (n * Math.pow(ybar, 2));
        //determine SQe
        se = yTy - regressionsCoefficientsTxTy;
        //determine SQt
        double st = sr + se;

        System.out.println("SQr = " + sr);
        System.out.println("SQe = " + se);

        //determine r2 and r2Adjusted
        r2 = sr / st;
        r2Adjusted = 1 - ((n-1.0) / (n-(NUM_REG_COEFFICIENTS+1)) * (1-r2));

        System.out.println("R2 = " + r2);
        System.out.println("R2 adjusted = " + r2Adjusted);

    }


    private double[][] transposeMatrix(double[][] x) {
        int rowX = x.length, columnX = x[0].length;
        double[][] xT = new double[columnX][rowX];

        for (int i = 0; i < rowX; i++) {
            for (int j = 0; j < columnX; j++) {
                xT[j][i] = x[i][j];
            }
        }
        return xT;
    }


    private double[][] matrixMultiplication(double[][] matrix, double[][] otherMatrix) {
        if(matrix[0].length != otherMatrix.length)
            throw new IllegalArgumentException("The multiplication is not possible with" + matrix[0].length + "columns from the" +
                    "first matrix and" + otherMatrix.length + "lines from the second matrix!");

        double[][] result = new double[matrix.length][otherMatrix[0].length];

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < otherMatrix[0].length; column++) {
                for (int k = 0; k < matrix[0].length; k++) {
                    result[row][column] += matrix[row][k] * otherMatrix[k][column];
                }
            }
        }
        return result;
    }

    private double[] matrixWithVectorMultiplication(double[][] matrix, double[] vector) {
        if(matrix[0].length != vector.length)
            throw new IllegalArgumentException("The multiplication is not possible with" + matrix[0].length + "columns from the" +
                    "matrix and" + vector.length + "lines from the vector!");

        int rows = matrix.length;
        int columns = matrix[0].length;

        double[] result = new double[rows];

        for (int row = 0; row < rows; row++) {
            double sum = 0;
            for (int column = 0; column < columns; column++) {
                sum += matrix[row][column] * vector[column];
            }
            result[row] = sum;
        }
        return result;
    }

    private double vectorWithVectorMultiplication(double[] vector, double[] otherVector) {
        if(vector.length != otherVector.length)
            throw new IllegalArgumentException("The multiplication is not possible with" + vector.length + "columns from the" +
                    "vector and" + otherVector.length + "lines from the matrix!");

        double result = 0;
        for (int i = 0; i < vector.length; i++) {
            result += vector[i] * otherVector[i];
        }

        return result;
    }

    private double[][] invert(double a[][]) {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i)
            b[i][i] = 1;

        // Transform the matrix into an upper triangle
        gaussian(a, index);

        // Update the matrix b[i][j] with the ratios stored
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k] -= a[index[j]][i] * b[index[i]][k];

        // Perform backward substitutions
        for (int i=0; i<n; ++i) {
            x[n-1][i] = b[index[n-1]][i] / a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k) {
                    x[j][i] -= a[index[j]][k] * x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

    // Method to carry out the partial-pivoting Gaussian
    // elimination.  Here index[] stores pivoting order.
    private void gaussian(double[][] a, int[] index) {
        int n = index.length;
        double c[] = new double[n];

        // Initialize the index
        for (int i = 0; i < n; ++i)
            index[i] = i;

        // Find the rescaling factors, one from each row
        for (int i=0; i<n; ++i) {
            double c1 = 0;
            for (int j=0; j<n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        // Search the pivoting element from each column
        int k = 0;
        for (int j = 0; j < n-1; ++j) {
            double pi1 = 0;
            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j+1; i < n; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];
                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;
                // Modify other elements accordingly
                for (int l = j+1; l < n; ++l)
                    a[index[i]][l] -= pj * a[index[j]][l];
            }
        }
    }

    public double mean(double[] x) {
        double sum = 0;
        for (int i = 0; i < x.length; i++) {
            sum += x[i];
        }
        return sum / x.length;
    }

    @Override
    public String toString() {
        return String.format("^y=%f + %fx1 + %fx2%nR2 = %s", b0, b1, b2, r2);
    }

    //APAGAR POSTERIORMENTE!!!!!!!
    public static void imprimir(double[][] array) {
        for (int linha = 0; linha < array.length; linha++) {
            for (int coluna = 0; coluna < array[linha].length; coluna++) {
                System.out.print(array[linha][coluna] + " ");
            }
            System.out.println();
        }
    }

    //APAGAR!!!!!!
    public static void imprimir(double[] vector) {
        for (int i = 0; i < vector.length; i++) {
            System.out.println(vector[i]);
        }
    }
}
