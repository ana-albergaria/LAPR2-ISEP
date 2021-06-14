package app.ui.gui;

import app.mappers.dto.TestDTO;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class ClientTestsInfoForTableview {

    //private List<ClientTestsInfoForTableview> clientTestsInfoForTableview = new ArrayList<>();

    private String testTypeDescription;

    private String stringDateOfTestRegistration;

    private Button button;

    public ClientTestsInfoForTableview(){
        this(null,null);
    }

    public ClientTestsInfoForTableview(String testTypeDescription, String stringDateOfTestRegistration) {
        this.testTypeDescription = testTypeDescription;
        this.stringDateOfTestRegistration = stringDateOfTestRegistration;
        this.button = new Button("View Test Results");
    }

    public String getTestTypeDescription() {
        return testTypeDescription;
    }

    public String getStringDateOfTestRegistration() {
        return stringDateOfTestRegistration;
    }

    public Button getButton() {
        return button;
    }

    public void setTestTypeDescription(String testTypeDescription) {
        this.testTypeDescription = testTypeDescription;
    }

    public void setStringDateOfTestRegistration(String stringDateOfTestRegistration) {
        this.stringDateOfTestRegistration = stringDateOfTestRegistration;
    }

    public void setButton(Button button) {
        this.button = button;
    }

}
