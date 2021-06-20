package app.ui.gui;

import app.controller.CompanyPerformanceAnalysisController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.commons.lang3.time.DateUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Marta Ribeiro 1201592
 */
public class CheckCompanyPerformanceUI2 implements Initializable {

    private App mainApp;

    @FXML
    private TextArea cliTesOverview;

    public CheckCompanyPerformanceUI2() {
    }

    public void setMainApp(App mainApp){
        this.mainApp=mainApp;
    }

    private CheckCompanyPerformanceUI1 checkCompPerUI1;

    public void setCheckCompPerUI1(CheckCompanyPerformanceUI1 checkCompPerUI1) {
        this.checkCompPerUI1 = checkCompPerUI1;
    }

    private CompanyPerformanceAnalysisController controller;

    public void setController(CompanyPerformanceAnalysisController controller) {
        this.controller = controller;
    }

    private int[] chartValues;

    private String option;

    public void setOption(String option) {
        this.option = option;
    }

    public void setCliTesOverview(TextArea cliTesOverview) {
        this.cliTesOverview = cliTesOverview;
    }

    private ArrayList<int[]> testsInfo = new ArrayList<>();

    public void setTestsInfo(ArrayList<int[]> testsInfo) {
        this.testsInfo = testsInfo;
    }

    private ArrayList<Integer> chosenGraphData = new ArrayList<>();

    public void setChosenGraphData(ArrayList<Integer> chosenGraphData) {
        this.chosenGraphData = chosenGraphData;
    }

    public void setLineChart(LineChart<String, Integer> lineChart) {
        this.lineChart = lineChart;
    }

    private String daysOption;

    public void setDaysOption(String daysOption) {
        this.daysOption = daysOption;
    }

    private Date beginning;
    private Date end;

    public void setBeginning(Date beginning) {
        this.beginning = beginning;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @FXML
    private Button exitBtn;

    @FXML
    private LineChart<String, Integer> lineChart;

    @FXML
    private Button returnBtn;

    @FXML
    private Button resultsWeeksBtn;

    @FXML
    private Button resultsMonthsBtn;

    @FXML
    private Button resultsDaysBtn;

    @FXML
    private Button diagnosisWeeksBtn;

    @FXML
    private Button diagnosisMonthsBtn;

    @FXML
    private Button diagnosisYearsBtn;

    @FXML
    private Button resultsYearsBtn;

    @FXML
    private Button diagnosisDaysBtn;

    @FXML
    void resultsDaysAction(ActionEvent event) {
        lineChart.getData().clear();
        int[] bothValues;
        ArrayList<Integer> desiredData = new ArrayList<>();
        setChosenGraphData(desiredData);
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
        setTestsInfo(controller.getTestInfoPerDay());
        for (int i = 0; i < testsInfo.size(); i++) {
            bothValues = testsInfo.get(i);
            desiredData.add(bothValues[0]);
        }
        series.setName("Tests Waiting For Results");
        System.out.println(desiredData);
        setChosenGraphData(desiredData);
        for (int i = 0; i < chosenGraphData.size(); i++) {
            series.getData().add(new XYChart.Data<String, Integer>("",chosenGraphData.get(i)));
        }
        lineChart.getData().clear();
        lineChart.getData().add(series);
        setLineChart(lineChart);
    }

    @FXML
    void resultsWeeksAction(ActionEvent event) {
        lineChart.getData().clear();
        int[] bothValues;
        ArrayList<Integer> desiredData = new ArrayList<>();
        setChosenGraphData(desiredData);
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
        setTestsInfo(controller.getTestInfoPerWeek());
        for (int i = 0; i < testsInfo.size(); i++) {
            bothValues = testsInfo.get(i);
            desiredData.add(bothValues[0]);
        }
        series.setName("Tests Waiting For Results");
        System.out.println(desiredData);
        setChosenGraphData(desiredData);
        for (int i = 0; i < chosenGraphData.size(); i++) {
            series.getData().add(new XYChart.Data<String, Integer>("",chosenGraphData.get(i)));
        }
        lineChart.getData().clear();
        lineChart.getData().add(series);
        setLineChart(lineChart);
    }

    @FXML
    void resultsMonthsAction(ActionEvent event) {
        lineChart.getData().clear();
        int[] bothValues;
        ArrayList<Integer> desiredData = new ArrayList<>();
        setChosenGraphData(desiredData);
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
        setTestsInfo(controller.getTestInfoPerMonth());
        for (int i = 0; i < testsInfo.size(); i++) {
            bothValues = testsInfo.get(i);
            desiredData.add(bothValues[0]);
        }
        series.setName("Tests Waiting For Results");
        System.out.println(desiredData);
        setChosenGraphData(desiredData);
        for (int i = 0; i < chosenGraphData.size(); i++) {
            series.getData().add(new XYChart.Data<String, Integer>("",chosenGraphData.get(i)));
        }
        lineChart.getData().clear();
        lineChart.getData().add(series);
        setLineChart(lineChart);
    }

    @FXML
    void resultsYearsAction(ActionEvent event) {
        lineChart.getData().clear();
        int[] bothValues;
        ArrayList<Integer> desiredData = new ArrayList<>();
        setChosenGraphData(desiredData);
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
        setTestsInfo(controller.getTestInfoPerYear());
        for (int i = 0; i < testsInfo.size(); i++) {
            bothValues = testsInfo.get(i);
            desiredData.add(bothValues[0]);
        }
        series.setName("Tests Waiting For Results");
        System.out.println(desiredData);
        setChosenGraphData(desiredData);
        for (int i = 0; i < chosenGraphData.size(); i++) {
            series.getData().add(new XYChart.Data<String, Integer>("",chosenGraphData.get(i)));
        }
        lineChart.getData().clear();
        lineChart.getData().add(series);
        setLineChart(lineChart);
    }

    @FXML
    void diagnosisDaysAction(ActionEvent event) {
        lineChart.getData().clear();
        int[] bothValues;
        ArrayList<Integer> desiredData = new ArrayList<>();
        setChosenGraphData(desiredData);
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
        setTestsInfo(controller.getTestInfoPerDay());
        for (int i = 0; i < testsInfo.size(); i++) {
            bothValues = testsInfo.get(i);
            desiredData.add(bothValues[1]);
        }
        series.setName("Tests Waiting For Diagnosis");
        System.out.println(desiredData);
        setChosenGraphData(desiredData);
        for (int i = 0; i < chosenGraphData.size(); i++) {
            series.getData().add(new XYChart.Data<String, Integer>("",chosenGraphData.get(i)));
        }
        lineChart.getData().clear();
        lineChart.getData().add(series);
        setLineChart(lineChart);
    }

    @FXML
    void diagnosisWeeksAction(ActionEvent event) {
        lineChart.getData().clear();
        int[] bothValues;
        ArrayList<Integer> desiredData = new ArrayList<>();
        setChosenGraphData(desiredData);
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
        setTestsInfo(controller.getTestInfoPerWeek());
        for (int i = 0; i < testsInfo.size(); i++) {
            bothValues = testsInfo.get(i);
            desiredData.add(bothValues[1]);
        }
        series.setName("Tests Waiting For Diagnosis");
        System.out.println(desiredData);
        setChosenGraphData(desiredData);
        for (int i = 0; i < chosenGraphData.size(); i++) {
            series.getData().add(new XYChart.Data<String, Integer>("",chosenGraphData.get(i)));
        }
        lineChart.getData().clear();
        lineChart.getData().add(series);
        setLineChart(lineChart);
    }

    @FXML
    void diagnosisMonthsAction(ActionEvent event) {
        lineChart.getData().clear();
        int[] bothValues;
        ArrayList<Integer> desiredData = new ArrayList<>();
        setChosenGraphData(desiredData);
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
        setTestsInfo(controller.getTestInfoPerMonth());
        for (int i = 0; i < testsInfo.size(); i++) {
            bothValues = testsInfo.get(i);
            desiredData.add(bothValues[1]);
        }
        series.setName("Tests Waiting For Diagnosis");
        System.out.println(desiredData);
        setChosenGraphData(desiredData);
        for (int i = 0; i < chosenGraphData.size(); i++) {
            series.getData().add(new XYChart.Data<String, Integer>("",chosenGraphData.get(i)));
        }
        lineChart.getData().clear();
        lineChart.getData().add(series);
        setLineChart(lineChart);
    }

    @FXML
    void diagnosisYearsAction(ActionEvent event) {
        lineChart.getData().clear();
        int[] bothValues;
        ArrayList<Integer> desiredData = new ArrayList<>();
        setChosenGraphData(desiredData);
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
        setTestsInfo(controller.getTestInfoPerYear());
        for (int i = 0; i < testsInfo.size(); i++) {
            bothValues = testsInfo.get(i);
            desiredData.add(bothValues[1]);
        }
        series.setName("Tests Waiting For Diagnosis");
        System.out.println(desiredData);
        setChosenGraphData(desiredData);
        for (int i = 0; i < chosenGraphData.size(); i++) {
            series.getData().add(new XYChart.Data<String, Integer>("",chosenGraphData.get(i)));
        }
        lineChart.getData().clear();
        lineChart.getData().add(series);
        setLineChart(lineChart);
    }

    @FXML
    void exitAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Many Labs Application");
        alert.setHeaderText("Action confirmation.");
        alert.setContentText("Do you really wish to exit the application?");

        ((Labeled) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
        ((Labeled) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");

        if (alert.showAndWait().get() == ButtonType.OK) {
            ((Stage) exitBtn.getScene().getWindow()).close();
        }

    }

    @FXML
    void returnAction(ActionEvent event) {
        try {
            CheckCompanyPerformanceUI1 checkCompanyPerformanceUI1 = (CheckCompanyPerformanceUI1) this.mainApp.replaceSceneContent("/fxml/CheckCompanyPerformance1.fxml");
            checkCompanyPerformanceUI1.setMainApp(mainApp);
            checkCompanyPerformanceUI1.setLcUI(this.checkCompPerUI1.getLcUI());
            checkCompanyPerformanceUI1.addOptions();
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void analyseCompany() {
        Date[] limits= controller.findWorstSubIntWithChosenAlgorithm();
        String text = "Interval When The Company Was Less Effective In Responding";
        if (limits[0]==null && limits[1]==null){
            text = text + "\n ↪ [The company was equally effective during the whole interval.]";
        } else {
            text = text + "\n ↪ From: " + limits[0].toString() + "\n ↪ To: " + limits[1].toString();
        }
        text = text + "\nTotal Number Of Clients In The System" + "\n ↪ " + controller.getClientsInfoPerInterval();
        text = text + "\nTotal Number of Processed Tests In The System" + "\n ↪ " + controller.getNumTestsProcessedInterval();
        text = text + "\nTests Overview Graphs" + "\n ↪ ";
        setDaysOption(checkCompPerUI1.getChosenOption());
        if (daysOption.equals("A Day")){
            text = text + "[Unavailable for day analysis.]";
        } else {
            setBeginning(checkCompPerUI1.getAnalysisBegDate());
            setEnd(checkCompPerUI1.getAnalysisEndDate());
            if (DateUtils.addDays(end,-7).before(beginning) || DateUtils.addDays(end,-7).equals(beginning)){
                text = text + "[The chosen interval is less than a week long. Analysis over weeks, months and years are not available. The other ones can be seen by selecting one of the options bellow.]";
            } else if (DateUtils.addMonths(end,-1).before(beginning) || DateUtils.addMonths(end,-1).equals(beginning)){
                text = text + "[The chosen interval is less than a month long. Analysis over months and years are not available. The other ones can be seen by selecting one of the options bellow.]";
            } else if (DateUtils.addYears(end,-1).before(beginning) || DateUtils.addYears(end,-1).equals(beginning)){
                text = text + "[The chosen interval is less than a year long. Analysis over years are not available. The other ones can be seen by selecting one of the options bellow.]";
            } else {
                text = text + "[The analysis over time can be seen by selecting one of the options bellow.]";
            }
        }
        cliTesOverview.setEditable(false);
        cliTesOverview.setText(text);
        System.out.println(text);
        /*setCliTesOverview(cliTesOverview);*/
    }

    public void enableBtns(){
        setDaysOption(checkCompPerUI1.getChosenOption());
        setBeginning(checkCompPerUI1.getAnalysisBegDate());
        setEnd(checkCompPerUI1.getAnalysisEndDate());
        if (!daysOption.equals("A Day")) {
            if (DateUtils.addDays(end,-7).before(beginning) || DateUtils.addDays(end,-7).equals(beginning)){
                resultsDaysBtn.setDisable(false);
                diagnosisDaysBtn.setDisable(false);
                resultsWeeksBtn.setDisable(true);
                diagnosisWeeksBtn.setDisable(true);
                resultsMonthsBtn.setDisable(true);
                diagnosisMonthsBtn.setDisable(true);
                resultsYearsBtn.setDisable(true);
                diagnosisYearsBtn.setDisable(true);
            } else if (DateUtils.addMonths(end,-1).before(beginning) || DateUtils.addMonths(end,-1).equals(beginning)){
                resultsDaysBtn.setDisable(false);
                diagnosisDaysBtn.setDisable(false);
                resultsWeeksBtn.setDisable(false);
                diagnosisWeeksBtn.setDisable(false);
                resultsMonthsBtn.setDisable(true);
                diagnosisMonthsBtn.setDisable(true);
                resultsYearsBtn.setDisable(true);
                diagnosisYearsBtn.setDisable(true);
            } else if (DateUtils.addYears(end,-1).before(beginning) || DateUtils.addYears(end,-1).equals(beginning)){
                resultsDaysBtn.setDisable(false);
                diagnosisDaysBtn.setDisable(false);
                resultsWeeksBtn.setDisable(false);
                diagnosisWeeksBtn.setDisable(false);
                resultsMonthsBtn.setDisable(false);
                diagnosisMonthsBtn.setDisable(false);
                resultsYearsBtn.setDisable(true);
                diagnosisYearsBtn.setDisable(true);
            } else {
                resultsDaysBtn.setDisable(false);
                diagnosisDaysBtn.setDisable(false);
                resultsWeeksBtn.setDisable(false);
                diagnosisWeeksBtn.setDisable(false);
                resultsMonthsBtn.setDisable(false);
                diagnosisMonthsBtn.setDisable(false);
                resultsYearsBtn.setDisable(false);
                diagnosisYearsBtn.setDisable(false);
            }
        }
    }

    //MUDAR A LABEL DO TITULO PARA INDICAR AS DATAS QUE ESTÃO A SER ANALIZADAS

}
