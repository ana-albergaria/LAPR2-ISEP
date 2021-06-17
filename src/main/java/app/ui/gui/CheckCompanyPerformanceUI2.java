package app.ui.gui;

import app.controller.CompanyPerformanceAnalysisController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckCompanyPerformanceUI2 implements Initializable {

    private App mainApp;

    public App getMainApp() {
        return this.mainApp;
    }

    public void setMainApp(App mainApp){
        this.mainApp=mainApp;
    }

    private CheckCompanyPerformanceUI1 checkCompPerUI1;

    public void setCheckCompPerUI1(CheckCompanyPerformanceUI1 checkCompPerUI1) {
        this.checkCompPerUI1 = checkCompPerUI1;
    }

    private CompanyPerformanceAnalysisController controller;

    private int[] chartValues;

    private String option;

    public void setOption(String option) {
        this.option = option;
    }

    private Date singleDateD;
    private Date beginningDateD;
    private Date endingDateD;

    public void setSingleDateD(Date singleDateD) {
        this.singleDateD = singleDateD;
    }

    public void setBeginningDateD(Date beginningDateD) {
        this.beginningDateD = beginningDateD;
    }

    public void setEndingDateD(Date endingDateD) {
        this.endingDateD = endingDateD;
    }

    private Date analysisBegDate;
    private Date analysisEndDate;

    public void setAnalysisBegDate(Date analysisBegDate) {
        this.analysisBegDate = analysisBegDate;
    }

    public void setAnalysisEndDate(Date analysisEndDate) {
        this.analysisEndDate = analysisEndDate;
    }

    private String chosenAlg;

    public void setChosenAlg(String chosenAlg) {
        this.chosenAlg = chosenAlg;
    }

    public void setCliTesOverview(TextArea cliTesOverview) {
        this.cliTesOverview = cliTesOverview;
    }

    @FXML
    private TextArea cliTesOverview;

    @FXML
    private Button exitBtn;

    @FXML
    private LineChart<String, Integer> inefChart;

    @FXML
    private Button returnBtn;

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
            checkCompanyPerformanceUI1.getDates();
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.controller=new CompanyPerformanceAnalysisController();
    }

    public void getDateOrInterval(){
        option=checkCompPerUI1.getChosenOption();
        if (option.equals("A Day")){
            singleDateD=checkCompPerUI1.getSingleDateD();
            setSingleDateD(singleDateD);
            analysisBegDate = new Date(singleDateD.getYear(), singleDateD.getMonth(), singleDateD.getDate(), 8,0,0);
            setAnalysisBegDate(analysisBegDate);
            analysisEndDate = new Date(singleDateD.getYear(), singleDateD.getMonth(), singleDateD.getDate(), 19,59,59);
            setAnalysisEndDate(analysisEndDate);
        }else if (option.equals("An Interval")){
            beginningDateD=checkCompPerUI1.getBeginningDateD();
            setBeginningDateD(beginningDateD);
            endingDateD=checkCompPerUI1.getEndingDateD();
            setEndingDateD(endingDateD);
            analysisBegDate = new Date(beginningDateD.getYear(), beginningDateD.getMonth(), beginningDateD.getDate(), 8,0,0);
            setAnalysisBegDate(analysisBegDate);
            analysisEndDate = new Date(endingDateD.getYear(), endingDateD.getMonth(), endingDateD.getDate(), 19,59,59);
            setAnalysisEndDate(analysisEndDate);
        }
    }

    public void analyseCompany() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        setChosenAlg(checkCompPerUI1.getChosenAlg());
        ArrayList<Date> days = controller.getDays(analysisBegDate,analysisEndDate);
        Date[] limits= controller.findWorstSubIntWithChosenAlgorithm(days,chosenAlg);
        String text = "Interval When The Company Was Less Effective In Responding" + "\n ↪ From: " + limits[0].toString() + "\n ↪ To: " + limits[1].toString();
        text = text + "\nTotal Number Of Clients In The System" + "\n ↪ " + controller.getClientsInfoPerInterval(days);
        text = text + "\nTotal Number of Processed Tests In The System" + "\n ↪ " + controller.getNumTestsProcessedInterval(days);
        cliTesOverview.setText(text);
        setCliTesOverview(cliTesOverview);
    }

    //MUDAR A LABEL DO TITULO PARA INDICAR AS DATAS QUE ESTÃO A SER ANALIZADAS

}
