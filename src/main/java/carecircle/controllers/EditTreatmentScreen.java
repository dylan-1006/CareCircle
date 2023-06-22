package carecircle.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import carecircle.App;
import carecircle.classes.treatment;
import carecircle.data.treatmentData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EditTreatmentScreen {

    @FXML
    private Button Continue;

    @FXML
    private DatePicker date;

    @FXML
    private TextField description;

    @FXML
    private TextField doctorId;

    @FXML
    private TextField treatmentId;

    

    public void initialize() {

        setDiagnosisDetails();

    }

    void setDiagnosisDetails() {

        treatmentId.setText(treatmentData.initTreatment.getTreatmentID());
        doctorId.setText(treatmentData.initTreatment.getDoctorID());
        date.setPromptText(treatmentData.initTreatment.getDate());
        description.setText(treatmentData.initTreatment.getDescription());
    }

    @FXML
    void backToPatientDetailTreatmentScreen(MouseEvent event) throws IOException {
        App.setRoot("patientDetailsScreenTreatment");

    }

@FXML
void saveChangesTreatment(ActionEvent event) {

    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
    confirmation.setTitle("Confirmation");
    confirmation.setHeaderText("Are you sure you want to proceed?");
    confirmation.setContentText("Click OK to continue or Cancel to abort.");

    Optional<ButtonType> result = confirmation.showAndWait();

    if (result.get() == ButtonType.OK) {

        List<treatment> treatmentList = treatmentData.loadTreatmentDataFromDatabase();

        for (int i = 0; i < treatmentList.size(); i++) {

            if (treatmentList.get(i).getTreatmentID()
                    .equals(treatmentData.initTreatment.getTreatmentID())) {

                // Setting the updated details
                treatmentList.get(i).setDoctorID(doctorId.getText());
                treatmentList.get(i).setTreatmentID(treatmentId.getText());
                treatmentList.get(i).setDate(date.getPromptText());
                treatmentList.get(i).setDescription(description.getText());

                break;
            }
        }

        try (FileWriter account = new FileWriter(
                "src/main/resources/carecircle/assets/database/treatment.txt",
                false)) {
            PrintWriter accountWriter = new PrintWriter(account);

            for (int i = 0; i < treatmentList.size(); i++) {

                accountWriter.println(
                        treatmentList.get(i).getTreatmentID() + ","
                                + treatmentList.get(i).getDoctorID() + ","
                                + treatmentList.get(i).getPatientID() + ","
                                + treatmentList.get(i).getDate());
            }
            accountWriter.close();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Treatment Edited!");
            alert.setHeaderText("Treatment record has been edited");
            alert.showAndWait();

            App.setRoot("patientGeneralDetailsScreen");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
}
