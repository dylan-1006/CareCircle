package carecircle.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import carecircle.App;
import carecircle.classes.medicine;
import carecircle.classes.doctor;
import carecircle.classes.patient;
import carecircle.data.doctorData;
import carecircle.data.medicineData;
import carecircle.data.patientData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddMedicationScreenController {

    @FXML
    private Button Continue;

    @FXML
    private Text backToMedicationButton;

    @FXML
    private ComboBox<String> doctorID;

    @FXML
    private TextField dosage;

    @FXML
    private TextField medicationName;

    @FXML
    private TextField patientNameBox;
    @FXML
    private TextField quantity;

    @FXML
    public void initialize() {

        // Set combox box selection items
        patientNameBox.setText(patientData.initPatientData.getName());
        doctorID.setItems(fetchAvailableDoctorId());
    }

    @FXML
    void addNewMedication(ActionEvent event) {

        boolean isDoubleInput = true;

        // Check if data is inputted is the correct data format
        try {
            Integer.parseInt(quantity.getText());
            Double.parseDouble(dosage.getText());

        } catch (NumberFormatException e) {
            isDoubleInput = false;
        }

        // Make sure all required details are filled in
        if (doctorID.getSelectionModel().isEmpty() || medicationName.getText().equals("")
                || quantity.getText().equals("") || dosage.getText().equals("")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Please fill in all the required fields.");
            alert.showAndWait();

            // Make sure data inputted is correct data type
        } else if (isDoubleInput == false) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong input");
            alert.setContentText("Please make sure quantity and dosage fields are numbers.");
            alert.showAndWait();
            quantity.setText("");
            dosage.setText("");

        }

        else {
            try {
                List<medicine> medicineList = medicineData.loadMedicineDataFromDatabase();
                // Generate new id based on last id in the database
                int newMedicationID = Integer
                        .parseInt(medicineList.get(medicineList.size() - 1)
                                .getMedicineID()
                                .substring(1))
                        + 1;

                String newMedicationIdFormatted = String.format("M0%2d", newMedicationID);

                List<patient> patientList = patientData.loadPatientDataFromDatabase();

                String patientId = " ";
                for (int i = 0; i < patientList.size(); i++) {

                    // Get patient id based on patient name
                    if (patientList.get(i).getName().equals(patientNameBox.getText())) {

                        patientId = patientList.get(i).getPatientID();
                        System.out.println("Found patient id");
                        break;

                    }

                }

                // Get filled in details
                medicine newMedicine = new medicine(newMedicationIdFormatted,
                        doctorID.getSelectionModel().getSelectedItem().toString(),
                        patientId,
                        medicationName.getText(),
                        Integer.parseInt(quantity.getText()),
                        Double.parseDouble(dosage.getText()));

                FileWriter account = new FileWriter(
                        "src/main/resources/carecircle/assets/database/medicine.txt", true);

                PrintWriter accountWriter = new PrintWriter(account);
                // Append new data to the .txt file
                accountWriter.println(
                        newMedicationIdFormatted + "," + newMedicine.getDoctorId() + ","
                                + newMedicine.getPatientId() + ","
                                + newMedicine.getMedicineName() + ","
                                + newMedicine.getQuantity() + ","
                                + newMedicine.getDosage());

                accountWriter.close();
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("New Medication Added!");
                alert.setHeaderText("New medication has been added");
                alert.showAndWait();

                App.setRoot("patientDetailsScreenMedicalHistory");

            } catch (Exception e) {
                System.out.println(e);

            }
        }

    }

    ObservableList<String> fetchAvailableDoctorId() {
        // Get data from database
        List<doctor> doctorList = doctorData.loadDoctorDataFromDatabase();
        ObservableList<String> doctorIdOptions = FXCollections.observableArrayList("Choose doctor ID");

        for (int i = 0; i < doctorList.size(); i++) {

            doctorIdOptions.add(doctorList.get(i).getDoctorID());

        }

        return doctorIdOptions;
    }

    ObservableList<String> fetchAvailablePatientName() {
        // Get data from database
        List<patient> patientList = patientData.loadPatientDataFromDatabase();
        ObservableList<String> patientIdOptions = FXCollections.observableArrayList("Choose patient name");

        for (int i = 0; i < patientList.size(); i++) {

            patientIdOptions.add(patientList.get(i).getName());

        }

        return patientIdOptions;
    }

    @FXML
    void backToPatientScreen(MouseEvent event) throws IOException {
        // Return to previous screen
        App.setRoot("patientDetailsScreenMedicalHistory");
    }

}
