package carecircle.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import carecircle.App;
import carecircle.classes.doctor;
import carecircle.classes.patient;
import carecircle.classes.diagnosis;
import carecircle.data.doctorData;
import carecircle.data.patientData;
import carecircle.data.diagnosisData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddDiagnosisScreenController {

        @FXML
        private Button Continue;

        @FXML
        private Text backToDiagnosisButton;

        @FXML
        private DatePicker date;

        @FXML
        private TextField description;

        @FXML
        private ComboBox<String> doctorID;

        @FXML
        private ComboBox<String> patientNameBox;

        @FXML
        public void initialize() {
                patientNameBox.setItems(fetchAvailablePatientName());
                doctorID.setItems(fetchAvailableDoctorId());
        }

        @FXML
        void addNewDiagnosis(ActionEvent event) {
                if (patientNameBox.getSelectionModel().isEmpty() ||
                                doctorID.getSelectionModel().isEmpty() || date.getValue().toString().equals("")
                                || description.getText().equals("event")) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Missing Information");
                        alert.setContentText("Please fill in all the required fields.");
                        alert.showAndWait();

                } else {
                        try {
                                List<diagnosis> diagnosisList = diagnosisData.loadDiagnosisDataFromDatabase();
                                int newDiagnosisID = Integer
                                                .parseInt(diagnosisList.get(diagnosisList.size() - 1)
                                                                .getDiagnosisID()
                                                                .substring(1))
                                                + 1;

                                String newDiagnosisIdFormatted = String.format("D0%2d", newDiagnosisID);

                                List<patient> patientList = patientData.loadPatientDataFromDatabase();

                                String patientId = " ";
                                for (int i = 0; i < patientList.size(); i++) {

                                        if (patientList.get(i).getName().equals(patientNameBox.getSelectionModel()
                                                        .getSelectedItem().toString())) {

                                                patientId = patientList.get(i).getPatientID();
                                                break;
                                        }

                                }

                                diagnosis newDiagnosis = new diagnosis(newDiagnosisIdFormatted,
                                                doctorID.getSelectionModel().getSelectedItem().toString(),
                                                patientId,
                                                date.getValue().toString(),
                                                description.getText());

                                FileWriter account = new FileWriter(
                                                "src/main/resources/carecircle/assets/database/diagnosis.txt", true);

                                PrintWriter accountWriter = new PrintWriter(account);
                                accountWriter.println(
                                                newDiagnosisIdFormatted + "," + newDiagnosis.getDoctorID() + ","
                                                                + newDiagnosis.getPatientID() + ","
                                                                + newDiagnosis.getDate() + ","
                                                                + newDiagnosis.getDescription());

                                accountWriter.close();
                                Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("New Diagnosis Added!");
                                alert.setHeaderText("New diagnosis has been added");
                                alert.showAndWait();

                                App.setRoot("patientDetailsScreenDiagnosis");

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
        void backToTreamentScreen(MouseEvent event) throws IOException {
                App.setRoot("patientDetailsScreenDiagnosis");
        }

}
