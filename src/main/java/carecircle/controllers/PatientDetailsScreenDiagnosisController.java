package carecircle.controllers;
import java.io.IOException;

import carecircle.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class PatientDetailsScreenDiagnosisController {

    @FXML
    private Button addDiagnosisButton;

    @FXML
    private TextField age;

    @FXML
    private Button analysisButton;

    @FXML
    private TextField bloodType;

    @FXML
    private Button deletePatientButton;

    @FXML
    private Button diagnosisButton;

    @FXML
    private Button editDiagnosis;

    @FXML
    private TextField gender;

    @FXML
    private Button generalButton;

    @FXML
    private TextField height;

    @FXML
    private Button historyButton;

    @FXML
    private TextArea notes;

    @FXML
    private TextField patientID;

    @FXML
    private TextField patientName;

    @FXML
    private TextFlow textFlowTitle;

    @FXML
    private Text titleText1;

    @FXML
    private Text titleText2;

    @FXML
    private Button treatmentButton;

    @FXML
    private TextField weight;

    @FXML
    void deletePatient(ActionEvent event) {

    }

    @FXML
    void editDiagnosisRecord(ActionEvent event) {

    }

    @FXML
    void switchToAddDiagnosisScreen(ActionEvent event) {

    }

    @FXML
    void switchToAnalysisSection(ActionEvent event) throws IOException{
        App.setRoot("patientDetailsScreenAnalysis");
    }

    @FXML
    void switchToGeneralSection(ActionEvent event) throws IOException{
        App.setRoot("patientGeneralDetailsScreen");
    }

    @FXML
    void switchToHistorySection(ActionEvent event) throws IOException {
        App.setRoot("patientDetailsScreenMedicalHistory");
    }

    @FXML
    void switchToTreatmentSection(ActionEvent event) throws IOException{
        App.setRoot("patientDetailsScreenTreatment");
    }

}
