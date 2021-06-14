package app.ui.gui;

import app.mappers.dto.TestDTO;
import app.mappers.dto.TestFileDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TestPageUI implements Initializable {

    @FXML
    private TextArea clientsTestArea;

    @FXML
    private Text clientsName;


    public void setTestIn(List<TestDTO> listOfTests, String clientsName){
        for(TestDTO test : listOfTests){
            clientsTestArea.appendText(test.showAllButReport());
        }
        this.clientsName.setText(clientsName);
    }

    @FXML
    private void returnAction(){

    }

    @FXML
    void exitAction(ActionEvent event) {
        System.exit(    0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
