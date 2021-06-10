package app.domain.adapters;

import app.domain.interfaces.RegressionModel;
import app.domain.model.Company;
import app.domain.model.ConfidenceInterval;
import app.domain.model.MyRegressionModel;
import app.domain.model.US19.LinearRegression;
import app.domain.shared.Constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleLinearRegressionAdapterTest {
    private Company company;
    private RegressionModel regressionModel;
    private MyRegressionModel myRegressionModel;
    private LinearRegression simpleLR;
    private int historicalPoints;


    @Before
    public void setUp() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        company = new Company("Many Labs", Constants.CLASS_BARCODE_API, Constants.CLASS_REGRESSION_MODEL, Constants.DATE_INTERVAL, Constants.HISTORICAL_POINTS, Constants.CONFIDENCE_LEVEL);
        regressionModel = this.company.getRegressionModel();
        //Ex. 1 TP PL7 MATCP
        double[] x = {825.0, 215.0, 1070.0, 550.0, 480.0, 920.0, 1350.0, 325.0, 670.0, 1215.0};
        double[] y = {3.5, 1.0, 4.0, 2.0, 1.0, 3.0, 4.5, 1.5, 3.0, 5.0};
        //somente para teste
        double[] x0 = {8.0, 9.0, 10.0, 12.0, 11.0, 8.0, 8.0, 10.0, 12.0, 11.0};
        historicalPoints = 10;
        //
        myRegressionModel = regressionModel.getRegressionModel(x, null, y, historicalPoints);
        simpleLR = new LinearRegression(x, y);
    }

    @Test
    public void getEstimatedPositives() {
        //Ex. 1 TP PL7 MATCP
        double[] x = {825.0, 215.0, 1070.0, 550.0, 480.0, 920.0, 1350.0, 325.0, 670.0, 1215.0};


    }

    @Test
    public void getConfidenceInterval() {
        //Arrange
        double x0 = 1000.0;
        double y0 = simpleLR.predict(x0);
        double auxDelta = 0.51334312;
        double confidenceLevel = 0.95;
        ConfidenceInterval expectedConfInt = new ConfidenceInterval(myRegressionModel,y0,auxDelta,confidenceLevel);
        //Act
        ConfidenceInterval confidenceInterval = regressionModel.getConfidenceInterval(myRegressionModel, x0, confidenceLevel);
        //Assert
        Assert.assertEquals(expectedConfInt, confidenceInterval);
    }


}