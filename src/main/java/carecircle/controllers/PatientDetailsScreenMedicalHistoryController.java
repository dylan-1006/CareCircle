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

public class PatientDetailsScreenMedicalHistoryController {

    @FXML
    private Button addMedicalHistoryButton;

    @FXML
    private TextField age;

    @FXML
    private TextArea allergies;

    @FXML
    private Button analysisButton;

    @FXML
    private TextField bloodType;

    @FXML
    private Button diagnosisButton;

    @FXML
    private Button editMedicalHistoryButton;

    @FXML
    private TextField gender;

    @FXML
    private Button generalButton;

    @FXML
    private TextField height;

    @FXML
    private Button historyButton;

    @FXML
    private TextArea pastMedications;

    @FXML
    private TextField patientID;

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
    void editMedicalHistoryRecord(ActionEvent event) {

    }

    @FXML
    void switchToAddMedicalHistoryScreen(ActionEvent event) throws IOException{
        
    }

    @FXML
    void switchToAnalysisSection(ActionEvent event) throws IOException{
        App.setRoot("patientDetailsScreenAnalysis");
    }

    @FXML
    void switchToDiagnosisSection(ActionEvent event) throws IOException{
        App.setRoot("patientDetailsScreenDiagnosis");
    }

    @FXML
    void switchToGeneralSection(ActionEvent event) throws IOException{
        App.setRoot("patientGeneralDetailsScreen");
    }

    @FXML
    void switchToTreatmentSection(ActionEvent event) throws IOException{
        App.setRoot("patientDetailsScreenTreatment");
    }

}
