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
    private RadioButton orderByTin;

    @FXML
    private RadioButton orderByName;

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
    private void searchForClients() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        if(orderByTin.isSelected()){
            clientsDto = ctrl.getClientsDtoInOrder(Constants.TIN_COMPARATOR_ID);
        }else{
            clientsDto = ctrl.getClientsDtoInOrder("name");
        }
        ObservableList<ClientDTO> items = FXCollections.observableArrayList();
        for(ClientDTO client : clientsDto){
            items.add(client);
        }
        clientsList.setItems(items);
    }

    @FXML
    private void getTestsOfClient(){
        int index = clientsList.getSelectionModel().getSelectedIndex();
        List<TestDTO> tests = ctrl.getValidatedTestsOfClient(clientsDto.get(index).getTinNumber());
        testPageUI.setTestIn(tests, clientsDto.get(index).getName());
        testPageStage.showAndWait();
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
