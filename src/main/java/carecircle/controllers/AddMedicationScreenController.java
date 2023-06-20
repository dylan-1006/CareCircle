package carecircle.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import carecircle.App;
import carecircle.classes.medication;
import carecircle.classes.doctor;
import carecircle.classes.patient;
import carecircle.data.doctorData;
import carecircle.data.medicationData;
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
    private Text backToMedicalHistoryButton;

    @FXML
    private ComboBox<String> doctorID;

    @FXML
    private TextField dosage;

    @FXML
    private TextField medicationName;

    @FXML
    private ComboBox<String> patientNameBox;

    @FXML
    private TextField quantity;

    @FXML
    public void initialize() {
        patientNameBox.setItems(fetchAvailablePatientName());
        doctorID.setItems(fetchAvailableDoctorId());
    }

    @FXML
    void addNewMedication(ActionEvent event) {
        if (patientNameBox.getSelectionModel().isEmpty() ||
                doctorID.getSelectionModel().isEmpty() || medicationName.getText().equals("")
                || quantity.getText().equals("") || dosage.getText().equals("")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Please fill in all the required fields.");
            alert.showAndWait();

        } else {
            try {
                List<medication> medicationList = medicationData.loadMedicationDataFromDatabase();
                int newMedicationID = Integer
                        .parseInt(medicationList.get(medicationList.size() - 1)
                                .getMedicationID()
                                .substring(1))
                        + 1;

                String newMedicationIdFormatted = String.format("A0%2d", newMedicationID);

                List<patient> patientList = patientData.loadPatientDataFromDatabase();

                String patientId = " ";
                for (int i = 0; i < patientList.size(); i++) {

                    if (patientList.get(i).getName().equals(patientNameBox.getSelectionModel()
                            .getSelectedItem().toString())) {

                        patientId = patientList.get(i).getPatientID();
                        break;
                    }

                }

                medication newMedication = new medication(newMedicationIdFormatted,
                        doctorID.getSelectionModel().getSelectedItem().toString(),
                        patientId,
                        medicationName.getText(),
                        quantity.getText(),
                        dosage.getText());

                FileWriter account = new FileWriter(
                        "src/main/resources/carecircle/assets/database/medication.txt", true);

                PrintWriter accountWriter = new PrintWriter(account);
                accountWriter.println(
                        newMedicationIdFormatted + "," + newMedication.getDoctorID() + ","
                                + newMedication.getPatientID() + ","
                                + newMedication.getMedicationName() + ","
                                + newMedication.getQuantity() + ","
                                + newMedication.getDosage());

                accountWriter.close();
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("New Medication Added!");
                alert.setHeaderText("New medication has been added");
                alert.showAndWait();

                App.setRoot("patientDetailsScreenMedicalHistory");

            } catch (Exception e) {

            }
        }

    }

    ObservableList<String> fetchAvailableDoctorId() {

        List<doctor> doctorList = doctorData.loadDoctorDataFromDatabase();
        ObservableList<String> doctorIdOptions = FXCollections.observableArrayList("Choose doctor ID");

        for (int i = 0; i < doctorList.size(); i++) {

            doctorIdOptions.add(doctorList.get(i).getDoctorID());

        }

        return doctorIdOptions;
    }

    ObservableList<String> fetchAvailablePatientName() {

        List<patient> patientList = patientData.loadPatientDataFromDatabase();
        ObservableList<String> patientIdOptions = FXCollections.observableArrayList("Choose patient name");

        for (int i = 0; i < patientList.size(); i++) {

            patientIdOptions.add(patientList.get(i).getName());

        }

        return patientIdOptions;
    }

    @FXML
    void backToPatientScreen(MouseEvent event) throws IOException {
        App.setRoot("patientDetailsScreenMedicalHistory");
    }

}
