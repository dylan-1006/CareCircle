package carecircle.controllers;

import java.io.IOException;

import carecircle.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SecondaryController {

    @FXML
    private Button secondaryButton;

    @FXML
    private Button testButton;

    @FXML
    private Button nothingButton;

    @FXML
    void switchToPrimary(ActionEvent event) throws IOException {
        App.setRoot("mainframe");

    }

    @FXML
    void switchToTest(ActionEvent event) throws IOException {
        App.setRoot("test1");

    }

    @FXML
    void switchToNothing(ActionEvent event) throws IOException {
        System.out.println("Nothing has been switched to");

    }

}
