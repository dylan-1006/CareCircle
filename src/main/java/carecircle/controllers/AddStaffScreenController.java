package carecircle.controllers;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import carecircle.App;
import carecircle.classes.doctor;
import carecircle.classes.nurse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AddStaffScreenController {
    ObservableList<String> genderOptions = FXCollections.observableArrayList(
        "Male", "Female", "Others");
    ObservableList<String> staffOptions = FXCollections.observableArrayList(
         "Doctor", "Nurse", "Others");
    

    @FXML
    private TextField contactNumber;

    @FXML
    private DatePicker date;

    @FXML
    private TextField email;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private TextField icNumber;

    @FXML
    private TextField name;

    @FXML
    private ComboBox<String> typeOfStaff;

    @FXML
    void initialize(ActionEvent event) {
        gender.setItems(genderOptions);
        typeOfStaff.setItems(staffOptions);
    }

    @FXML
    void createNewStaff(ActionEvent event) {
        if(typeOfStaff.getValue().toString().equals("Doctor")){
            try {
                /*public doctor(String doctorID, String name, String phoneNo, String email, String dateOfBirth, String gender,
            String specialization) */
                doctor newDoctor = new doctor("1", name.getText(), contactNumber.getText(), email.getText(), date.getValue().toString(),
                gender.getValue().toString(), "");
                FileWriter account = new FileWriter(
                "src/main/resources/carecircle/assets/database/doctor.txt",
                true);
                PrintWriter accountWriter = new PrintWriter(account);
                accountWriter.println(
                                                newDoctor.getDoctorID() + "," + newDoctor.getName() + ","
                                                                + newDoctor.getPhoneNo() + ","
                                                                + newDoctor.getEmail() + ","
                                                                + newDoctor.getDateOfBirth()
                                                                + "," + newDoctor.getGender()
                                                                + ","
                                                                + newDoctor.getSpecialization());
                accountWriter.close();
                Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("New Doctor Added!");
                                alert.setHeaderText(name.getText() + " has been added");
                                alert.showAndWait();

                                App.setRoot("homeScreen");

            } 
            
            
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(typeOfStaff.getValue().toString().equals("Nurse")){
            try {
                /* public nurse(String nurseID, String name, String phoneNo, String email, String dateOfBirth, String gender) */
                nurse newNurse = new nurse("1", name.getText(), contactNumber.getText(), email.getText(), date.getValue().toString(),
                gender.getValue().toString());
                FileWriter account = new FileWriter(
                "src/main/resources/carecircle/assets/database/nurse.txt",
                true);
                PrintWriter accountWriter = new PrintWriter(account);
                accountWriter.println(
                                                newNurse.getNurseID() + "," + newNurse.getName() + ","
                                                                + newNurse.getPhoneNo() + ","
                                                                + newNurse.getEmail() + ","
                                                                + newNurse.getDateOfBirth()
                                                                + "," + newNurse.getGender()
                );
                accountWriter.close();
                Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("New Nurse Added!");
                                alert.setHeaderText(name.getText() + " has been added");
                                alert.showAndWait();

                                App.setRoot("homeScreen");

            } 
            
            
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
