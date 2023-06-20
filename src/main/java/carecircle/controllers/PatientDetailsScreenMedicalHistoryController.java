package carecircle.controllers;

import java.io.IOException;
import java.time.Year;
import java.util.Optional;

import carecircle.App;
import carecircle.classes.medicalHistory;
import carecircle.data.medicalHistoryData;
import carecircle.data.medicineData;
import carecircle.data.patientData;
import carecircle.tableModels.patientMedicalHistoryTableModel;
import carecircle.tableModels.patientMedicineTableModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class PatientDetailsScreenMedicalHistoryController {

    @FXML
    private Button addMedicalHistoryButton;

    @FXML
    private Button addMedicineButton;

    @FXML
    private TextField age;

    @FXML
    private Button analysisButton;

    @FXML
    private ImageView backButton;

    @FXML
    private TextField bloodType;

    @FXML
    private Button deletePatientButton;

    @FXML
    private Button diagnosisButton;

    @FXML
    private Button editMedicalHistoryButton;

    @FXML
    private Button editMedicineButton;

    @FXML
    private TextField gender;

    @FXML
    private Button generalButton;

    @FXML
    private TextField height;

    @FXML
    private Button historyButton;

    @FXML
    private Pane patientDetailsSidePane;

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
    private Button deleteMedicalHistoryButton;

    @FXML
    private Button deleteMedicineButton;

    @FXML
    private Pane showAvailablilityofMedicalRecord;

    @FXML
    private Pane showAvailablilityofMedicationsRecord;

    @FXML
    private TableView<patientMedicineTableModel> pastMedicationTable;

    @FXML
    private TableView<patientMedicalHistoryTableModel> medicalHistoryTable;

    public void initialize() {

        setSideBarPatientDetails();
        setMedicalHistoryTable();
        setPastMedicationTable();
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

    void setMedicalHistoryTable() {

        ObservableList<patientMedicalHistoryTableModel> medicalHistoryDataList = patientMedicalHistoryTableModel
                .convertSelectedPatientMedicalHistoryDataToModel();

        if (medicalHistoryDataList.isEmpty()) {

            // Setting table & buttons to be non-visible
            medicalHistoryTable.setVisible(false);

            addMedicalHistoryButton.setVisible(true);
            editMedicalHistoryButton.setVisible(false);
            deleteMedicalHistoryButton.setVisible(false);

        }

        else {

            showAvailablilityofMedicalRecord.setVisible(false);

            TableColumn<patientMedicalHistoryTableModel, String> diagnosisIdColumn = new TableColumn<>("Diagnosis ID");
            TableColumn<patientMedicalHistoryTableModel, String> treatmentIdColumn = new TableColumn<>("Treatment ID");
            TableColumn<patientMedicalHistoryTableModel, String> procedureIdColumn = new TableColumn<>("Procedure ID");
            TableColumn<patientMedicalHistoryTableModel, String> descriptionColumn = new TableColumn<>("Description");
            TableColumn<patientMedicalHistoryTableModel, String> allergiesColumn = new TableColumn<>("Allergies");
            TableColumn<patientMedicalHistoryTableModel, String> pastMedicationIdColumn = new TableColumn<>(
                    "Past Medication ID");

            medicalHistoryTable.getColumns().addAll(diagnosisIdColumn, treatmentIdColumn,
                    procedureIdColumn,
                    descriptionColumn, allergiesColumn, pastMedicationIdColumn);

            diagnosisIdColumn.setCellValueFactory(new PropertyValueFactory<>("diagnosisId"));
            treatmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("treatmentId"));
            procedureIdColumn.setCellValueFactory(new PropertyValueFactory<>("procedureId"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            allergiesColumn.setCellValueFactory(new PropertyValueFactory<>("allergies"));
            pastMedicationIdColumn.setCellValueFactory(new PropertyValueFactory<>("pastMedicationId"));

            medicalHistoryTable.setItems(medicalHistoryDataList);
        }

    }

    @FXML
    void deleteMedicalHistoryRecord(ActionEvent event) throws IOException {

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Are you sure you want to proceed?");
        confirmation.setContentText("Click OK to continue or Cancel to abort.");

        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.get() == ButtonType.OK) {

            patientMedicalHistoryTableModel selectedMedicalHistory = medicalHistoryTable.getSelectionModel()
                    .getSelectedItem();
            medicalHistoryTable.getItems().remove(selectedMedicalHistory);

            String medicalHistoryId = selectedMedicalHistory.getMedicalHistoryId();

            medicalHistoryData.deleteMedicalHistory(medicalHistoryId);

            App.setRoot("patientGeneralDetailsScreen");
        } else {

            App.setRoot("patientGeneralDetailsScreenMedicalHistory");
        }
    }

    void setPastMedicationTable() {
        ObservableList<patientMedicineTableModel> pastMedicationDataList = patientMedicineTableModel
                .convertSelectedPatientMedicineDataToModel();

        if (pastMedicationDataList.isEmpty()) {

            // Setting table & buttons to be non-visible
            pastMedicationTable.setVisible(false);

            addMedicineButton.setVisible(true);
            editMedicineButton.setVisible(false);
            deleteMedicineButton.setVisible(false);

        }

        else {

            showAvailablilityofMedicationsRecord.setVisible(false);

            TableColumn<patientMedicineTableModel, String> medicineIDColumn = new TableColumn<>("Medicine ID");
            TableColumn<patientMedicineTableModel, String> patientIdColumn = new TableColumn<>("Patient ID");
            TableColumn<patientMedicineTableModel, String> doctorIdColumn = new TableColumn<>("Doctor ID");
            TableColumn<patientMedicineTableModel, String> medicineNameColumn = new TableColumn<>("Medicine Name");
            TableColumn<patientMedicineTableModel, Double> quantityColumn = new TableColumn<>("Quantity");
            TableColumn<patientMedicineTableModel, Double> dosageColumn = new TableColumn<>("Dosage");

            pastMedicationTable.getColumns().addAll(medicineIDColumn, patientIdColumn, doctorIdColumn,
                    medicineNameColumn,
                    quantityColumn, dosageColumn);

            medicineIDColumn.setCellValueFactory(new PropertyValueFactory<>("medicineID"));
            patientIdColumn.setCellValueFactory(new PropertyValueFactory<>("patientId"));
            doctorIdColumn.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
            medicineNameColumn.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
            quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            dosageColumn.setCellValueFactory(new PropertyValueFactory<>("dosage"));

            pastMedicationTable.setItems(patientMedicineTableModel.convertSelectedPatientMedicineDataToModel());
        }

    }

    @FXML
    void deleteMedicineRecord(ActionEvent event) throws IOException {

        patientMedicineTableModel selectedPastMedication = pastMedicationTable.getSelectionModel()
                .getSelectedItem();

        if (selectedPastMedication == null) {

            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("Please select a record before proceeding");
            error.showAndWait();
        }

        else {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmation");
            confirmation.setHeaderText("Are you sure you want to proceed?");
            confirmation.setContentText("Click OK to continue or Cancel to abort.");

            Optional<ButtonType> result = confirmation.showAndWait();

            if (result.get() == ButtonType.OK) {

                patientMedicineTableModel selectedPastMedicine = pastMedicationTable.getSelectionModel()
                        .getSelectedItem();

                pastMedicationTable.getItems().remove(selectedPastMedicine);

                String pastMedicineId = selectedPastMedicine.getMedicineID();

                medicineData.deleteMedicine(pastMedicineId);

                App.setRoot("patientGeneralDetailsScreen");
            } else {

                App.setRoot("patientGeneralDetailsScreenMedicalHistory");
            }
        }

    }

    @FXML
    void editMedicalHistoryRecord(ActionEvent event) throws IOException {
        patientMedicalHistoryTableModel selectedMedicalHistory = medicalHistoryTable.getSelectionModel()
                .getSelectedItem();

        if (selectedMedicalHistory == null) {

            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("Please select a record before proceeding");
            error.showAndWait();

        } else {

            medicalHistoryData.initMedicalHistoryData.setMedicalHistoryId(selectedMedicalHistory.getMedicalHistoryId());
            medicalHistoryData.initMedicalHistoryData.setPastMedicationId(selectedMedicalHistory.getPastMedicationId());
            medicalHistoryData.initMedicalHistoryData.setAllergies(selectedMedicalHistory.getAllergies());
            medicalHistoryData.initMedicalHistoryData.setDescription(selectedMedicalHistory.getDescription());
            medicalHistoryData.initMedicalHistoryData.setDiagnosisId(selectedMedicalHistory.getDiagnosisId());
            medicalHistoryData.initMedicalHistoryData.setPatientId(selectedMedicalHistory.getPatientId());
            medicalHistoryData.initMedicalHistoryData.setProcedureId(selectedMedicalHistory.getProcedureId());
            medicalHistoryData.initMedicalHistoryData.setTreatmentId(selectedMedicalHistory.getTreatmentId());

            App.setRoot("editMedicalHistoryScreen");
        }

    }

    @FXML
    void editMedicine(ActionEvent event) throws IOException {

        patientMedicineTableModel selectedPastMedication = pastMedicationTable.getSelectionModel()
                .getSelectedItem();

        if (selectedPastMedication == null) {

            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("Please select a record before proceeding");
            error.showAndWait();

        } else {

            medicineData.initMedicineData.setMedicineID(selectedPastMedication.getMedicineID());
            medicineData.initMedicineData.setDoctorId(selectedPastMedication.getDoctorId());
            medicineData.initMedicineData.setMedicineName(selectedPastMedication.getMedicineName());
            medicineData.initMedicineData.setQuantity(selectedPastMedication.getQuantity());
            medicineData.initMedicineData.setDosage(selectedPastMedication.getDosage());

            App.setRoot("editPastMedicationScreen");
        }

    }

    @FXML
    void switchToAddMedicineScreen(ActionEvent event) {

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
    void switchToAddMedicalHistoryScreen(ActionEvent event) throws IOException {
        App.setRoot("addMedicalHistory");
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
    void switchToTreatmentSection(ActionEvent event) throws IOException {
        App.setRoot("patientDetailsScreenTreatment");
    }

    @FXML
    void switchToPatient(MouseEvent event) throws IOException {

        App.setRoot("patientScreenGeneral");

    }

}
