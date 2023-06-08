package carecircle.controllers;

import java.io.IOException;

import carecircle.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LoginScreenController {

    @FXML
    private Button continueButton;

    @FXML
    private PasswordField password;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField username;

    @FXML
    void logIn(ActionEvent event) throws IOException {
        if ((username.getText().equals("admin")) && ((password.getText().equals("admin")))) {
          App.setRoot("homeScreen");
        } 
        else {
            Alert alert= new Alert(AlertType.ERROR);
            alert.setTitle("Credentials incorrect");
            alert.setHeaderText("PLease enter your credentials correctly");
            alert.showAndWait();
        }
    }

    @FXML
    void switchToRegisterScreen(ActionEvent event) throws IOException {
        App.setRoot("registerScreen");        
    }

}

