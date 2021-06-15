package app.ui.gui;

import app.controller.ConsultTestByClient;
import app.controller.ImportTestController;
import app.domain.model.Client;
import app.domain.shared.Constants;
import app.mappers.dto.ClientDTO;
import app.mappers.dto.TestDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultTestsUI implements Initializable {

    @FXML
    private RadioButton tin;

    @FXML
    private RadioButton name;

    @FXML
    private ListView<ClientDTO> clientsList = new ListView<>();

    private App mainApp;

    private Stage testPageStage;

    private Menu myMenu;

    private ConsultTestByClient ctrl;

    private TestPageUI testPageUI;

    List<ClientDTO> clientsDto;

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    public void setMyMenu(Menu menu){
        this.myMenu = menu;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.ctrl = new ConsultTestByClient();
        this.clientsDto = new ArrayList<>();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TestsPage.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            testPageStage = new Stage();
            /*testPageStage.initModality(Modality.WINDOW_MODAL);*/
            testPageStage.setTitle("Client's Test");
            testPageStage.setWidth(580);
            testPageStage.setWidth(500);
            testPageStage.setResizable(false);
            testPageStage.setScene(scene);

            testPageUI = loader.getController();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void nameSelected(){
        this.tin.setSelected(false);
    }

    @FXML
    public void selectedTin(){
        this.name.setSelected(false);
    }

    @FXML
    private void searchForClients() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        if(!name.isSelected() && !tin.isSelected()){
            AlertUI.createAlert(Alert.AlertType.WARNING, "Many labs", "No order selected!", "Please select by which attribute the clients should be ordered.").show();
        }else {
            clientsDto = ctrl.getClientsDtoInOrder(getSelectedOrder());
        }
        ObservableList<ClientDTO> items = FXCollections.observableArrayList();
        items.addAll(clientsDto);
        clientsList.setItems(items);
    }

    private String getSelectedOrder(){
        System.out.println(tin.getId());
        return tin.isSelected() ? tin.getId() : name.getId();
    }
    @FXML
    private void getTestsOfClient(){
        if(clientsList.getSelectionModel().getSelectedIndex() < 0){
            AlertUI.createAlert(Alert.AlertType.WARNING, "Many labs", "No client selected!", "Please select a client to see tests historic.").show();
        }else {
            int index = clientsList.getSelectionModel().getSelectedIndex();
            List<TestDTO> tests = ctrl.getValidatedTestsOfClient(clientsDto.get(index).getTinNumber());
            testPageUI.setTestIn(tests, clientsDto.get(index).getName());
            testPageStage.showAndWait();
        }
    }

    @FXML
    private void returnAction(){
        try {
            ChemTechUI menu = (ChemTechUI) mainApp.replaceSceneContent("/fxml/ChemistryTechMenu.fxml");
            menu.setMainApp(this.mainApp);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void exitAction(ActionEvent event) {
        System.exit(    0);
    }
}
