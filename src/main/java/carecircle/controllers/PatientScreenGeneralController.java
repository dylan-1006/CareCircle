package carecircle.controllers;

import java.io.IOException;

import carecircle.App;
import carecircle.classes.patient;
import carecircle.data.patientData;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import carecircle.tableModels.patientTableModel;

public class PatientScreenGeneralController {

    @FXML
    private Text totalPatientAdmissions;

    @FXML
    private TableView<patientTableModel> patientScreenTable;

    @FXML
    private VBox menuBar;

    @FXML
    private Pane navigationSideBar;

    @FXML
    private Pane sideBarAppointmentButton;

    @FXML
    private Pane sideBarDashboardButton;

    @FXML
    private Pane sideBarHelpCenterButton;

    @FXML
    private Pane sideBarLogoutButton;

    @FXML
    private Pane sideBarMedicalStuffButton;

    @FXML
    private Pane sideBarPatientButton;

    @FXML
    private Pane sideBarSettingsButton;

    @FXML
    private TextFlow textFlowTitle;

    @FXML
    private Text titleText1;

    @FXML
    private Text titleText2;

    public void initialize() {

        // Loading the patient table
        ObservableList<patientTableModel> patientDataList = patientTableModel
                .convertPatientDataToPatientDataModel();

        TableColumn patientIdColumn = new TableColumn("Patient ID");
        TableColumn patientNameColumn = new TableColumn<>("Patient Name");
        TableColumn icColumn = new TableColumn("IC");
        TableColumn phoneNoColumn = new TableColumn("Phone No");
        TableColumn dateOfBirthColumn = new TableColumn("Date of Birth");
        TableColumn heightColumn = new TableColumn("Height [CM]");
        TableColumn weightColumn = new TableColumn("Weight [KG]");
        TableColumn bloodTypeColumn = new TableColumn("Blood Type");

        TableColumn detailsColumn = new TableColumn("");

        patientScreenTable.getColumns().addAll(patientIdColumn, patientNameColumn, icColumn, phoneNoColumn,
                dateOfBirthColumn, heightColumn, weightColumn, bloodTypeColumn, detailsColumn);

        patientIdColumn.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        patientNameColumn.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        icColumn.setCellValueFactory(new PropertyValueFactory<>("ic"));
        phoneNoColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        heightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        bloodTypeColumn.setCellValueFactory(new PropertyValueFactory<>("bloodType"));

        detailsColumn.setCellValueFactory(new PropertyValueFactory<>("details"));

        patientScreenTable.setItems(patientDataList);

        setPatientAmount();

    }

    void setPatientAmount(){
            totalPatientAdmissions.setText(Integer.toString(patientData.loadPatientDataFromDatabase().size()));

    }

    @FXML
    void switchToAppoitmentScreen(MouseEvent event) throws IOException {
        App.setRoot("appointmentScreenGeneral");

    }

    @FXML
    void switchToHelpCenterScreen(MouseEvent event) throws IOException {
        App.setRoot("helpCenterScreen");

    }

    @FXML
    void switchToHomeScreen(MouseEvent event) throws IOException {
        App.setRoot("homeScreen");

    }

    @FXML
    void switchToLoginScreen(MouseEvent event) throws IOException {
        App.setRoot("loginScreen");

    }

    @FXML
    void switchToMedicalStaffScreen(MouseEvent event) throws IOException {
        App.setRoot("medicalStaffScreenGeneral");

    }

    @FXML
    void switchToPatientScreen(MouseEvent event) throws IOException {
        App.setRoot("patientScreenGeneral");

    }

    @FXML
    void switchToSettingsScreen(MouseEvent event) throws IOException {
        // Have to merge settings screen with master first
        // App.setRoot("appointmentScreenGeneral");

    }

}
