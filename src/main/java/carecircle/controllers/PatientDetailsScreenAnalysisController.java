package carecircle.controllers;

import java.io.IOException;
import java.time.Year;
import java.util.Optional;

import carecircle.App;
import carecircle.data.patientData;
import carecircle.tableModels.patientAnalysisTableModel;
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

public class PatientDetailsScreenAnalysisController {

    @FXML
    private Button addAnalysisButton;

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
    private Button editAnalysisButton;

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
    private Pane showAvailabilityOfAnalysisRecord;

    @FXML
    private TextField weight;

    @FXML
    private Pane patientDetailsSidePane;

    @FXML
    private TableView<patientAnalysisTableModel> patientDetailsAnalysisTable;

    public void initialize() {

        setSideBarPatientDetails();
        setAnalysisTable();
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

    void setAnalysisTable() {

        ObservableList<patientAnalysisTableModel> analysisDataList = patientAnalysisTableModel
                .convertSelectedPatientAnalysisDataToModel();

        if (analysisDataList.isEmpty()) {

            // Setting table & buttons to be non-visible
            patientDetailsAnalysisTable.setVisible(false);

            addAnalysisButton.setVisible(true);
            editAnalysisButton.setVisible(false);
            // deleteAnalysisButton.setVisible(false);

        } else {

            showAvailabilityOfAnalysisRecord.setVisible(false);

            TableColumn<patientAnalysisTableModel, String> analysisIDColumn = new TableColumn<>("Analysis ID");
            TableColumn<patientAnalysisTableModel, String> doctorIDColumn = new TableColumn<>("Doctor ID");
            TableColumn<patientAnalysisTableModel, String> patientIDColumn = new TableColumn<>("Patient ID");
            TableColumn<patientAnalysisTableModel, String> dateColumn = new TableColumn<>("Date");
            TableColumn<patientAnalysisTableModel, String> descriptionColumn = new TableColumn<>("Description");

            patientDetailsAnalysisTable.getColumns().addAll(analysisIDColumn, doctorIDColumn, patientIDColumn,
                    dateColumn,
                    descriptionColumn);

            analysisIDColumn.setCellValueFactory(new PropertyValueFactory<>("analysisID"));
            doctorIDColumn.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
            patientIDColumn.setCellValueFactory(new PropertyValueFactory<>("patientID"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

            patientDetailsAnalysisTable.setItems(analysisDataList);
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
    void editAnalysisRecord(ActionEvent event) {

    }

    @FXML
    void switchToAddAnalysisScreen(ActionEvent event) throws IOException {
        App.setRoot("addAnalysisScreen");
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
    void switchToTreatmentSection(ActionEvent event) throws IOException {
        App.setRoot("patientDetailsScreenTreatment");
    }

    @FXML
    void switchToPatient(MouseEvent event) throws IOException {
        App.setRoot("patientScreenGeneral");

    }

}
