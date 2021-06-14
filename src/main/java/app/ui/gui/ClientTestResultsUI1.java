package app.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ClientTestResultsUI1 {

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> colDateOfTestRegistration;

    @FXML
    private TableColumn<?, ?> colTestResults;

    @FXML
    private TableColumn<?, ?> colTestType;

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