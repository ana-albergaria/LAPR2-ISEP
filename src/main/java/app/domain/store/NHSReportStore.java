package app.domain.store;

import app.domain.interfaces.RegressionModel;
import app.domain.model.HypothesisTest;
import app.domain.model.MyRegressionModel;
import app.domain.model.NHSDailyReport;
import app.domain.model.SignificanceModelAnova;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NHSReportStore {

    private List<NHSDailyReport> nhsDailyReportList = new ArrayList<>();

    public MyRegressionModel createMyRegressionModel(RegressionModel regressionModel,
                                                  int historicalPoints,
                                                  List< List<Double> > dataList) throws ParseException, IllegalAccessException, InstantiationException, ClassNotFoundException {

        double[] covidTestsArray = getDoubleArrayWithData(dataList, 0);
        double[] meanAgeArray = getDoubleArrayWithData(dataList, 1);
        double[] observedPositives = getDoubleArrayWithData(dataList, 2);

        MyRegressionModel myRegressionModel = regressionModel.getRegressionModel(covidTestsArray,
                meanAgeArray, observedPositives, historicalPoints);

        return myRegressionModel;
    }

    public HypothesisTest createHypothesisTest(RegressionModel regressionModel, MyRegressionModel myRegressionModel) {
        return regressionModel.getHypothesisTest(myRegressionModel);
    }

    public SignificanceModelAnova createSignificanceModelAnova(RegressionModel regressionModel, MyRegressionModel myRegressionModel) {
        return regressionModel.getSignificanceModelAnova(myRegressionModel);
    }

    public double[] getDoubleArrayWithData(List<List<Double>> covidTestAndMeanAgeList, int index) {
        Double[] extractedArray = new Double[covidTestAndMeanAgeList.get(index).size()];
        double[] wishedArray = new double[covidTestAndMeanAgeList.get(index).size()];
        for (int i = 0; i < wishedArray.length; i++) {
            wishedArray[i] = extractedArray[i];
        }
        return wishedArray;
    }

    /*
    WARNING - ter em atenção que ao converter a String selecionada pelo administrador
    ou a que está definida na configuration file,
     */
    public List<String> getDatesColumnToTableOfValues(int numberOfObservations,
                                              Date currentDate) {
        List<String> dates = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);

        for (int i = 0; i < numberOfObservations; i++) {
            dates.add(sdf.format(currentDate));
            cal.add(Calendar.DAY_OF_MONTH,-1);
            currentDate = cal.getTime();
        }
        return dates;
    }
}
