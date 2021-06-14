package app.ui.gui;

import app.controller.ViewClientResultsController;
import app.mappers.dto.TestDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.List;

public class ClientTestResultsUI1 /*implements Initializable*/ {

    private App mainApp;
    private ClientMenuUI clientMenuUI;
    private ViewClientResultsController controller;

    //List<TestDTO> testsWithResults = controller.getClientTestsWithOrWithoutResults(this.clientMenuUI.getMainUI().getEmail(), true);

    @FXML
    private TableView<List<String>> tableView;

    @FXML
    private TableColumn<List<String>, String> colTestType;

    @FXML
    private TableColumn<List<String>, String> colDateOfTestRegistration;

    @FXML
    private TableColumn<List<String>, Button> colTestResults;

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

}