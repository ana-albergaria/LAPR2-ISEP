package app.ui.gui;

import app.controller.ImportTestController;
import app.domain.shared.utils.TestFileUtils;
import app.mappers.dto.TestFileDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;


import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImportTestsUI implements Initializable {

    @FXML
    private Text pathOfFile;

    @FXML
    private TextArea txtImportedTests;

    @FXML
    private Label importedTests;

    private App mainApp;

    private Menu myMenu;

    private File inputFile;

    private ImportTestController ctrl;

    private List<TestFileDTO> testsOfFile;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.ctrl = new ImportTestController();
        this.testsOfFile = Collections.emptyList();
    }

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    public void setMyMenu(Menu menu){
        this.myMenu = menu;
    }

    @FXML
    private void openFileChooser(){
        FileChooser flChooser = FileChooserCsvFiles.createCsvFileChooser();
        File choosenFile = flChooser.showOpenDialog(this.mainApp.getStage());

        if(choosenFile != null){
            inputFile = choosenFile;
            this.pathOfFile.setText(inputFile.toString());
        }else{
            AlertUI.createAlert(Alert.AlertType.ERROR, "Import file", "Failed to import file",
                    "None file selected!").show();
        }

    }

    @FXML
    private void importTests(){
        TestFileUtils testFileUtils = new TestFileUtils();
        testsOfFile = testFileUtils.getTestsDataToDto(inputFile.toString());
        List<TestFileDTO> addedTests = new ArrayList<>();
        for(int i=0; i < testsOfFile.size();i++){
            try{
                ctrl.importTestFromFile(testsOfFile.get(i));
                addedTests.add(testsOfFile.get(i));
            }catch (Exception e){
                System.out.println("Error in line " + (i+2) + " of csv file");
                e.printStackTrace();
            }
        }

        if(!addedTests.isEmpty()){
            this.txtImportedTests.setVisible(true);
            this.importedTests.setVisible(true);
            showImportedTests(addedTests);
        }

    }

    private void showImportedTests(List<TestFileDTO> addedTests){
        for(TestFileDTO test : addedTests){
            txtImportedTests.appendText(test.showAddedTest());
        }
    }

    @FXML
    private void returnAction(){
        try {
            LabCoordinatorUI menu = (LabCoordinatorUI) mainApp.replaceSceneContent("/fxml/LabCoordinatorMenu.fxml");
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
