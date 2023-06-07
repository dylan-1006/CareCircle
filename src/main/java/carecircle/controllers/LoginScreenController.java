package carecircle.controllers;

import java.io.IOException;

import carecircle.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoginScreenController {

    @FXML
    private Button continueButton;

    @FXML
    private Button signUpButton;

    @FXML
    void switchToRegisterScreen(ActionEvent event) throws IOException{
        App.setRoot("registerScreen");
    }

}
