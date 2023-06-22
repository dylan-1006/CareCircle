package carecircle.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import carecircle.App;
import carecircle.classes.doctor;
import carecircle.classes.patient;
import carecircle.data.appointmentData;
import carecircle.data.doctorData;
import carecircle.data.patientData;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class AppointmentDetailScreenController {

    @FXML
    private TextField contactNumber;

    @FXML
    private TextField date;

    @FXML
    private TextField department;

    @FXML
    private TextField doctorID;

    @FXML
    private TextField doctorName;

    @FXML
    private TextField patientID;

    @FXML
    private TextField patientName;

    @FXML
    private Text sideDate;

    @FXML
    private Text sideTime;

    @FXML
    private TextField time;

    @FXML
    private TextField venue;

    @FXML
    private Text sideAppointmentID;

    @FXML
    private Text sideDoctorID;

    @FXML
    private Text sideDoctorName;

    @FXML
    private Text sidePatientID;

    @FXML
    private Text sidePatientName;

    @FXML
    private Text sideVenue;

    @FXML
    private Text appointmentID;

    @FXML
    public void initialize() {

        setAppointmentDetails();

    }

    public void setAppointmentDetails() {
        List<patient> patientList = patientData.loadPatientDataFromDatabase();
        List<doctor> doctorList = doctorData.loadDoctorDataFromDatabase();

        sidePatientID.setText(appointmentData.initAppointmentData.getPatientID());
        sideAppointmentID.setText(appointmentData.initAppointmentData.getAppointmentID());
        sideDoctorID.setText(appointmentData.initAppointmentData.getDoctorID());
        sideVenue.setText(appointmentData.initAppointmentData.getVenue());
        sideDate.setText(appointmentData.initAppointmentData.getDate());
        sideTime.setText(appointmentData.initAppointmentData.getTime());

        appointmentID.setText(appointmentData.initAppointmentData.getAppointmentID());
        patientID.setText(appointmentData.initAppointmentData.getPatientID());
        doctorID.setText(appointmentData.initAppointmentData.getDoctorID());
        date.setText(appointmentData.initAppointmentData.getDate());
        venue.setText(appointmentData.initAppointmentData.getVenue());
        time.setText(appointmentData.initAppointmentData.getTime());
        department.setText(appointmentData.initAppointmentData.getDepartment());

        for (int i = 0; i < patientList.size(); i++) {
            if (appointmentData.initAppointmentData.getPatientID().equals(patientList.get(i).getPatientID())) {
                patientName.setText(patientList.get(i).getName());
                contactNumber.setText(patientList.get(i).getPhoneNo());
                sidePatientName.setText(patientList.get(i).getName());
                break;
            }

        }
        for (int i = 0; i < doctorList.size(); i++) {
            if (appointmentData.initAppointmentData.getDoctorID().equals(doctorList.get(i).getDoctorID())) {
                doctorName.setText(doctorList.get(i).getName());
                sideDoctorName.setText(doctorList.get(i).getName());
                break;
            }

        }
    }

    @FXML
    void editDetails(MouseEvent event) {
        venue.setEditable(true);
        time.setEditable(true);
        date.setEditable(true);
    }

    @FXML
    void deleteThisAppointment(MouseEvent event) throws IOException {

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Are you sure you want to proceed?");
        confirmation.setContentText("Click OK to continue or Cancel to abort.");

        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.get() == ButtonType.OK) {

            appointmentData.deleteAppointment(appointmentData.initAppointmentData.getAppointmentID());
            App.setRoot("appointmentScreenGeneral");
        } else {

            App.setRoot("appointmentDetailScreen");
        }

    }

    @FXML
    void goBack(MouseEvent event) throws IOException {
        App.setRoot("appointmentScreenGeneral");
    }

}
