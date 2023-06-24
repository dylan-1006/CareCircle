package carecircle.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import carecircle.App;
import carecircle.classes.medicalHistory;
import carecircle.classes.patient;
import carecircle.classes.diagnosis;
import carecircle.classes.treatment;
import carecircle.classes.procedure;
import carecircle.data.medicalHistoryData;
import carecircle.data.patientData;
import carecircle.data.procedureData;
import carecircle.data.diagnosisData;
import carecircle.data.treatmentData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class AddMedicalHistoryScreenController {

    @FXML
    private Button Continue;

    @FXML
    private Text backToMedicalHistoryButton;

    @FXML
    private TextField allergies;

    @FXML
    private TextField description;

    @FXML
    private ComboBox<String> diagnosisID;

    @FXML
    private TextField pastMedication;

    @FXML
    private ComboBox<String> patientNameBox;

    @FXML
    private ComboBox<String> procedureID;

    @FXML
    private ComboBox<String> treatmentID;

    @FXML
    public void initialize() {
        patientNameBox.setItems(fetchAvailablePatientName());
        diagnosisID.setItems(fetchAvailableDiagnosisID());
        treatmentID.setItems(fetchAvailableTreatmentID());
        procedureID.setItems(fetchAvailableProcedureID());
    }

    @FXML
    void addNewMedicalHistory(ActionEvent event) {
        if (patientNameBox.getSelectionModel().isEmpty() ||
                diagnosisID.getSelectionModel().isEmpty() || treatmentID.getSelectionModel().isEmpty()
                || procedureID.getSelectionModel().isEmpty() || procedureID.getSelectionModel().isEmpty()
                || allergies.getText().equals("") || pastMedication.getText().equals("")
                || description.getText().equals("")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Please fill in all the required fields.");
            alert.showAndWait();

        } else {
            try {
                List<medicalHistory> medicalHistoryList = medicalHistoryData.loadMedicalHistoryDataFromDatabase();
                int newMedicalHistoryID = Integer
                        .parseInt(medicalHistoryList.get(medicalHistoryList.size() - 1)
                                .getMedicalHistoryId()
                                .substring(1))
                        + 1;

                String newMedicalHistoryIdFormatted = String.format("MH0%2d", newMedicalHistoryID);

                List<patient> patientList = patientData.loadPatientDataFromDatabase();

                String patientId = " ";
                for (int i = 0; i < patientList.size(); i++) {

                    if (patientList.get(i).getName().equals(patientNameBox.getSelectionModel()
                            .getSelectedItem().toString())) {

                        patientId = patientList.get(i).getPatientID();
                        break;
                    }

                }

                medicalHistory newMedicalHistory = new medicalHistory(newMedicalHistoryIdFormatted,
                        patientId,
                        diagnosisID.getSelectionModel().toString(),
                        treatmentID.getSelectionModel().toString(),
                        procedureID.getSelectionModel().toString(),
                        allergies.getText(),
                        pastMedication.getText(),
                        description.getText());

                FileWriter account = new FileWriter(
                        "src/main/resources/carecircle/assets/database/medicalHistory.txt", true);

                PrintWriter accountWriter = new PrintWriter(account);
                accountWriter.println(
                        newMedicalHistoryIdFormatted + ","
                                + newMedicalHistory.getPatientId() + ","
                                + newMedicalHistory.getDiagnosisId() + ","
                                + newMedicalHistory.getTreatmentId() + ","
                                + newMedicalHistory.getProcedureId() + ","
                                + newMedicalHistory.getAllergies() + ","
                                + newMedicalHistory.getPastMedicationId()
                                + newMedicalHistory.getDescription());

                accountWriter.close();
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("New Medical History Added!");
                alert.setHeaderText("New medical history has been added");
                alert.showAndWait();

                App.setRoot("patientDetailsScreenMedicalHistory");

            } catch (Exception e) {

            }
        }

    }

    ObservableList<String> fetchAvailablePatientName() {

        List<patient> patientList = patientData.loadPatientDataFromDatabase();
        ObservableList<String> patientIdOptions = FXCollections.observableArrayList("Choose patient name");

        for (int i = 0; i < patientList.size(); i++) {

            patientIdOptions.add(patientList.get(i).getName());

        }
        return patientIdOptions;
    }

    ObservableList<String> fetchAvailableDiagnosisID() {

        List<diagnosis> diagnosisList = diagnosisData.loadDiagnosisDataFromDatabase();
        ObservableList<String> diagnosisIdOptions = FXCollections.observableArrayList("Choose diagnosis ID");

        for (int i = 0; i < diagnosisList.size(); i++) {

            diagnosisIdOptions.add(diagnosisList.get(i).getDiagnosisID());

        }
        return diagnosisIdOptions;
    }

    ObservableList<String> fetchAvailableTreatmentID() {

        List<treatment> treatmentList = treatmentData.loadTreatmentDataFromDatabase();
        ObservableList<String> treatmentIdOptions = FXCollections.observableArrayList("Choose treatment ID");

        for (int i = 0; i < treatmentList.size(); i++) {

            treatmentIdOptions.add(treatmentList.get(i).getTreatmentID());

        }
        return treatmentIdOptions;
    }

    ObservableList<String> fetchAvailableProcedureID() {

        List<procedure> procedureList = procedureData.loadProcedureDataFromDatabase();
        ObservableList<String> procedureIdOptions = FXCollections.observableArrayList("Choose procedure ID");

        for (int i = 0; i < procedureList.size(); i++) {

            procedureIdOptions.add(procedureList.get(i).getProcedureId());

        }
        return procedureIdOptions;
    }

    @FXML
    void backToMedicalHistoryScreen(MouseEvent event) throws IOException {
        App.setRoot("patientDetailsScreenMedicalHistory");
    }
}
