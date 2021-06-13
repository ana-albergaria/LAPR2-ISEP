package app.domain.model;

/**
 * Represents a Significance Model Anova through:
 * a My Regression Model,
 * the Hypothesis Test for the Test for Significance of Regression (testRegSignificance),
 * the significance level
 *
 * @author Ana Albergaria
 */

public class SignificanceModelAnova {
    /**
     * The MyRegressionModel of the HypothesisTest.
     */
    private MyRegressionModel myRegressionModel;
    /**
     * The Hypothesis Test for the Test for Significance of Regression.
     */
    private HypothesisTest testRegSignificance;
    /**
     * The significance level.
     */
    private double significanceLevel;
    /**
     * The regression sum of squares.
     */
    private final double sr;
    /**
     * The residual sum of squares.
     */
    private final double se;
    /**
     * The total sum of squares.
     */
    private final double st;
    /**
     * The regression mean square.
     */
    private final double mr;
    /**
     * The mean squared error.
     */
    private final double me;
    /**
     * The observed value f for the Test for Significance of Regression.
     */
    private final double f;
    /**
     * The degrees of freedom regarding the regression mean square.
     */
    private final int deg_freedom_sr;
    /**
     * The degrees of freedom regarding the mean squared error.
     */
    private final int deg_freedom_se;
    /**
     * The degrees of freedom regarding the total sum of squares.
     */
    private final int deg_freedom_st;

    /**
     * The number of the Regression Coefficients of the Simple Linear Regression.
     * (it doesn't include B0)
     *
     */
    private static final int NUM_REG_COEFFICIENTS_SLR = 1;
    /**
     * The number of the Regression Coefficients of the Multiple Linear Regression.
     * (it doesn't include B0)
     *
     */
    private static final int NUM_REG_COEFFICIENTS_MLR = 2;

    /**
     * Builds a SignificanceModelAnova's instance receiving:
     * the myRegressionModel
     * the regression sum of squares (sr),
     * the residual sum of squares (se),
     * the significance level
     *
     * @param myRegressionModel the myRegressionModel of the SignificanceModelAnova
     * @param sr the regression sum of squares of the SignificanceModelAnova
     * @param se the residual sum of squares of the SignificanceModelAnova
     * @param significanceLevel the significance level of the SignificanceModelAnova
     */
    public SignificanceModelAnova(MyRegressionModel myRegressionModel,
                                  double sr,
                                  double se,
                                  double significanceLevel) {
        this.myRegressionModel = myRegressionModel;
        //k - coefficients of regression but without the first one - B0
        int k = (myRegressionModel.getSecondIndVariable() == null) ? NUM_REG_COEFFICIENTS_SLR : NUM_REG_COEFFICIENTS_MLR;
        this.deg_freedom_sr = k;
        this.deg_freedom_se = myRegressionModel.getNumberOfObservations() - (k + 1);
        this.deg_freedom_st = myRegressionModel.getNumberOfObservations() - 1;
        this.sr = sr;
        this.se = se;
        this.st = sr + se;
        this.mr = sr / deg_freedom_sr;
        this.me = se / deg_freedom_se;
        this.f = mr / me;
        this.significanceLevel = significanceLevel;
        this.testRegSignificance = new HypothesisTest(myRegressionModel, f, deg_freedom_sr, deg_freedom_se, significanceLevel);
    }

    /**
     * Returns the textual description of the SignificanceModelAnova's instance.
     *
     * @return characteristics of the SignificanceModelAnova
     */
    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("Significance model with Anova\n");
        text.append("H0: b=0  H1:b<>0\n");
        text.append(String.format("%-20s%-20s%-20s%-20s%-20s%n", "", "df", "SS", "MS","F"));
        text.append(String.format("%-20s%-20d%-20f%-20f%-20f%n", "Regression", deg_freedom_sr, sr, mr,f));
        text.append(String.format("%-20s%-20d%-20f%-20f%n", "Residual", deg_freedom_se, se, me));
        text.append(String.format("%-20s%-20d%-20f%n%n", "Total", deg_freedom_st, st));
        text.append(testRegSignificance.toStringForAnova());
        return text.toString();
    }
}
