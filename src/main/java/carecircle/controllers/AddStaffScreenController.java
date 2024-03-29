package carecircle.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import carecircle.App;
import carecircle.classes.doctor;
import carecircle.classes.nurse;
import carecircle.classes.patient;
import carecircle.data.doctorData;
import carecircle.data.nurseData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

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
    private Text backToStaffButton;

    @FXML
    void initialize() {

        // Set combox box selection items
        gender.setItems(genderOptions);
        typeOfStaff.setItems(staffOptions);
    }

    @FXML
    void backToStaffScreen(MouseEvent event) throws IOException {
        // Return to previous screen
        App.setRoot("medicalStaffScreenGeneral");

    }

    @FXML
    void createNewStaff(ActionEvent event) {

        // Check if type of staff is doctor or nurse
        if (typeOfStaff.getValue().toString().equals("Doctor")) {
            try {
                List<doctor> doctorList = doctorData.loadDoctorDataFromDatabase();
                // Generate new id based on last id in the database
                int newDoctorId = Integer.parseInt(doctorList.get(doctorList.size() - 1).getDoctorID()
                        .substring(1))
                        + 1;
                String newDoctorIdFormatted = String.format("D%02d", newDoctorId);

                // Get filled in details
                doctor newDoctor = new doctor(newDoctorIdFormatted, name.getText(), contactNumber.getText(),
                        email.getText(), date.getValue().toString(),
                        gender.getValue().toString(), " ");
                FileWriter account = new FileWriter(
                        "src/main/resources/carecircle/assets/database/doctor.txt",
                        true);
                PrintWriter accountWriter = new PrintWriter(account);
                // Append new data to the .txt file
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

                App.setRoot("medicalStaffScreenGeneral");

            }

            catch (IOException e) {
                e.printStackTrace();
            }
        } else if (typeOfStaff.getValue().toString().equals("Nurse")) {
            try {
                List<nurse> nurseList = nurseData.loadNurseDataFromDatabase();
                // Generate new id based on last id in the database
                int newNurseId = Integer.parseInt(nurseList.get(nurseList.size() - 1).getNurseID()
                        .substring(1))
                        + 1;
                String newNurseIdFormatted = String.format("N%02d", newNurseId);
                // Get filled in details
                nurse newNurse = new nurse(newNurseIdFormatted, name.getText(), contactNumber.getText(),
                        email.getText(),
                        date.getValue().toString(),
                        gender.getValue().toString());
                FileWriter account = new FileWriter(
                        "src/main/resources/carecircle/assets/database/nurse.txt",
                        true);
                PrintWriter accountWriter = new PrintWriter(account);
                // Append new data to the .txt file
                accountWriter.println(
                        newNurse.getNurseID() + "," + newNurse.getName() + ","
                                + newNurse.getPhoneNo() + ","
                                + newNurse.getEmail() + ","
                                + newNurse.getDateOfBirth()
                                + "," + newNurse.getGender());
                accountWriter.close();
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("New Nurse Added!");
                alert.setHeaderText(name.getText() + " has been added");
                alert.showAndWait();

                App.setRoot("medicalStaffScreenGeneral");

            }

            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
