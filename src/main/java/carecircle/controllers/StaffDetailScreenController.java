package carecircle.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import carecircle.App;
import carecircle.classes.doctor;
import carecircle.classes.nurse;
import carecircle.data.doctorData;
import carecircle.data.nurseData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
    private Button saveButton;

    @FXML
    private Button cancelButton;

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
        if (MedicalStaffScreenGeneralController.doctorOrNurse == true) {
            setDoctorDetail();
        } else {
            setNurseDetail();
        }
    }

    void setDoctorDetail() {

        int year = Year.now().getValue();
        int doctorAge = year - Integer.parseInt(doctorData.initDoctorData.getDateOfBirth().substring(0, 4));

        sideName.setText(doctorData.initDoctorData.getName());
        sideStaffID.setText(doctorData.initDoctorData.getDoctorID());
        sideGender.setText(doctorData.initDoctorData.getGender());
        sideAge.setText(doctorAge + "");

        name.setText(doctorData.initDoctorData.getName());
        gender.setText(doctorData.initDoctorData.getGender());
        typeOfStaff.setText("Doctor");
        dateOfBirth.setText(doctorData.initDoctorData.getDateOfBirth());
        email.setText(doctorData.initDoctorData.getEmail());
        contactNumber.setText(doctorData.initDoctorData.getPhoneNo());
    }

    void setNurseDetail() {
        int year = Year.now().getValue();
        int nurseAge = year - Integer.parseInt(nurseData.initNurseData.getDateOfBirth().substring(0, 4));

        sideName.setText(nurseData.initNurseData.getName());
        sideStaffID.setText(nurseData.initNurseData.getNurseID());
        sideGender.setText(nurseData.initNurseData.getGender());
        sideAge.setText(nurseAge + "");

        name.setText(nurseData.initNurseData.getName());
        gender.setText(nurseData.initNurseData.getGender());
        typeOfStaff.setText("Nurse");
        dateOfBirth.setText(nurseData.initNurseData.getDateOfBirth());
        email.setText(nurseData.initNurseData.getEmail());
        contactNumber.setText(nurseData.initNurseData.getPhoneNo());

    }

    @FXML
    void deleteThisStaff(MouseEvent event) throws IOException {
        if (MedicalStaffScreenGeneralController.doctorOrNurse == true) {
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

        } else {
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

    @FXML
    void goBack(MouseEvent event) throws IOException {
        App.setRoot("medicalStaffScreenGeneral");
    }

    @FXML
    void cancelEdit(ActionEvent event) throws IOException {
        App.setRoot("staffDetailScreen");
    }

    @FXML
    void saveDetails(ActionEvent event) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Are you sure you want to proceed?");
        confirmation.setContentText("Click OK to continue or Cancel to abort.");

        Optional<ButtonType> result = confirmation.showAndWait();
        if (MedicalStaffScreenGeneralController.doctorOrNurse == true) {
            if (result.get() == ButtonType.OK) {

                List<doctor> doctorList = doctorData.loadDoctorDataFromDatabase();

                for (int i = 0; i < doctorList.size(); i++) {

                    if (doctorList.get(i).getDoctorID().equals(doctorData.initDoctorData.getDoctorID())) {

                        // Setting the updated details
                        doctorList.get(i).setName(name.getText());
                        doctorList.get(i).setDateOfBirth(dateOfBirth.getText());
                        doctorList.get(i).setGender(gender.getText());
                        doctorList.get(i).setEmail(email.getText());
                        doctorList.get(i).setPhoneNo(contactNumber.getText());
                        break;
                    }

                }

                // String patientId = doctorD.initPatientData.getPatientID();

                try (FileWriter account = new FileWriter(
                        "src/main/resources/carecircle/assets/database/doctor.txt",
                        false)) {
                    PrintWriter accountWriter = new PrintWriter(account);

                    for (int i = 0; i < doctorList.size(); i++) {

                        accountWriter.println(
                                doctorList.get(i).getDoctorID() + "," + doctorList.get(i).getName() + ","
                                        + doctorList.get(i).getPhoneNo() + ","
                                        + doctorList.get(i).getEmail() + ","
                                        + doctorList.get(i).getDateOfBirth()
                                        + "," + doctorList.get(i).getGender()
                                        + "," + doctorList.get(i).getSpecialization());

                    }
                    accountWriter.close();
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Staff Edited!");
                    alert.setHeaderText("Doctor record has been edited");
                    alert.showAndWait();

                    App.setRoot("medicalStaffScreenGeneral");

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else {
            if (result.get() == ButtonType.OK) {

                List<nurse> nurseList = nurseData.loadNurseDataFromDatabase();

                for (int i = 0; i < nurseList.size(); i++) {

                    System.out.println("For loop started");

                    if (nurseList.get(i).getNurseID().equals(nurseData.initNurseData.getNurseID())) {
                        System.out.println("Nurse found");
                        // Setting the updated details
                        nurseList.get(i).setName(name.getText());
                        nurseList.get(i).setDateOfBirth(dateOfBirth.getText());
                        nurseList.get(i).setGender(gender.getText());
                        nurseList.get(i).setEmail(email.getText());
                        nurseList.get(i).setPhoneNo(contactNumber.getText());
                        break;
                    }

                }

                try (FileWriter account = new FileWriter(
                        "src/main/resources/carecircle/assets/database/nurse.txt",
                        false)) {
                    PrintWriter accountWriter = new PrintWriter(account);

                    for (int i = 0; i < nurseList.size(); i++) {

                        accountWriter.println(
                                nurseList.get(i).getNurseID() + "," + nurseList.get(i).getName() + ","
                                        + nurseList.get(i).getPhoneNo() + ","
                                        + nurseList.get(i).getEmail() + ","
                                        + nurseList.get(i).getDateOfBirth()
                                        + "," + nurseList.get(i).getGender());

                    }
                    accountWriter.close();
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Staff Edited!");
                    alert.setHeaderText("Nurse record has been edited");
                    alert.showAndWait();

                    App.setRoot("medicalStaffScreenGeneral");

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void editDetails(MouseEvent event) {
        name.setEditable(true);
        gender.setEditable(true);
        email.setEditable(true);
        dateOfBirth.setEditable(true);
        contactNumber.setEditable(true);

        saveButton.setVisible(true);
        cancelButton.setVisible(true);
        editButton.setVisible(false);

    }

}
