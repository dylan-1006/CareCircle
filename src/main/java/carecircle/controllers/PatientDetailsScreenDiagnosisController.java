package carecircle.controllers;

import java.io.IOException;
import java.time.Year;
import java.util.Optional;

import carecircle.App;
import carecircle.data.diagnosisData;
import carecircle.data.patientData;
import carecircle.data.diagnosisData;
import carecircle.tableModels.patientDiagnosisTableModel;
import carecircle.tableModels.patientMedicalHistoryTableModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private Button editDiagnosisButton;

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
    private Pane showAvailabilityOfDiagnosisRecord;

    @FXML
    private Pane patientDetailsSidePane;

    @FXML
    private TableView<patientDiagnosisTableModel> patientDetailsDiagnosisTable;

    public void initialize() {

        setSideBarPatientDetails();
        setDiagnosisTable();
    }

    void setSideBarPatientDetails() {

        // Calculating the patient age
        int year = Year.now().getValue();
        int patientAge = year - Integer.parseInt(patientData.initPatientData.getDateOfBirth().substring(0, 4));

        // Setting the patient details
        patientName.setText(patientData.initPatientData.getName());
        patientID.setText(patientData.initPatientData.getPatientID());
        gender.setText(patientData.initPatientData.getGender());
        age.setText(Integer.toString(patientAge));
        height.setText(Double.toString(patientData.initPatientData.getHeight()) + "cm");
        weight.setText(Double.toString(patientData.initPatientData.getWeight()) + "kg");
        bloodType.setText(patientData.initPatientData.getBloodType());

    }

    void setDiagnosisTable() {

        ObservableList<patientDiagnosisTableModel> diagnosisDataList = patientDiagnosisTableModel
                .convertSelectedPatientDiagnosisDataToModel();

        if (diagnosisDataList.isEmpty()) {

            // Setting table & buttons to be non-visible
            patientDetailsDiagnosisTable.setVisible(false);

            addDiagnosisButton.setVisible(true);
            editDiagnosisButton.setVisible(false);


        } else {

            showAvailabilityOfDiagnosisRecord.setVisible(false);

            TableColumn<patientDiagnosisTableModel, String> diagnosisIDColumn = new TableColumn<>("Diagnosis ID");
            TableColumn<patientDiagnosisTableModel, String> doctorIDColumn = new TableColumn<>("Doctor ID");
            TableColumn<patientDiagnosisTableModel, String> patientIDColumn = new TableColumn<>("Patient ID");
            TableColumn<patientDiagnosisTableModel, String> dateColumn = new TableColumn<>("Date");
            TableColumn<patientDiagnosisTableModel, String> descriptionColumn = new TableColumn<>("Description");

            patientDetailsDiagnosisTable.getColumns().addAll(diagnosisIDColumn, doctorIDColumn, patientIDColumn,
                    dateColumn,
                    descriptionColumn);

            diagnosisIDColumn.setCellValueFactory(new PropertyValueFactory<>("diagnosisID"));
            doctorIDColumn.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
            patientIDColumn.setCellValueFactory(new PropertyValueFactory<>("patientID"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

            patientDetailsDiagnosisTable.setItems(diagnosisDataList);
        }

    }

    @FXML
    void deleteThisPatient(ActionEvent event) throws IOException {

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Are you sure you want to proceed?");
        confirmation.setContentText("Click OK to continue or Cancel to abort.");

        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.get() == ButtonType.OK) {

            patientData.deletePatient(patientData.initPatientData.getPatientID());
            App.setRoot("patientScreenGeneral");
        } else {

            App.setRoot("patientGeneralDetailsScreen");
        }

    }

    @FXML
    void editDiagnosisRecord(ActionEvent event) throws IOException {
        patientDiagnosisTableModel selectedDiagnosis = patientDetailsDiagnosisTable.getSelectionModel()
                .getSelectedItem();

        if (selectedDiagnosis == null) {

            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("Please select a record before proceeding");
            error.showAndWait();

        } else {

            diagnosisData.initDiagnosis.setDiagnosisID(selectedDiagnosis.getDiagnosisID());
            diagnosisData.initDiagnosis.setDoctorID(selectedDiagnosis.getDoctorID());
            diagnosisData.initDiagnosis.setPatientID(selectedDiagnosis.getPatientID());
            diagnosisData.initDiagnosis.setDate(selectedDiagnosis.getDate());
            diagnosisData.initDiagnosis.setDescription(selectedDiagnosis.getDescription());

            App.setRoot("editDiagnosisScreen");
        }
    }

    @FXML
    void switchToAddDiagnosisScreen(ActionEvent event) throws IOException {
        App.setRoot("addDiagnosisScreen");

    }

    @FXML
    void switchToAnalysisSection(ActionEvent event) throws IOException {
        App.setRoot("patientDetailsScreenAnalysis");
    }

    @FXML
    void switchToGeneralSection(ActionEvent event) throws IOException {
        App.setRoot("patientGeneralDetailsScreen");
    }

    @FXML
    void switchToHistorySection(ActionEvent event) throws IOException {
        App.setRoot("patientDetailsScreenMedicalHistory");
    }

    @FXML
    void switchToTreatmentSection(ActionEvent event) throws IOException {
        App.setRoot("patientDetailsScreenTreatment");
    }

    @FXML
    void switchToPatient(MouseEvent event) throws IOException {

        App.setRoot("patientScreenGeneral");

    }

    @FXML
    void deleteDiagnosisRecord(ActionEvent event) throws IOException {

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Are you sure you want to proceed?");
        confirmation.setContentText("Click OK to continue or Cancel to abort.");

        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.get() == ButtonType.OK) {

            patientDiagnosisTableModel selectedDiagnosis = patientDetailsDiagnosisTable.getSelectionModel()
                    .getSelectedItem();
            patientDetailsDiagnosisTable.getItems().remove(selectedDiagnosis);

            String DiagnosisId = selectedDiagnosis.getDiagnosisID();

            diagnosisData.deleteDiagnosis(DiagnosisId);

            App.setRoot("patientGeneralDetailsScreen");
        } else {

            App.setRoot("patientGeneralDetailsScreenMedicalHistory");
        }
    }

}
