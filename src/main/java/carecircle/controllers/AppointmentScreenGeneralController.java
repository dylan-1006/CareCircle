package carecircle.controllers;

import java.io.IOException;

import carecircle.App;
import carecircle.data.appointmentData;
import javafx.collections.ObservableList;
import carecircle.tableModels.appointmentTableModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class AppointmentScreenGeneralController {

    @FXML
    private Circle addNewAppointmentButton;
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

    @FXML
    private Text totalAppointments;

    @FXML
    private TableView<appointmentTableModel> appointmentTable;

    public void initialize() {

        // Loading the patient table
        ObservableList<appointmentTableModel> appointmentDataList = appointmentTableModel
                .convertAppointmentDataToAppointmentDataModel();

        TableColumn appointmentIDColumn = new TableColumn("Appointment ID");
        TableColumn patientIDColumn = new TableColumn<>("Patient ID");
        TableColumn doctorIDColumn = new TableColumn("Doctor ID");
        TableColumn dateColum = new TableColumn("Date");
        TableColumn timeColumn = new TableColumn("Time");
        TableColumn venueColumn = new TableColumn("Venue");
        TableColumn departmentColumn = new TableColumn("Department");

        TableColumn detailsColumn = new TableColumn("");

        appointmentTable.getColumns().addAll(appointmentIDColumn, patientIDColumn, doctorIDColumn, dateColum,
        timeColumn, venueColumn, departmentColumn, detailsColumn);

        appointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        patientIDColumn.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        doctorIDColumn.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        dateColum.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        venueColumn.setCellValueFactory(new PropertyValueFactory<>("venue"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));

        detailsColumn.setCellValueFactory(new PropertyValueFactory<>("details"));

        appointmentTable.setItems(appointmentDataList);

        setAppointmentAmount();

    }

    void setAppointmentAmount() {
        totalAppointments.setText(Integer.toString(appointmentData.loadAppointmentDataFromDatabase().size()));

    }

    @FXML
    void switchToAddAppointmentScreen(MouseEvent event) {

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
