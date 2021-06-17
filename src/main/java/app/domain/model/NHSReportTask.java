
package app.domain.model;

import app.controller.ImportTestController;
import app.domain.interfaces.RegressionModel;
import app.ui.console.utils.TestFileUtils;
import app.domain.store.NHSReportStore;
import app.domain.store.TestStore;
import app.mappers.dto.TestFileDTO;
import com.nhs.report.Report2NHS;
import net.sourceforge.barbecue.BarcodeException;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class NHSReportTask extends TimerTask {
    private String regressionModelClass;
    private RegressionModel regressionModel;
    private int historicalPoints;
    private double significanceLevel;
    private double confidenceLevel;
    private String dateInterval;
    private TestStore testStore;
    private NHSReportStore nhsReportStore;
    private NHSReport nhsReport;

    public NHSReportTask(String regressionModelClass,
                         String historicalPoints,
                         String significanceLevel,
                         String confidenceLevel,
                         String dateInterval,
                         TestStore testStore,
                         NHSReportStore nhsReportStore) {
        this.regressionModelClass = regressionModelClass;
        this.historicalPoints = Integer.parseInt(historicalPoints);
        this.significanceLevel = Double.parseDouble(significanceLevel);
        this.confidenceLevel = Double.parseDouble(confidenceLevel);
        this.dateInterval = dateInterval;
        this.testStore = testStore;
        this.nhsReportStore = nhsReportStore;
    }

    public boolean createNHSDailyReport() throws ClassNotFoundException, InstantiationException, ParseException, IllegalAccessException {
        this.regressionModel = getRegressionModel(regressionModelClass);
        List<List<Double>> dataList = getDataListToFitTheModel();
        double[] covidTestsArray = nhsReportStore.getDoubleArrayWithData(dataList, 0);
        double[] meanAgeArray = nhsReportStore.getDoubleArrayWithData(dataList, 1);
        double[] observedPositives = nhsReportStore.getDoubleArrayWithData(dataList, 2);
        Integer bestXIndex = nhsReportStore.getBestXIndex(regressionModel, covidTestsArray, meanAgeArray, observedPositives);

        MyRegressionModel myRegressionModel = getMyRegressionModel(regressionModel, bestXIndex, covidTestsArray, meanAgeArray, observedPositives, historicalPoints);
        HypothesisTest hypothesisTest = nhsReportStore.createHypothesisTest(regressionModel, myRegressionModel, significanceLevel);
        SignificanceModelAnova modelAnova = nhsReportStore.createSignificanceModelAnova(regressionModel, myRegressionModel, significanceLevel);

        Date startDate = nhsReportStore.getStartDate();
        TableOfValues tableOfValues = getTableOfValues(myRegressionModel, bestXIndex, historicalPoints, startDate);

        this.nhsReport = nhsReportStore.createNHSDailyReport(myRegressionModel,hypothesisTest,modelAnova,tableOfValues);
        return nhsReportStore.validateNHSDailyReport(nhsReport);
    }


    public List<List<Double>> getDataListToFitTheModel() throws ParseException {
        String[] intervalDatesInString = dateInterval.split("-");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date beginDate = sdf.parse(intervalDatesInString[0]), endDate = sdf.parse(intervalDatesInString[1]);
        List<List<Double>> dataList = testStore.getAllDataToFitTheModel(beginDate, endDate);
        return dataList;
    }

    public MyRegressionModel getMyRegressionModel(RegressionModel regressionModel,
                                                  Integer bestXIndex,
                                                  double[] covidTestsArray,
                                                  double[] meanAgeArray,
                                                  double[] observedPositives,
                                                  int historicalPoints) {

        MyRegressionModel myRegressionModel = (bestXIndex == null) ? nhsReportStore.createMyRegressionModel(regressionModel, covidTestsArray, meanAgeArray, observedPositives, historicalPoints) :
                ((bestXIndex == 1) ? nhsReportStore.createMyBestRegressionModel(regressionModel, covidTestsArray, observedPositives, historicalPoints) : nhsReportStore.createMyBestRegressionModel(regressionModel, meanAgeArray, observedPositives, historicalPoints));
        return myRegressionModel;
    }



    public TableOfValues getTableOfValues(MyRegressionModel myRegressionModel,
                                          Integer bestXIndex,
                                          int historicalPoints,
                                          Date startDate) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        List<String> dates = nhsReportStore.getDatesColumnToTableOfValues(historicalPoints, startDate);
        int[] observedPositives = testStore.getObservedPositivesToTableOfValues(historicalPoints, dates);
        this.regressionModel = getRegressionModel(regressionModelClass);

        Double[] bestXInHistoricalPoints;
        List<Double> estimatedPositives;
        Double[] numCovidTestsInHistoricalPoints = testStore.getNumberOfCovidTestsInHistoricalPoints(dates);
        Double[] meanAgeInHistoricalPoints = testStore.getMeanAgeInHistoricalPoints(dates);
        List<ConfidenceInterval> confidenceIntervals;

        if(bestXIndex != null) {
            //for Simple Linear Regression
            if(bestXIndex == 1)
                bestXInHistoricalPoints = nhsReportStore.copyArray(numCovidTestsInHistoricalPoints);
            else
                bestXInHistoricalPoints = nhsReportStore.copyArray(meanAgeInHistoricalPoints);
            estimatedPositives = this.regressionModel.getEstimatedPositives(myRegressionModel, bestXInHistoricalPoints, null);
            confidenceIntervals = getConfidenceIntervalListForTableOfValues(myRegressionModel, regressionModel, bestXInHistoricalPoints, null, bestXIndex);
        } else {
            //for Multiple Linear Regression
            estimatedPositives = this.regressionModel.getEstimatedPositives(myRegressionModel, numCovidTestsInHistoricalPoints, meanAgeInHistoricalPoints);
            confidenceIntervals = getConfidenceIntervalListForTableOfValues(myRegressionModel, regressionModel, numCovidTestsInHistoricalPoints, meanAgeInHistoricalPoints, bestXIndex);
        }

        TableOfValues tableOfValues = nhsReportStore.createTableOfValues(myRegressionModel, dates, observedPositives, estimatedPositives, confidenceIntervals);
        return tableOfValues;
    }

    public List<ConfidenceInterval> getConfidenceIntervalListForTableOfValues(MyRegressionModel myRegressionModel,
                                                                              RegressionModel regressionModel,
                                                                              Double[] x1InHistoricalPoints,
                                                                              Double[] x2InHistoricalPoints,
                                                                              Integer bestXIndex) {
        List<ConfidenceInterval> confidenceIntervals;
        if(bestXIndex != null) //for Simple Linear Regression
            confidenceIntervals = regressionModel.getConfidenceIntervalList(myRegressionModel, x1InHistoricalPoints, null, confidenceLevel);
        else //For Multiple Linear Regression
            confidenceIntervals = regressionModel.getConfidenceIntervalList(myRegressionModel, x1InHistoricalPoints, x2InHistoricalPoints, confidenceLevel);
        return confidenceIntervals;
    }

    public RegressionModel getRegressionModel(String regressionModelCLass) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> oClass = Class.forName(regressionModelCLass);
        return (RegressionModel) oClass.newInstance();
    }

    @Override
    public void run() {
        try {

            //só para teste
            TestFileUtils testFileUtils = new TestFileUtils();
            ImportTestController importTestCtrl = new ImportTestController();
            List<TestFileDTO> procedData = testFileUtils.getTestsDataToDto("tests_Covid_short.csv");
            for (TestFileDTO testData : procedData) {
                importTestCtrl.importTestFromFile(testData);
            }
            //fim teste

            boolean success = createNHSDailyReport();
            if(success) {
                File path = new File("./NHSReport/");
                if(!path.exists())
                    path.mkdir();

                Report2NHS.writeUsingFileWriter(this.nhsReport.toString());

                Logger logger = Logger.getLogger(NHSReportTask.class.getSimpleName());
                FileHandler fh;

                // This block configure the logger with handler and formatter
                fh = new FileHandler("./NHSReport.log", true);
                logger.addHandler(fh);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);

                // the following statement is used to log any messages
                logger.info("NHS Daily Report sent");

                logger.setUseParentHandlers(false);

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BarcodeException e) {
            e.printStackTrace();
        }

    }
}


