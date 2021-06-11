package app.ui.gui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;


public class LoginController extends MainUI {

    @FXML
    private ImageView imgTeam;

    @FXML
    private Button sairBtn;


    @FXML
    void exitAction(ActionEvent event) {
        System.exit(0);
    }





}
