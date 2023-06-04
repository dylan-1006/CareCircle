package carecircle.controllers;

import java.io.IOException;

import carecircle.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TestController {

    @FXML
    private Button PrimaryButton;

    @FXML
    void goToPrimary(ActionEvent event) throws IOException {
        App.setRoot("mainframe");

    }

}
