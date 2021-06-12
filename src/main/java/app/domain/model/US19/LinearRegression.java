package app.domain.model.US19;

/******************************************************************************
 *  Compute least squares solution to y = beta * x + alpha.
 *  Simple linear regression.
 ******************************************************************************/

import app.domain.shared.Constants;

/**
 *  The code LinearRegression class performs a simple linear regression
 *  on an set of n data points (y_i, x_i).
 *  That is, it fits a straight line y = alpha + beta * x,
 *  (where y is the response variable, x is the predictor variable,
 *  alpha is the y-intercept, and beta is the slope)
 *  that minimizes the sum of squared residuals of the linear regression model.
 *  It also computes associated statistics, including the coefficient of
 *  determination R^2 and the standard deviation of the
 *  estimates for the slope and y-intercept.
 *
 */
public class LinearRegression {
    private final double intercept, slope; //intercept - y, a | slope - x, b
    private final double r2;
    private final double svar0, svar1;

    //svar - variância
    private int n;
    private double xxbar;
    private double xbar; //média
    private double rss; // residual sum of squares
    private double ssr; // regression sum of squares
    private double s; //standard deviation (desvio padrão amostral)
    private double r2Adjusted;

    private static final double A0 = Constants.A0;
    private static final double B0 = Constants.B0;
    private static final int NUM_REG_COEFFICIENTS = 1; //temos 2 coeficientes de regressão, mas para a fórmula não conta o da var independente

    /**
     * Performs a linear regression on the data points (y[i], x[i]).
     *
     * @param  x the values of the predictor variable
     * @param  y the corresponding values of the response variable
     * @throws IllegalArgumentException if the lengths of the two arrays are not equal
     */
    public LinearRegression(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("array lengths are not equal");
        }
        n = x.length;

        // first pass
        double sumx = 0.0, sumy = 0.0, sumx2 = 0.0;
        for (int i = 0; i < n; i++) {
            sumx  += x[i];
            sumx2 += x[i]*x[i];
            sumy  += y[i];
        }
        xbar = sumx / n;
        double ybar = sumy / n;

        // second pass: compute summary statistics
        double yybar = 0.0, xybar = 0.0;
        for (int i = 0; i < n; i++) {
            xxbar += (x[i] - xbar) * (x[i] - xbar);
            yybar += (y[i] - ybar) * (y[i] - ybar);
            xybar += (x[i] - xbar) * (y[i] - ybar);
        }
        slope  = xybar / xxbar;
        intercept = ybar - slope * xbar;

        // more statistical analysis
        for (int i = 0; i < n; i++) {
            double fit = slope*x[i] + intercept;
            rss += (fit - y[i]) * (fit - y[i]); //se (Anova) residual sum of squares
            ssr += (fit - ybar) * (fit - ybar); //sr (Anova) regression sum of squares
        }

        int degreesOfFreedom = n-2;
        if(yybar == 0)
            throw new IllegalArgumentException("yybar cannot be 0!");
        r2    = ssr / yybar;
        r2Adjusted = 1 - ((n-1.0) / (n-(NUM_REG_COEFFICIENTS+1)) * (1-r2));
        double svar  = rss / degreesOfFreedom;
        s = Math.sqrt(svar); //acrescentei -> desvio padrão amostral
        svar1 = svar / xxbar;
        svar0 = svar/n + xbar*xbar*svar1;
    }

    /**
     * Returns the y-intercept alpha of the best of the best-fit line y = alpha + beta * x.
     *
     * @return the y-intercept alpha of the best-fit line y = alpha + beta * x
     */
    public double intercept() {
        return intercept;
    }

    /**
     * Returns the slope beta of the best of the best-fit line y = alpha + beta * x.
     *
     * @return the slope beta of the best-fit line y = alpha + beta * x
     */
    public double slope() {
        return slope;
    }

    /**
     * Returns the coefficient of determination R^2.
     *
     * @return the coefficient of determination R^2,
     *         which is a real number between 0 and 1
     */
    public double R2() {
        return r2;
    }

    /**
     * Returns the standard error of the estimate for the intercept.
     *
     * @return the standard error of the estimate for the intercept
     */
    public double interceptStdErr() {
        return Math.sqrt(svar0);
    }

    /**
     * Returns the standard error of the estimate for the slope.
     *
     * @return the standard error of the estimate for the slope
     */
    public double slopeStdErr() {
        return Math.sqrt(svar1);
    }

    public double getR2Adjusted() {
        return r2Adjusted;
    }

    public int getN() {
        return n;
    }

    public double getRSS() {
        return rss;
    }

    public double getSSR() {
        return ssr;
    }

    public double getS() {
        return s;
    }

    public double getXbar() {
        return xbar;
    }

    public double getXXbar() {
        return xxbar;
    }

    /**
     * Returns the expected response y given the value of the predictor
     * variable x.
     *
     * @param  x the value of the predictor variable
     * @return the expected response y given the value of the predictor
     *         variable x
     */
    public double predict(double x) {
        return slope*x + intercept;
    }

    public double calculatetObsA() {
        return (intercept-A0) / (s * Math.sqrt((1.0/n) + (Math.pow(xbar,2) / xxbar)));
    }

    public double calculateTObsB() {
        return (slope-B0) / (s * Math.sqrt(1/xxbar));
    }

    public double calculateAuxDelta(Double x0) {
        return s * Math.sqrt(1 + (1.0/n) + (Math.pow((x0-xbar),2) / xxbar));
    }


    /**
     * Returns a string representation of the simple linear regression model.
     *
     * @return a string representation of the simple linear regression model,
     *         including the best-fit line and the coefficient of determination
     *         R^2
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        //tirei 2 casas decimais para testar
        s.append(String.format("%f n + %f", slope(), intercept()));
        //tirei 3 casas decimais para testar
        s.append("  (R^2 = " + String.format("%f", R2()) + ")");
        return s.toString();
    }

}


