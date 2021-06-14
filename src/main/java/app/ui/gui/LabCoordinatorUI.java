package app.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LabCoordinatorUI implements Initializable, Menu {

    private App mainApp;

    private final String FXML_PATH = "/fxml/LabCoordinatorMenu.fxml";


    @Override
    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public String getFXML_PATH() {
        return FXML_PATH;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void handleImportTests(){
        try {
            ImportTestsUI importTests = (ImportTestsUI) mainApp.replaceSceneContent("/fxml/ImportTests.fxml");
            importTests.setMainApp(this.mainApp);
            importTests.setMyMenu(this);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void exitAction(ActionEvent event) {
        System.exit(    0);
    }

    @FXML
    public void logoutAction(){
        app.controller.App.getInstance().doLogout();
        this.mainApp.toMainScene();
    }
}
