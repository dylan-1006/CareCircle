package carecircle.controllers;

import java.io.IOException;
import java.util.List;

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
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import carecircle.tableModels.patientTableModel;

public class PatientScreenGeneralController {

    @FXML
    private Pane addNewPatientPane;
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

        // Setting total patient amount
        setPatientAmount();

    }

    void setPatientAmount() {
        totalPatientAdmissions.setText(Integer.toString(patientData.loadPatientDataFromDatabase().size()));

    }

    @FXML
    void switchToUpdateScreen(MouseEvent event) {
        patientTableModel selectedPatient = patientScreenTable.getSelectionModel().getSelectedItem();

        patientData.initPatientData.setPatientID(selectedPatient.getPatientId());
        List<patient> patientList = patientData.loadPatientDataFromDatabase();

        // System.out.println(patientData.initPatientData.getPatientID());
        for (int i = 0; i < patientList.size(); i++) {

            if (patientList.get(i).getPatientID().equals(patientData.initPatientData.getPatientID())) {

                patientData.initPatientData.setName(patientList.get(i).getName());
                patientData.initPatientData.setIc(patientList.get(i).getIc());
                patientData.initPatientData.setPhoneNo(patientList.get(i).getPhoneNo());
                patientData.initPatientData.setEmail(patientList.get(i).getEmail());
                patientData.initPatientData.setDateOfBirth(patientList.get(i).getDateOfBirth());
                patientData.initPatientData.setGender(patientList.get(i).getGender());
                patientData.initPatientData.setAddress(patientList.get(i).getAddress());
                patientData.initPatientData.setWeight(patientList.get(i).getWeight());
                patientData.initPatientData.setHeight(patientList.get(i).getHeight());
                patientData.initPatientData.setBloodType(patientList.get(i).getBloodType());

                try {
                    App.setRoot("patientGeneralDetailsScreen");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            } else {

                continue;
            }

        }

    }

    @FXML
    void switchToAddNewPatientScreen(MouseEvent event) throws IOException {

        App.setRoot("addPatientScreen");

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
