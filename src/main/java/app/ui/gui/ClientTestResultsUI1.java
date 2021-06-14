package app.ui.gui;

import app.controller.ViewClientResultsController;
import app.domain.model.TestType;
import app.mappers.dto.TestDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientTestResultsUI1 implements Initializable {

    private App mainApp;

    private final String FXML_PATH = "/fxml/ClientTestResults1.fxml";

    public App getMainApp() {
        return this.mainApp;
    }

    public void setMainApp(App mainApp){
        this.mainApp=mainApp;
    }

    private ClientMenuUI clientMenuUI;
    private ViewClientResultsController controller;

    @FXML
    private TableView<ClientTestsInfoForTableview> tableView;

    @FXML
    private TableColumn<ClientTestsInfoForTableview, String> colTestType;

    @FXML
    private TableColumn<ClientTestsInfoForTableview, String> colDateOfTestRegistration;

    @FXML
    private TableColumn<ClientTestsInfoForTableview, Button> colTestResults;

    @FXML
    private Button exitBtn;

    @FXML
    private Button returnBtn;

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
            ClientMenuUI clientMenuUI = (ClientMenuUI) this.mainApp.replaceSceneContent("/fxml/ClientMenu.fxml");
            clientMenuUI.setMainApp(mainApp);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*List<TestDTO> testsWithResults = controller.getClientTestsWithResults(this.clientMenuUI.getMainUI().getEmail());
        updateClientTestsInfoForTableview(testsWithResults, clientTestsInfoForTableview);
        colTestType.setCellValueFactory(new PropertyValueFactory<ClientTestsInfoForTableview,String>("testType"));
        colDateOfTestRegistration.setCellValueFactory(new PropertyValueFactory<ClientTestsInfoForTableview,String>("stringDateOfTestRegistration"));
        tableView.setItems(clientTestsInfoForTableview);*/
    }

    /*private ObservableList<ClientTestsInfoForTableview> clientTestsInfoForTableview = FXCollections.observableArrayList();

    public void updateClientTestsInfoForTableview(List<TestDTO> testsToAdd, List<ClientTestsInfoForTableview> clientTestsInfoForTableview){
        String description, date;
        ClientTestsInfoForTableview toAdd;
        for (int i = 0; i < testsToAdd.size(); i++) {
            description=testsToAdd.get(i).getTestTypeDescription();
            date=testsToAdd.get(i).getStringDateOfTestRegistration();
            toAdd = new ClientTestsInfoForTableview(description,date);
            clientTestsInfoForTableview.add(toAdd);
        }
    }*/

}