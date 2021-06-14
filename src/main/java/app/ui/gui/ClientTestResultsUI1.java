package app.ui.gui;

import app.controller.ViewClientResultsController;
import app.mappers.dto.TestDTO;
import app.mappers.dto.TestTypeDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ClientTestResultsUI1 implements Initializable {

    private App mainApp;
    private ClientMenuUI clientMenuUI;
    private ViewClientResultsController controller;

    List<TestDTO> testWithResults = controller.getClientTestsWithOrWithoutResults(this.clientMenuUI.getMainUI().getEmail(), true);

    @FXML
    private TableView<TestDTO> tableView;

    @FXML
    private TableColumn<TestDTO, TestTypeDTO> colTestType;

    @FXML
    private TableColumn<TestDTO, Date> colDateOfTestRegistration;

    @FXML
    private TableColumn<TestDTO, Button> colTestResults;

    @FXML
    private Button exitBtn;

    @FXML
    private Button returnBtn;

    /**
     * Initializes the UI class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    void exitAction(ActionEvent event) {
    }

    @FXML
    void returnAction(ActionEvent event) {
    }

    private void initTable(){
        initCols();
    }

    private void initCols(){
        colTestType.setCellValueFactory(new PropertyValueFactory<>("Test Type"));
        colDateOfTestRegistration.setCellValueFactory(new PropertyValueFactory<>("Date of Registration"));
        colTestResults.setCellValueFactory(new PropertyValueFactory<>("Test Results"));
    }

    private void editableCols(){

    }

}