package carecircle.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import carecircle.App;
import carecircle.classes.analysis;
import carecircle.data.analysisData;
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

public class EditAnalysisScreen {

    @FXML
    private Button Continue;

    @FXML
    private TextField analysisId;

    @FXML
    private DatePicker date;

    @FXML
    private TextField description;

    @FXML
    private TextField doctorId;

    @FXML
    private TextField patientName;

    public void initialize() {

        setAnalysisDetails();

    }

    void setAnalysisDetails() {

        // Setting necessary details
        patientName.setText(patientData.initPatientData.getName());
        analysisId.setText(analysisData.initAnalysis.getAnalysisID());
        date.setPromptText(analysisData.initAnalysis.getDate());
        doctorId.setText(analysisData.initAnalysis.getDoctorID());
        description.setText(analysisData.initAnalysis.getDescription());
    }

    @FXML
    void backToPatientDetailAnalysisScreen(MouseEvent event) throws IOException {
        App.setRoot("patientDetailsScreenAnalysis");

    }

    @FXML
    void saveChangesAnalysis(ActionEvent event) {

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Are you sure you want to proceed?");
        confirmation.setContentText("Click OK to continue or Cancel to abort.");

        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.get() == ButtonType.OK) {

            List<analysis> analysisList = analysisData.loadAnalysisDataFromDatabase();

            for (int i = 0; i < analysisList.size(); i++) {

                if (analysisList.get(i).getAnalysisID()
                        .equals(analysisData.initAnalysis.getAnalysisID())) {

                    String editedDate;

                    // Check if date is changed
                    if (date.getValue() == null) {
                        editedDate = date.getPromptText();
                    } else {

                        editedDate = date.getValue().toString();
                    }

                    // Setting the updated details
                    analysisList.get(i).setDoctorID(doctorId.getText());
                    analysisList.get(i).setAnalysisID(analysisId.getText());
                    analysisList.get(i).setDate(editedDate);
                    analysisList.get(i).setDescription(description.getText());

                    break;
                }
            }
            //below is writing the updated and edited data into the file
            try (FileWriter account = new FileWriter(
                    "src/main/resources/carecircle/assets/database/analysis.txt",
                    false)) {
                PrintWriter accountWriter = new PrintWriter(account);

                for (int i = 0; i < analysisList.size(); i++) {

                    accountWriter.println(
                            analysisList.get(i).getAnalysisID() + ","
                                    + analysisList.get(i).getDoctorID() + ","
                                    + analysisList.get(i).getPatientID() + ","
                                    + analysisList.get(i).getDate() + "," + analysisList.get(i).getDescription());
                }
                accountWriter.close();
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Medical History Edited!");
                alert.setHeaderText("Medical history record has been edited");
                alert.showAndWait();

                App.setRoot("patientGeneralDetailsScreen");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
