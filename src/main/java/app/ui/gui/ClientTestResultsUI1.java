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
    private List<String> testParametersName;
    private List<Double> testParametersResult;
    private List<String> testParametersMetric;
    private List<Double> testParametersMinRef;
    private List<Double> testParametersMaxRef;

    private ArrayList<String> stringsForListview = new ArrayList<>();

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
    private TextArea resultText;

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
            clientMenuUI.setMainUI(this.clientMenuUI.getMainUI());
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.controller=new ViewClientResultsController();
    }

    public void getClientTests(){
        clientTests= controller.getClientTestsWithResults(this.clientMenuUI.getMainUI().getEmail());
        populateData(clientTests);
        handleItemClicks(clientTests);
    }

    private void populateData(List<TestDTO> testsWithResults){
        String toAdd;
        for (int i = 0; i < testsWithResults.size(); i++) {
            toAdd=testsWithResults.get(i).getTestTypeDescription() + "\n ↪ " + testsWithResults.get(i).getStringDateOfTestRegistration();
            stringsForListview.add(toAdd);
        }
        for (String string : stringsForListview) {
            mListView.getItems().add(string);
        }
    }

    public void handleItemClicks(List<TestDTO> testsWithResults){
        mListView.setOnMouseClicked(event -> {
            String selectedItem = mListView.getSelectionModel().getSelectedItem();
            int num = -1;
            for (int i = 0; i < stringsForListview.size(); i++) {
                if (stringsForListview.get(i).equals(selectedItem)){
                    num=i;
                }
            }
            if (num!=-1) {
                testParametersName = testsWithResults.get(num).getTestParametersName();
                testParametersMetric = testsWithResults.get(num).getTestParametersMetric();
                testParametersResult = testsWithResults.get(num).getTestParametersResult();
                testParametersMinRef = testsWithResults.get(num).getTestParametersReferenceValueMin();
                testParametersMaxRef = testsWithResults.get(num).getTestParametersReferenceValueMax();
                String text = "";
                for (int i = 0; i < testParametersName.size(); i++) {
                    text = text + testParametersName.get(i) + " (" + testParametersMetric.get(i) + ")" + "\n ↪ Result: " +
                            testParametersResult.get(i) + "\n ↪ Normal Values: " + testParametersMinRef.get(i) + " - " +
                            testParametersMaxRef.get(i) + "\n ↪ Report: " + testsWithResults.get(num).getReport() + "\n";
                }
                resultText.setText(text);
            } else {
                resultText.setText("There are no results to show.");
            }
        });
    }



}