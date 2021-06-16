package app.ui.gui;

import app.controller.SendNHSReportController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NHSReportUI implements Initializable, Menu {

    private App mainApp;

    private Menu myMenu;

    @FXML
    private Button exitBtn;

    @FXML
    private Button returnBtn;

    @FXML
    private DatePicker currentDate;

    @FXML
    private ToggleGroup tgTypeOfData;

    @FXML
    private RadioButton dayRadioBtn;

    @FXML
    private RadioButton weekRadioBtn;

    @FXML
    private TextField historicalPoints;

    @FXML
    private DatePicker initialDate;

    @FXML
    private DatePicker endDate;

    @FXML
    private ComboBox regressionCombBox;

    @FXML
    private ComboBox variableCombBox;

    @FXML
    private TextField significanceLevel;

    @FXML
    private TextField confidenceLevel;

    @FXML
    private Button sendBtn;

    private SendNHSReportController controller;

    private final String FXML_PATH = "./fxml/NHSReport.fxml";

    @Override
    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    public void setMyMenu(Menu menu){
        this.myMenu = menu;
    }

    @Override
    public String getFXML_PATH() {
        return FXML_PATH;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> regressionModelList = FXCollections.observableArrayList("Simple Linear Regression", "Multiple Linear Regression");
        regressionCombBox.setItems(regressionModelList);
        ObservableList<String> variableList = FXCollections.observableArrayList("Covid-19 Tests Realized", "Mean Age Of Clients");
        variableCombBox.setItems(variableList);
        this.controller = new SendNHSReportController();
    }

    @FXML
    void sendReportAction(ActionEvent event) {
        try {
            LocalDate currentDateValue = this.currentDate.getValue();
            Date currentDate = Date.from(currentDateValue.atStartOfDay(ZoneId.systemDefault()).toInstant());
            System.out.println("Current Date: " + currentDate);
            String typeOfData = (this.dayRadioBtn.isSelected()) ? this.dayRadioBtn.getText() : weekRadioBtn.getText();
            System.out.println("Type Of Data: " + typeOfData);
            int historicalPoints = Integer.parseInt(this.historicalPoints.getText());
            System.out.println("Historical Points: " + historicalPoints);
            LocalDate initalDateValue = this.initialDate.getValue();
            Date beginDate = Date.from(initalDateValue.atStartOfDay(ZoneId.systemDefault()).toInstant());
            System.out.println("Initial Date: " + beginDate);
            LocalDate endDateValue = this.endDate.getValue();
            Date endDate = Date.from(endDateValue.atStartOfDay(ZoneId.systemDefault()).toInstant());
            System.out.println("End Date: " + endDate);
            String chosenRegressionModelClass = this.regressionCombBox.getSelectionModel().getSelectedItem().toString();
            System.out.println("Chosen Regression Model Class: " + chosenRegressionModelClass);

            String chosenVariable = "";
            if(this.regressionCombBox.getSelectionModel().getSelectedItem().toString().equals("Simple Linear Regression"))
                chosenVariable = this.variableCombBox.getSelectionModel().getSelectedItem().toString();
            else
                throw new UnsupportedOperationException("For Multiple Linear Regression, there's no chosen independent variable!");
            double significanceLevel = Double.parseDouble(this.significanceLevel.getText());
            double confidenceLevel = Double.parseDouble(this.confidenceLevel.getText());

            //COLOCAR MAIS UMA OPÇÃO NA COMBO BOX PARA A REGRESSÃO MÚLTIPLA!!
            //FALTA COLOCAR EXCEÇÕES PARA A REGRESSÃO


            boolean success = this.controller.createNHSDailyReport(currentDate,
                    typeOfData, historicalPoints, beginDate, endDate,
                    chosenRegressionModelClass, chosenVariable, significanceLevel, confidenceLevel);

            if(success) //COLOCAR QUE O REPORT FOI ENVIADO COM SUCESSO!
                this.controller.sendNHSReport();
            else
                AlertUI.createAlert(Alert.AlertType.ERROR, mainApp.getTITLE(), "Error on data",
                        "Something went wrong! Please, try again.").show();





        } catch (NumberFormatException nfe) {
            AlertUI.createAlert(Alert.AlertType.ERROR, mainApp.getTITLE(), "Error on data",
                    "Make sure the numbers only contain digits and cannot be blank!").show();
        } catch (UnsupportedOperationException uoe) {
            AlertUI.createAlert(Alert.AlertType.ERROR, mainApp.getTITLE(), "Error on data",
                    "Multiple Linear Regression doesn't have a chosen independent variable!").show();
        } catch (NullPointerException npe) {
            AlertUI.createAlert(Alert.AlertType.ERROR, mainApp.getTITLE(), "Error on data",
                    "Every date is mandatory!").show();
        } catch(Exception e) {
            AlertUI.createAlert(Alert.AlertType.ERROR, mainApp.getTITLE(), "Error on data",
                    "Invalid data was submitted. Please verify if everything was correctly selected/typed.").show();
        }

    }

    public void exitAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void returnAction(ActionEvent actionEvent) {
        try {
            AdminMenuUI menu = (AdminMenuUI) mainApp.replaceSceneContent("/fxml/AdminMenu.fxml");
            menu.setMainApp(this.mainApp);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
