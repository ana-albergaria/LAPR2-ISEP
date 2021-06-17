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

    public void setChosenOption(String choosenOption) {
        this.chosenOption = choosenOption;
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

    @FXML
    private Button exitBtn;

    @FXML
    private Button returnBtn;

    @FXML
    private DatePicker endingDate;

    @FXML
    private ChoiceBox<String> intervalOption;

    private final String[] options = {"Analyse A Day", "Analyse An Interval"};

    @FXML
    private Button analyseBtn;

    @FXML
    private DatePicker beginningDate;

    @FXML
    private DatePicker singleDate;

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
    void beginningDateAction(ActionEvent event) {

    }

    @FXML
    void endingDateAction(ActionEvent event) {

    }

    @FXML
    void singleDateAction(ActionEvent event) {

    }

    @FXML
    void analyseAction(ActionEvent event) {
        try {
            CheckCompanyPerformanceUI2 checkCompanyPerformanceUI2 = (CheckCompanyPerformanceUI2) this.mainApp.replaceSceneContent("/fxml/CheckCompanyPerformance2.fxml");
            checkCompanyPerformanceUI2.setMainApp(mainApp);
            checkCompanyPerformanceUI2.setCheckCompPerUI1(this);
            checkCompanyPerformanceUI2.getDateOrInterval();

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
    }

    public void getButtons(ActionEvent event){
        String option = intervalOption.getValue();
        if (option.equals("Analyse A Day")){
            setChosenOption(option);
            singleDate.setDisable(false);
            beginningDate.setDisable(true);
            endingDate.setDisable(true);
        }else if (option.equals("Analyse An Interval")){
            setChosenOption(option);
            singleDate.setDisable(true);
            beginningDate.setDisable(false);
            endingDate.setDisable(false);
        }
    }

    public void getDates(){ //ActionEvent??
        String option = getChosenOption();
        if (option.equals("Analyse A Day")){
            LocalDate singDate = singleDate.getValue();
            singleDateD = Date.from(singDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            setSingleDateD(singleDateD);
        }else if (option.equals("Analyse An Interval")){
            LocalDate begDate = beginningDate.getValue();
            beginningDateD = Date.from(begDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            setBeginningDateD(beginningDateD);
            LocalDate endDate = endingDate.getValue();
            endingDateD = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            setEndingDateD(endingDateD);
        }
    }

}
