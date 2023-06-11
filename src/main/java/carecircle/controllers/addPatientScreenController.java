package carecircle.controllers;


import java.io.FileWriter;
import java.io.PrintWriter;

import carecircle.App;
import carecircle.classes.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class addPatientScreenController {
    ObservableList<String> bloodTypeOptions = FXCollections.observableArrayList(
         "Choose Blood type","A-","A+","B-","B+","O-","O+","AB-","AB+");    

    @FXML
    private TextField Continue;

    @FXML
    private ComboBox<String> bloodType;

    @FXML
    private TextField contactNumber;

    @FXML
    private TextField date;

    @FXML
    private TextField email;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private TextField height;

    @FXML
    private TextField icNumber;

    @FXML
    private TextField name;

    @FXML
    private TextField weight;

    @FXML
    public void initialize(){
        bloodType.setItems(bloodTypeOptions);
    }

    

    @FXML
    void addNewPatient(ActionEvent event) {
        try {

            // public Patient(String patientID, String name, String ic, String phoneNo,
            // String email, String dateOfBirth,
            // String gender, String address, double height, double weight, String
            // bloodType)
            Patient newPatient = new Patient("1", name.getText(), icNumber.getText(), contactNumber.getText(),
                    email.getText(), date.getText(), gender.getValue().toString(), Double.parseDouble(height.getText()),
                    Double.parseDouble(weight.getText()), bloodType.getValue().toString());

            FileWriter account = new FileWriter("patient.txt", true);

            PrintWriter accountWriter = new PrintWriter(account);
            accountWriter.println(
                    newPatient.patientID + "," + newPatient.name + "," + newPatient.ic + "," + newPatient.phoneNo + ","
                            + newPatient.email + "," + newPatient.dateOfBirth + "," + newPatient.gender + ","
                            + newPatient.height + "," + newPatient.weight + "," + newPatient.bloodType);
            accountWriter.close(); 
            Alert alert=new Alert(AlertType.CONFIRMATION);
            alert.setTitle("New Patient Added!");
            alert.setHeaderText(name.getText());
            alert.showAndWait();

            App.setRoot("homeScreen");

        } catch (Exception e) {
            
        }
    }

}
