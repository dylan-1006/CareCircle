package carecircle.controllers;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import carecircle.App;
import carecircle.classes.patient;
import carecircle.data.patientData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddPatientScreenController {
        ObservableList<String> bloodTypeOptions = FXCollections.observableArrayList(
                        "Choose Blood type", "A-", "A+", "B-", "B+", "O-", "O+", "AB-", "AB+");
        ObservableList<String> genderOptions = FXCollections.observableArrayList(
                        "Male", "Female", "Others");

        @FXML
        private Button Continue;

        @FXML
        private ComboBox<String> bloodType;

        @FXML
        private TextField contactNumber;

        @FXML
        private DatePicker date;

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
        public void initialize() {
                bloodType.setItems(bloodTypeOptions);
                gender.setItems(genderOptions);
        }

        @FXML
        void addNewPatient(ActionEvent event) {

                boolean isDoubleInput = true;

                try {
                        Double.parseDouble(height.getText());
                        Double.parseDouble(weight.getText());

                } catch (NumberFormatException e) {
                        isDoubleInput = false;
                }

                try {
                        if (name.getText().equals("") || icNumber.getText().equals("") ||
                                        contactNumber.getText().equals("") ||
                                        email.getText().equals("") || date.getValue().toString().equals("")
                                        || gender.getSelectionModel().isEmpty() ||
                                        height.getText().equals("") ||
                                        weight.getText().equals("") || bloodType.getSelectionModel().isEmpty()) {
                                Alert alert = new Alert(AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText("Missing Information");
                                alert.setContentText("Please fill in all the required fields.");
                                alert.showAndWait();
                                App.setRoot("addPatientScreen");

                        } else if (isDoubleInput == false) {
                                Alert alert = new Alert(AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText("Wrong input");
                                alert.setContentText("Please make sure height and weight fields are numbers.");
                                alert.showAndWait();
                                App.setRoot("addPatientScreen");

                        } else {
                                List<patient> patientList = patientData.loadPatientDataFromDatabase();
                                int newPatientId = Integer
                                                .parseInt(patientList.get(patientList.size() - 1).getPatientID()
                                                                .substring(1))
                                                + 1;

                                String newPatientIdFormatted = String.format("P%03d", newPatientId);

                                patient newPatient = new patient(newPatientIdFormatted, name.getText(),
                                                icNumber.getText(),
                                                contactNumber.getText(),
                                                email.getText(), date.getValue().toString(),
                                                gender.getValue().toString(), " ",
                                                Double.parseDouble(height.getText()),
                                                Double.parseDouble(weight.getText()), bloodType.getValue().toString());

                                FileWriter account = new FileWriter(
                                                "src/main/resources/carecircle/assets/database/patient.txt",
                                                true);

                                PrintWriter accountWriter = new PrintWriter(account);
                                accountWriter.println(
                                                newPatient.getPatientID() + "," + newPatient.getName() + ","
                                                                + newPatient.getIc() + ","
                                                                + newPatient.getPhoneNo() + ","
                                                                + newPatient.getEmail() + ","
                                                                + newPatient.getDateOfBirth()
                                                                + "," + newPatient.getGender()
                                                                + ","
                                                                + " " + "," + newPatient.getHeight() + ","
                                                                + newPatient.getWeight() + ","
                                                                + newPatient.getBloodType());
                                accountWriter.close();
                                Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("New Patient Added!");
                                alert.setHeaderText(name.getText() + " has been added");
                                alert.showAndWait();

                                App.setRoot("patientScreenGeneral");
                        }

                } catch (Exception e) {

                }
        }

}