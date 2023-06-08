package carecircle.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import carecircle.App;
import carecircle.classes.user;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

public class registerScreenController {

 
    @FXML
    private Button continueButton;

    @FXML
    private PasswordField password;

    @FXML
    private Button signInButton;

    @FXML
    private TextField username;


    @FXML
    void register(ActionEvent event) throws IOException {
        

        try {
            if (!(username.getText()).equals("") && !(password.getText().equals(""))) {
                

                TextInputDialog nameInput = new TextInputDialog();
                nameInput.setTitle("What should we call you");
                nameInput.setHeaderText("Enter your name");
                nameInput.setContentText("Name");
                Optional<String> input=nameInput.showAndWait();
                String name=input.get();
                user newUser = new user(name,username.getText(),password.getText());
                
                FileWriter account = new FileWriter("user.txt", true);

                PrintWriter accountWriter = new PrintWriter(account);
                accountWriter.println(newUser.name+","+newUser.username+","+newUser.password);
                accountWriter.close();
                Alert alert=new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Sign up successful!");
                alert.setHeaderText("Welcome "+name);
                alert.showAndWait();
                App.setRoot("homeScreen");

            } 
            else {
                Alert alert=new Alert(AlertType.ERROR);
                alert.setTitle("Sign up failed!");
                alert.setHeaderText("Please fill out all the required information!");
                alert.showAndWait();
            }

        } catch (Exception e) {
        }

    }


    @FXML
    void switchToLoginScreen(ActionEvent event) throws IOException {
        App.setRoot("loginScreen");
    }
    

}