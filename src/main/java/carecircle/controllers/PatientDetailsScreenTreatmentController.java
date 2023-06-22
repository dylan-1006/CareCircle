package carecircle.controllers;

import java.io.IOException;
import java.time.Year;
import java.util.Optional;

import carecircle.App;
import carecircle.data.patientData;
import carecircle.tableModels.patientDiagnosisTableModel;
import carecircle.tableModels.patientTreatmentTableModel;
import carecircle.data.treatmentData;
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

public class PatientDetailsScreenTreatmentController {

    @FXML
    private Button addTreatmentButton;

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
    private Button editTreatmentButton;

    @FXML
    private TextField gender;

    @FXML
    private Button generalButton;

    @FXML
    private Pane showAvailabilityOfTreatmentRecord;

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
    private TextField weight;

    @FXML
    private Pane patientDetailsSidePane;

    @FXML
    private TableView<patientTreatmentTableModel> patientDetailsTreatmentTable;

    public void initialize() {

        setSideBarPatientDetails();
        setTreatmentTable();
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

    void setTreatmentTable() {

        ObservableList<patientTreatmentTableModel> TreatmentDataList = patientTreatmentTableModel
                .convertSelectedPatientTreatmentDataToModel();

        if (TreatmentDataList.isEmpty()) {

            // Setting table & buttons to be non-visible
            patientDetailsTreatmentTable.setVisible(false);

            addTreatmentButton.setVisible(true);
            editTreatmentButton.setVisible(false);
            // deleteTreatmentButton.setVisible(false);

        } else {

            showAvailabilityOfTreatmentRecord.setVisible(false);

            TableColumn<patientTreatmentTableModel, String> TreatmentIDColumn = new TableColumn<>("Treatment ID");
            TableColumn<patientTreatmentTableModel, String> doctorIDColumn = new TableColumn<>("Doctor ID");
            TableColumn<patientTreatmentTableModel, String> patientIDColumn = new TableColumn<>("Patient ID");
            TableColumn<patientTreatmentTableModel, String> dateColumn = new TableColumn<>("Date");
            TableColumn<patientTreatmentTableModel, String> descriptionColumn = new TableColumn<>("Description");

            patientDetailsTreatmentTable.getColumns().addAll(TreatmentIDColumn, doctorIDColumn, patientIDColumn,
                    dateColumn,
                    descriptionColumn);

            TreatmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("TreatmentID"));
            doctorIDColumn.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
            patientIDColumn.setCellValueFactory(new PropertyValueFactory<>("patientID"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

            patientDetailsTreatmentTable.setItems(TreatmentDataList);
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
    void editTreatmentRecord(ActionEvent event) throws IOException {

        patientTreatmentTableModel selectedTreatment = patientDetailsTreatmentTable.getSelectionModel()
                .getSelectedItem();

        if (selectedTreatment == null) {

            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("Please select a record before proceeding");
            error.showAndWait();

        } else {

            treatmentData.initTreatment.setTreatmentID(selectedTreatment.getTreatmentID());
            treatmentData.initTreatment.setPatientID(selectedTreatment.getPatientID());
            treatmentData.initTreatment.setDoctorID(selectedTreatment.getDoctorID());
            treatmentData.initTreatment.setDate(selectedTreatment.getDate());
            treatmentData.initTreatment.setDescription(selectedTreatment.getDescription());
            App.setRoot("editTreatmentScreen");
        }

    }

    @FXML
    void switchToAddTreatmentScreen(ActionEvent event) throws IOException {
        App.setRoot("addTreatmentScreen");
    }

    @FXML
    void switchToAnalysisSection(ActionEvent event) throws IOException {
        App.setRoot("patientDetailsScreenAnalysis");
    }

    @FXML
    void switchToDiagnosisSection(ActionEvent event) throws IOException {
        App.setRoot("patientDetailsScreenDiagnosis");
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
    void switchToPatient(MouseEvent event) throws IOException {

        App.setRoot("patientScreenGeneral");

    }

    @FXML
    void deleteTreatmentRecord(ActionEvent event) throws IOException {

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Are you sure you want to proceed?");
        confirmation.setContentText("Click OK to continue or Cancel to abort.");

        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.get() == ButtonType.OK) {

            patientTreatmentTableModel selectedTreatment = patientDetailsTreatmentTable.getSelectionModel()
                    .getSelectedItem();
            patientDetailsTreatmentTable.getItems().remove(selectedTreatment);

            String treatmentId = selectedTreatment.getTreatmentID();

            treatmentData.deleteTreatment(treatmentId);

            App.setRoot("patientGeneralDetailsScreen");
        } else {

            App.setRoot("patientGeneralDetailsScreenMedicalHistory");
        }
    }

}
