package app.ui.gui;

import app.controller.CompanyPerformanceAnalysisController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
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
    private CompanyPerformanceAnalysisController controller;

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

        alert.setTitle("Application");
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
            singleDate.setDisable(false);
            beginningDate.setDisable(true);
            endingDate.setDisable(true);
        }else if (option.equals("Analyse An Interval")){
            singleDate.setDisable(true);
            beginningDate.setDisable(false);
            endingDate.setDisable(false);
        }else{
            singleDate.setDisable(true);
            beginningDate.setDisable(true);
            endingDate.setDisable(true);
        }
    }


}
