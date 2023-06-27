package carecircle.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import carecircle.App;
import carecircle.classes.medicalHistory;
import carecircle.classes.patient;
import carecircle.data.medicalHistoryData;
import carecircle.data.patientData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EditMedicalHistoryScreen {

    @FXML
    private TextField procedure;

    @FXML
    private TextField medicalHistoryDescription;

    @FXML
    private Button continueButton;

    @FXML
    private TextField allergies;

    @FXML
    private TextField diagnosis;

    @FXML
    private TextField name;

    @FXML
    private TextField treatment;

    @FXML
    private TextField pastMedication;

    public void initialize() {

        setMedicalHistoryDetails();

    }

    void setMedicalHistoryDetails() {

        name.setText(patientData.initPatientData.getName());
        treatment.setText(medicalHistoryData.initMedicalHistoryData.getTreatmentId());
        allergies.setText(medicalHistoryData.initMedicalHistoryData.getAllergies());
        pastMedication.setText(medicalHistoryData.initMedicalHistoryData.getPastMedicationId());
        diagnosis.setText(medicalHistoryData.initMedicalHistoryData.getDiagnosisId());
        procedure.setText(medicalHistoryData.initMedicalHistoryData.getProcedureId());
        medicalHistoryDescription.setText(medicalHistoryData.initMedicalHistoryData.getDescription());
    }

    @FXML
    void backToAppointmentScreen(MouseEvent event) throws IOException {
        App.setRoot("PatientDetailsScreenMedicalHistory");

    }

    @FXML
    void saveChangesMedicalHistory(ActionEvent event) {

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Are you sure you want to proceed?");
        confirmation.setContentText("Click OK to continue or Cancel to abort.");

        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.get() == ButtonType.OK) {

            List<medicalHistory> medicalHistoryList = medicalHistoryData.loadMedicalHistoryDataFromDatabase();

            for (int i = 0; i < medicalHistoryList.size(); i++) {

                if (medicalHistoryList.get(i).getMedicalHistoryId()
                        .equals(medicalHistoryData.initMedicalHistoryData.getMedicalHistoryId())) {

                    // Setting the updated details
                    medicalHistoryList.get(i).setTreatmentId(treatment.getText());
                    medicalHistoryList.get(i).setAllergies(allergies.getText());
                    medicalHistoryList.get(i).setPastMedicationId(pastMedication.getText());
                    medicalHistoryList.get(i).setDiagnosisId(diagnosis.getText());
                    medicalHistoryList.get(i).setProcedureId(procedure.getText());
                    medicalHistoryList.get(i).setDescription(medicalHistoryDescription.getText());

                    break;
                }

            }
            //below is writing updated data into the file
            try (FileWriter account = new FileWriter(
                    "src/main/resources/carecircle/assets/database/medicalHistory.txt",
                    false)) {
                PrintWriter accountWriter = new PrintWriter(account);

                for (int i = 0; i < medicalHistoryList.size(); i++) {

                    accountWriter.println(
                            medicalHistoryList.get(i).getMedicalHistoryId() + ","
                                    + medicalHistoryList.get(i).getPatientId() + ","
                                    + medicalHistoryList.get(i).getDiagnosisId() + ","
                                    + medicalHistoryList.get(i).getTreatmentId() + ","
                                    + medicalHistoryList.get(i).getProcedureId() + ","
                                    + medicalHistoryList.get(i).getDescription()
                                    + "," + medicalHistoryList.get(i).getAllergies()
                                    + ","
                                    + medicalHistoryList.get(i).getPastMedicationId());
                }
                accountWriter.close();
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Medical History Edited!");
                alert.setHeaderText("Medical history record has been edited");
                alert.showAndWait();

                App.setRoot("patientGeneralDetailsScreen");

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

}
