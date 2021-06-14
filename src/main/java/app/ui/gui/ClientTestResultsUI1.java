package app.ui.gui;

import app.controller.ViewClientResultsController;
import app.domain.model.TestType;
import app.mappers.dto.TestDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ClientTestResultsUI1 implements Initializable {

    private App mainApp;
    private ClientMenuUI clientMenuUI;
    private ViewClientResultsController controller;

    List<TestDTO> testsWithResults = controller.getClientTestsWithResults(this.clientMenuUI.getMainUI().getEmail());

    @FXML
    private TableView<TestDTO> tableView;

    @FXML
    private TableColumn<TestDTO, String> colTestType;

    @FXML
    private TableColumn<TestDTO, String> colDateOfTestRegistration;

    @FXML
    private TableColumn<TestDTO, Button> colTestResults;

    @FXML
    private Button exitBtn;

    @FXML
    private Button returnBtn;

    @FXML
    void exitAction(ActionEvent event) {
    }

    @FXML
    void returnAction(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {



    }



}