package carecircle.controllers;

import java.io.IOException;
import java.time.Year;
import java.util.Optional;

import carecircle.App;
import carecircle.data.doctorData;
import carecircle.data.nurseData;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class StaffDetailScreenController {

    @FXML
    private TextField contactNumber;

    @FXML
    private TextField dateOfBirth;

    @FXML
    private Text deleteStaffButton;

    @FXML
    private TextField email;

    @FXML
    private TextField typeOfStaff;

    @FXML
    private Text editButton;

    @FXML
    private TextField gender;

    @FXML
    private TextField name;

    @FXML
    private Text sideAge;

    @FXML
    private Text sideName;

    @FXML
    private Text sideGender;

    @FXML
    private Text sideStaffID;

    @FXML
    private Text staffID;


    @FXML
    void initialize() {
        if(MedicalStaffScreenGeneralController.doctorOrNurse==true){
            setDoctorDetail();
        }
        else{
            setNurseDetail();
        }
    }

    void setDoctorDetail(){

        int year = Year.now().getValue();
        int doctorAge = year - Integer.parseInt(doctorData.initDoctorData.getDateOfBirth().substring(0, 4));

        sideName.setText(doctorData.initDoctorData.getName());
        sideStaffID.setText(doctorData.initDoctorData.getDoctorID());
        sideGender.setText(doctorData.initDoctorData.getGender());
        sideAge.setText(doctorAge+"");


        name.setText(doctorData.initDoctorData.getName());
        gender.setText(doctorData.initDoctorData.getGender());
        typeOfStaff.setText("Doctor");
        dateOfBirth.setText(doctorData.initDoctorData.getDateOfBirth());
        email.setText(doctorData.initDoctorData.getEmail());
        contactNumber.setText(doctorData.initDoctorData.getPhoneNo());
    }

    void setNurseDetail(){
        int year = Year.now().getValue();
        int nurseAge = year - Integer.parseInt(nurseData.initNurseData.getDateOfBirth().substring(0, 4));

        sideName.setText(nurseData.initNurseData.getName());
        sideStaffID.setText(nurseData.initNurseData.getNurseID());
        sideGender.setText(nurseData.initNurseData.getGender());
        sideAge.setText(nurseAge+"");


        name.setText(nurseData.initNurseData.getName());
        gender.setText(nurseData.initNurseData.getGender());
        typeOfStaff.setText("Nurse");
        dateOfBirth.setText(nurseData.initNurseData.getDateOfBirth());
        email.setText(nurseData.initNurseData.getEmail());
        contactNumber.setText(nurseData.initNurseData.getPhoneNo());

    }
    @FXML
    void deleteThisStaff(MouseEvent event) throws IOException {
        if(MedicalStaffScreenGeneralController.doctorOrNurse==true){
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmation");
            confirmation.setHeaderText("Are you sure you want to proceed?");
            confirmation.setContentText("Click OK to continue or Cancel to abort.");
            Optional<ButtonType> result = confirmation.showAndWait();
            if (result.get() == ButtonType.OK) {

                doctorData.deleteData(doctorData.initDoctorData.getDoctorID());
                App.setRoot("medicalStaffScreenGeneral");
            } else {
    
                App.setRoot("staffDetailScreen");
            }

        }
        else{
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmation");
            confirmation.setHeaderText("Are you sure you want to proceed?");
            confirmation.setContentText("Click OK to continue or Cancel to abort.");
            Optional<ButtonType> result = confirmation.showAndWait();
            if (result.get() == ButtonType.OK) {

                nurseData.deleteData(nurseData.initNurseData.getNurseID());
                App.setRoot("medicalStaffScreenGeneral");
            } else {
    
                App.setRoot("staffDetailScreen");
            }

        }
    }

    void setStaffDetails(){

    }

    @FXML
    void goBack(MouseEvent event) throws IOException {
        App.setRoot("medicalStaffScreenGeneral");
    }

}
