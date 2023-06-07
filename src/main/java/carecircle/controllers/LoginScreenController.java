package carecircle.controllers;

import java.io.IOException;

import carecircle.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class LoginScreenController {

    @FXML
    private Button continueButton;

    @FXML
    private Button signUpButton;

    @FXML
    void continueToPrevious(ActionEvent event) {

    }

    @FXML
    void switchToRegister(MouseEvent event) {

    }

    @FXML
    void switchToRegisterScreen(ActionEvent event) throws IOException{
        App.setRoot("registerScreen");
    }

}
