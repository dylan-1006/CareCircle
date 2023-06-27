package carecircle.controllers;

import java.io.IOException;

import carecircle.App;
import carecircle.classes.user;
import carecircle.data.userData;
import carecircle.tableModels.homeScreenPatientTableModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import carecircle.data.patientData;
import carecircle.data.doctorData;
import carecircle.data.nurseData;

public class HomeScreenController {

    @FXML
    private TextFlow welcomeMessageTextFlow;

    @FXML
    private Pane backgroundPane;

    @FXML
    private Pane navigationSideBar;

    @FXML
    private Text totalAvailableDoctors;

    @FXML
    private Text totalPatientAdmissions;

    @FXML
    private Text totalAvailableNurses;

    @FXML
    private Pane sideBarAppointmentButton;

    @FXML
    private TableView<homeScreenPatientTableModel> homeScreenPatientTable;

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
    private VBox menuBar;

    @FXML
    private TextFlow textFlowTitle;

    @FXML
    private Text titleText1;

    @FXML
    private Text titleText2;

    @FXML

    public void initialize() {

        // Loading the patient table
        ObservableList<homeScreenPatientTableModel> patientDataList = homeScreenPatientTableModel
                .convertPatientDataToPatientDataModel();

        TableColumn patientIdColumn = new TableColumn("Patient ID");
        TableColumn patientNameColumn = new TableColumn<>("Patient Name");
        TableColumn icColumn = new TableColumn("IC");
        TableColumn phoneNoColumn = new TableColumn("Phone No");
        TableColumn dateOfBirthColumn = new TableColumn("Date of Birth");
        TableColumn detailsColumn = new TableColumn("");

        homeScreenPatientTable.getColumns().addAll(patientIdColumn, patientNameColumn, icColumn, phoneNoColumn,
                dateOfBirthColumn, detailsColumn);

        patientIdColumn.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        patientNameColumn.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        icColumn.setCellValueFactory(new PropertyValueFactory<>("ic"));
        phoneNoColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        detailsColumn.setCellValueFactory(new PropertyValueFactory<>("details"));

        homeScreenPatientTable.setItems(patientDataList);

        // Setting the username in welcome message
        setUserName();
        setDoctorAmount();
        setPatientAmount();
        setNurseAmount();

    }

    void setPatientAmount() {

        totalPatientAdmissions.setText(Integer.toString(patientData.loadPatientDataFromDatabase().size()));
    }

    void setDoctorAmount() {

        totalAvailableDoctors.setText(Integer.toString(doctorData.loadDoctorDataFromDatabase().size()));
    }

    void setNurseAmount() {

        totalAvailableNurses.setText(Integer.toString(nurseData.loadNurseDataFromDatabase().size()));
    }

    void setUserName() {

        Text welcomeBackText = new Text("Welcome back,");

        welcomeBackText.setFont(Font.font("SansSerif", FontWeight.BOLD, 36));

        // Giving effect to text and making it look nice
        DropShadow dropShadow = new DropShadow();
        dropShadow.setBlurType(BlurType.GAUSSIAN);
        dropShadow.setColor(Color.rgb(0, 0, 0, 0.25));
        dropShadow.setHeight(5);
        dropShadow.setOffsetY(4);
        dropShadow.setRadius(4.5);
        dropShadow.setWidth(10);

        welcomeBackText.setEffect(dropShadow);
        welcomeBackText.setWrappingWidth(803);

        // Get user's name
        Text homeScreenUserName = new Text(userData.initUserData.getName());
        homeScreenUserName.setFont(Font.font("SansSerif", FontWeight.BOLD, 36));
        homeScreenUserName.setFill(Color.web("#4FB3FF"));

        homeScreenUserName.setEffect(dropShadow);
        homeScreenUserName.setWrappingWidth(803);

        // Add user's name into welcome message
        welcomeMessageTextFlow.getChildren().addAll(welcomeBackText, homeScreenUserName);
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

        App.setRoot("settingsScreen");

    }

}
