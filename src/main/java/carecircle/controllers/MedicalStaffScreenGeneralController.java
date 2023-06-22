package carecircle.controllers;

import java.io.IOException;
import java.util.List;

import carecircle.App;
import carecircle.classes.doctor;
import carecircle.classes.nurse;
import carecircle.data.doctorData;
import carecircle.data.nurseData;
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
    public static boolean doctorOrNurse=false;
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
    private Pane addNewStaff;

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

        // Setting total staff amount
        setStaffAmount();
    }

    void setStaffAmount() {
        totalStaff.setText(Integer.toString(staffTableModel.convertStaffDataToStaffDataModel().size()));

    }


    @FXML
    void switchToUpdateScreen(MouseEvent event) {
       staffTableModel selectedStaff = medicalStaffScreenTable.getSelectionModel().getSelectedItem();

       

        if(selectedStaff.getDepartment().equals("Doctor")){
            doctorData.initDoctorData.setDoctorID(selectedStaff.getStaffID());
            List<doctor> doctorList = doctorData.loadDoctorDataFromDatabase();
            doctorOrNurse=true;

            for (int i = 0; i < doctorList.size(); i++) {

                if (doctorList.get(i).getDoctorID().equals(doctorData.initDoctorData.getDoctorID())) {
    
                    doctorData.initDoctorData.setName(doctorList.get(i).getName());
                    doctorData.initDoctorData.setDateOfBirth(doctorList.get(i).getDateOfBirth());
                    doctorData.initDoctorData.setEmail(doctorList.get(i).getEmail());
                    doctorData.initDoctorData.setGender(doctorList.get(i).getGender());
                    doctorData.initDoctorData.setPhoneNo(doctorList.get(i).getPhoneNo());
                    doctorData.initDoctorData.setSpecialization(doctorList.get(i).getSpecialization());    
                    try {
                        App.setRoot("staffDetailScreen");
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
    
                    break;
                } 
                
            }

        }

        else if(selectedStaff.getDepartment().equals("Nurse")){
            nurseData.initNurseData.setNurseID(selectedStaff.getStaffID());
            List<nurse> nurseList = nurseData.loadNurseDataFromDatabase();
            doctorOrNurse=false;

            for (int i = 0; i < nurseList.size(); i++) {

                if (nurseList.get(i).getNurseID().equals(nurseData.initNurseData.getNurseID())) {
    
                    nurseData.initNurseData.setName(nurseList.get(i).getName());
                    nurseData.initNurseData.setGender(nurseList.get(i).getGender());
                    nurseData.initNurseData.setDateOfBirth(nurseList.get(i).getDateOfBirth());   
                    nurseData.initNurseData.setEmail(nurseList.get(i).getEmail());
                    nurseData.initNurseData.setPhoneNo(nurseList.get(i).getPhoneNo());
                    try {
                        App.setRoot("staffDetailScreen");
                    } catch (IOException e) {
                       
                        e.printStackTrace();
                    }
    
                    break;
                } 
                
            }
        }
        
    }

    

    @FXML
    void switchToAddStaffScreen(MouseEvent event) throws IOException {
        App.setRoot("addStaffScreen");

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
