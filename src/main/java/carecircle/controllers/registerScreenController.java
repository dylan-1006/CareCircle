package carecircle.controllers;

import java.io.IOException;

import carecircle.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class registerScreenController {

    @FXML
    private Button signInButton;

    @FXML
    void switchToLoginScreen(ActionEvent event) throws IOException {
        App.setRoot("loginScreen");
    }

}
