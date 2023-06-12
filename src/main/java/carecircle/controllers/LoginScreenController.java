package carecircle.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import carecircle.App;
import carecircle.classes.user;
import carecircle.data.userData;
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
        try {
            boolean found = false;
            FileReader info = new FileReader("user.txt");
            BufferedReader infoR = new BufferedReader(info);
            String line = "";
            while ((line = infoR.readLine()) != null) {
                StringTokenizer infoToken = new StringTokenizer(line, ",");
                String Name = infoToken.nextToken();
                String Username = infoToken.nextToken();
                String Password = infoToken.nextToken();
                if (username.getText().equals(Username) && password.getText().equals(Password)) {
                    found = true;
                    infoR.close();
                    System.out.println(userData.initUserData.name);
                    userData.initUserData.name = Name;
                    System.out.println(userData.initUserData.name);
                    //HomeScreenController.setUsername(userData.initUserData.name);
                    // HomeScreenController.homeScreenUserName.setText(userData.initUserData.name);
                    App.setRoot("homeScreen");

                }
            }
            if (found == false) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Credentials incorrect");
                alert.setHeaderText("PLease enter your credentials correctly");
                alert.showAndWait();
                infoR.close();
            }

        } catch (Exception e) {

        }

    }

    @FXML
    void switchToRegisterScreen(ActionEvent event) throws IOException {
        App.setRoot("registerScreen");
    }

}
