package app.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Labeled;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuUI implements Initializable {

    private MainUI mainUI;

    public MainUI getMainUI(){
        return this.mainUI;
    }

    @FXML
    private Button logoutBtn;

    @FXML
    private Button updateClientDataBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button viewTestResultBtn;

    /**
     * Initializes the UI class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    void logoutAction(ActionEvent event) {
        this.mainUI.getMainApp().toMainScene();
    }

    @FXML
    void viewTestResultAction(ActionEvent event) {

    }

    @FXML
    void updateClientDataAction(ActionEvent event) {

    }

    @FXML
    void exitAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Aplication");
        alert.setHeaderText("Action confirmation.");
        alert.setContentText("Do you really wish to exit the application?");

        ((Labeled) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
        ((Labeled) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");

        if (alert.showAndWait().get() == ButtonType.OK) {
            ((Stage) exitBtn.getScene().getWindow()).close();
        }
    }

}