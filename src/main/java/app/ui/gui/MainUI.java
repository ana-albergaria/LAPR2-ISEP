package app.ui.gui;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainUI implements Initializable {

    private App mainApp;

    /**
     * Initializes the UI class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }
}
