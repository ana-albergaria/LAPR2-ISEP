package app.ui.gui;

import app.controller.CompanyPerformanceAnalysisController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckCompanyPerformanceUI2 {

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

    @FXML
    private TextArea cliTesOverview;

    @FXML
    private Button exitBtn;

    @FXML
    private LineChart<?, ?> inefChart;

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
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
