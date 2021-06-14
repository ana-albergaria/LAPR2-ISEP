package app.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminMenu implements Initializable, Menu {

    private App mainApp;

    private final String FXML_PATH = "/fxml/AdminMenu.fxml";

    public String getFXML_PATH() {
        return FXML_PATH;
    }

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    public App getMainApp() {
        return this.mainApp;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
