package app.ui.gui;

import app.controller.ViewClientResultsController;
import app.mappers.dto.TestDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
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

    private List<TestDTO> clientTests;

    public void setClientMenuUI(ClientMenuUI clientMenuUI) {
        this.clientMenuUI = clientMenuUI;
    }

    @FXML
    private ListView<String> mListView;

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
        setClientMenuUI(clientMenuUI);
        this.controller=new ViewClientResultsController();
        clientTests= controller.getClientTestsWithResults(this.clientMenuUI.getMainUI().getEmail());
        populateData(clientTests);
    }

    private void populateData(List<TestDTO> testsWithResults){
        ArrayList<String> stringsForListview = new ArrayList<>();
        String toAdd;
        for (int i = 0; i < testsWithResults.size(); i++) {
            toAdd=testsWithResults.get(i).getTestTypeDescription() + "|" + testsWithResults.get(i).getStringDateOfTestRegistration();
            stringsForListview.add(toAdd);
        }
        //ObservableList<String> items =FXCollections.observableArrayList(stringsForListview);
        //mListView.setItems(items);
        for (String string : stringsForListview)
            mListView.getItems().add(string);
    }

}