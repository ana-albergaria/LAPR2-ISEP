package app.ui.gui;

import app.controller.CompanyPerformanceAnalysisController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckCompanyPerformanceUI1 implements Initializable {

    private App mainApp;

    public App getMainApp(){
        return this.mainApp;
    }

    public void setMainApp(App mainApp){
        this.mainApp=mainApp;
    }

    private LabCoordinatorUI lcUI;

    public void setLcUI(LabCoordinatorUI lcUI) {
        this.lcUI = lcUI;
    }

    public LabCoordinatorUI getLcUI() {
        return lcUI;
    }

    private CompanyPerformanceAnalysisController controller;

    private String chosenOption;

    public void setChosenOption(String chosenOption) {
        this.chosenOption = chosenOption;
    }

    public String getChosenOption() {
        return chosenOption;
    }

    private Date singleDateD;

    public void setSingleDateD(Date singleDateD) {
        this.singleDateD = singleDateD;
    }

    public Date getSingleDateD() {
        return singleDateD;
    }

    private Date beginningDateD;

    public void setBeginningDateD(Date beginningDateD) {
        this.beginningDateD = beginningDateD;
    }

    public Date getBeginningDateD() {
        return beginningDateD;
    }

    private Date endingDateD;

    public void setEndingDateD(Date endingDateD) {
        this.endingDateD = endingDateD;
    }

    public Date getEndingDateD() {
        return endingDateD;
    }

    private final String[] options = {"A Day", "An Interval"};
    private final String[] optionsAlg = {"Benchmark Algorithm", "Brute-Force Algorithm"};

    private String chosenAlg;

    public String getChosenAlg() {
        return chosenAlg;
    }

    public void setChosenAlg(String chosenAlg) {
        this.chosenAlg = chosenAlg;
    }

    @FXML
    private Button exitBtn;

    @FXML
    private Button returnBtn;

    @FXML
    private DatePicker endingDate;

    @FXML
    private ChoiceBox<String> intervalOption;

    @FXML
    private Button analyseBtn;

    @FXML
    private DatePicker beginningDate;

    @FXML
    private DatePicker singleDate;

    @FXML
    private ChoiceBox<String> algorithmOption;

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
            LabCoordinatorUI labCoordinatorUI = (LabCoordinatorUI) this.mainApp.replaceSceneContent("/fxml/LabCoordinatorMenu.fxml");
            labCoordinatorUI.setMainApp(mainApp);
            labCoordinatorUI.setMainUI(this.lcUI.getMainUI());
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void analyseAction(ActionEvent event) {
        try {
            CheckCompanyPerformanceUI2 checkCompanyPerformanceUI2 = (CheckCompanyPerformanceUI2) this.mainApp.replaceSceneContent("/fxml/CheckCompanyPerformance2.fxml");
            checkCompanyPerformanceUI2.setMainApp(this.mainApp);
            checkCompanyPerformanceUI2.setCheckCompPerUI1(this);
            checkCompanyPerformanceUI2.setController(this.controller);
            checkCompanyPerformanceUI2.getDateOrInterval();
            checkCompanyPerformanceUI2.analyseCompany();
            checkCompanyPerformanceUI2.populateListView();
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.controller=new CompanyPerformanceAnalysisController();
    }

    public void addOptions(){
        intervalOption.getItems().addAll(options);
        intervalOption.setOnAction(this::getButtons);
        algorithmOption.getItems().addAll(optionsAlg);
    }

    public void getButtons(ActionEvent event){
        String option = intervalOption.getValue();
        setChosenOption(option);
        if (chosenOption.equals("A Day")){
            singleDate.setDisable(false);
            beginningDate.setDisable(true);
            endingDate.setDisable(true);
        } else if(chosenOption.equals("An Interval")){
            singleDate.setDisable(true);
            beginningDate.setDisable(false);
            endingDate.setDisable(false);
        }
    }

    public void getDates(){ //ActionEvent??
        if (chosenOption.equals("A Day")){
            LocalDate singDate = singleDate.getValue();
            singleDateD = Date.from(singDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            setSingleDateD(singleDateD);
        }else if (chosenOption.equals("An Interval")){
            LocalDate begDate = beginningDate.getValue();
            beginningDateD = Date.from(begDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            setBeginningDateD(beginningDateD);
            LocalDate endDate = endingDate.getValue();
            endingDateD = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            setEndingDateD(endingDateD);
        }
    }

    //É PRECISO CRIAR ALERTAS SE FOREM ESCOLHIDAS DATAS NÃO PERMITIDAS E NÃO PERMITIR AVANÇAR
    //FAZER DISABLE AO ANALYSE BUTTON ATÉ SER POSSÍVEL

}
