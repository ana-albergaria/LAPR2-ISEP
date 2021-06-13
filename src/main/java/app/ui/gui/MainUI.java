package app.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainUI implements Initializable {

    private App mainApp;

    @FXML
    private ImageView imgTeam;

    @FXML
    private Button sairBtn;

    /**
     * Initializes the UI class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public App getMainApp(){
        return this.mainApp;
    }

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void exitAction(ActionEvent event) {
        System.exit(0);
    }
}
