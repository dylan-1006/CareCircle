package carecircle.controllers;

import java.io.IOException;
import carecircle.App;
import carecircle.tableModels.appointmentTableModel;
import carecircle.tableModels.staffTableModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class MedicalStaffScreenGeneralController {

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
    private TextFlow textFlowTitle1;

    @FXML
    private Text titleText1;

    @FXML
    private Text titleText2;

    @FXML
    private Text totalStaff;

    @FXML
    private TableView<staffTableModel> medicalStaffScreenTable;

    public void initialize() {

         // Loading the staff table

        ObservableList<staffTableModel> staffDataList = staffTableModel
                .convertStaffDataToStaffDataModel();

        TableColumn staffIDColumn = new TableColumn<>("Staff ID");
        TableColumn nameColumn = new TableColumn<>("Name");
        TableColumn phoneNoColumn = new TableColumn<>("Phone No");
        TableColumn emailColumn = new TableColumn<>("Email");
        TableColumn dateOfBirthColumn = new TableColumn<>("Date of Birth");
        TableColumn genderColumn = new TableColumn<>("Gender");
        TableColumn departmentColumn = new TableColumn<>("Department");
        TableColumn detailsColumn = new TableColumn<>("Details");

        medicalStaffScreenTable.getColumns().addAll(staffIDColumn, nameColumn, phoneNoColumn, emailColumn,
                dateOfBirthColumn, genderColumn, departmentColumn, detailsColumn);

        staffIDColumn.setCellValueFactory(new PropertyValueFactory<>("staffID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneNoColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        detailsColumn.setCellValueFactory(new PropertyValueFactory<>("details"));

        medicalStaffScreenTable.setItems(staffDataList);


        //Setting total staff amount
        setStaffAmount();
    }

    void setStaffAmount() {
        totalStaff.setText(Integer.toString(staffTableModel.convertStaffDataToStaffDataModel().size()));

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
