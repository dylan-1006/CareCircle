package carecircle.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import carecircle.App;
import carecircle.classes.diagnosis;
import carecircle.data.diagnosisData;
import carecircle.data.patientData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class EditDiagnosisScreen {

    @FXML
    private Button Continue;

    @FXML
    private Pane D;

    @FXML
    private DatePicker date;

    @FXML
    private TextField description;

    @FXML
    private TextField diagnosisID;

    @FXML
    private TextField doctorId;

    @FXML
    private TextField patientName;

    public void initialize() {

        setDiagnosisDetails();

    }

    void setDiagnosisDetails() {

        patientName.setText(patientData.initPatientData.getName());
        diagnosisID.setText(diagnosisData.initDiagnosis.getDiagnosisID());
        date.setPromptText(diagnosisData.initDiagnosis.getDate());
        doctorId.setText(diagnosisData.initDiagnosis.getDoctorID());
        description.setText(diagnosisData.initDiagnosis.getDescription());
    }

    @FXML
    void backToPatientDetailDiagnosisScreen(MouseEvent event) throws IOException {
        App.setRoot("patientDetailsScreenDiagnosis");

    }

    @FXML
    void saveChangesDiagnosis(ActionEvent event) {

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Are you sure you want to proceed?");
        confirmation.setContentText("Click OK to continue or Cancel to abort.");

        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.get() == ButtonType.OK) {

            List<diagnosis> diagnosisList = diagnosisData.loadDiagnosisDataFromDatabase();

            for (int i = 0; i < diagnosisList.size(); i++) {

                if (diagnosisList.get(i).getDiagnosisID()
                        .equals(diagnosisData.initDiagnosis.getDiagnosisID())) {

                    String editedDate;

                    if (date.getValue() == null) {
                        editedDate = date.getPromptText();
                    } else {

                        editedDate = date.getValue().toString();
                    }

                    // Setting the updated details
                    diagnosisList.get(i).setDoctorID(doctorId.getText());
                    diagnosisList.get(i).setDiagnosisID(diagnosisID.getText());
                    diagnosisList.get(i).setDate(editedDate);
                    diagnosisList.get(i).setDescription(description.getText());

                    break;
                }

            }

            try (FileWriter account = new FileWriter(
                    "src/main/resources/carecircle/assets/database/diagnosis.txt",
                    false)) {
                PrintWriter accountWriter = new PrintWriter(account);

                for (int i = 0; i < diagnosisList.size(); i++) {

                    accountWriter.println(
                            diagnosisList.get(i).getDiagnosisID() + ","
                                    + diagnosisList.get(i).getDoctorID() + ","
                                    + diagnosisList.get(i).getPatientID() + ","
                                    + diagnosisList.get(i).getDate() + "," + diagnosisList.get(i).getDescription());
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
