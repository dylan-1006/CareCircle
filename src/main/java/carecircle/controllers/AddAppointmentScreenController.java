package carecircle.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import carecircle.App;
import carecircle.classes.appointment;
import carecircle.classes.doctor;
import carecircle.classes.patient;
import carecircle.data.appointmentData;
import carecircle.data.doctorData;
import carecircle.data.patientData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddAppointmentScreenController {

        ObservableList<String> venueOptions = FXCollections.observableArrayList(
                        "Main Hospital", "Speciality Clinic");

        ObservableList<String> appointmentTimeOptions = FXCollections.observableArrayList(
                        "9:00", "9:30", "10:00", "10:30", "11:00", "12:00", "12:30",
                        "13:00", "13:30", "14:00", "15:00", "15:30", "16:00", "16:30", "17:00");

        ObservableList<String> departmentOptions = FXCollections.observableArrayList(
                        "Cardiology",
                        "Dermatology",
                        "Orthopedics",
                        "Ophthalmology",
                        "Pediatrics",
                        "Neurology",
                        "Internal Medicine",
                        "Obstetrics and Gynecology",
                        "Surgery",
                        "Psychiatry",
                        "Oncology",
                        "Radiology",
                        "Emergency Medicine",
                        "Physical Therapy",
                        "Cardiothoracic Surgery",
                        "Endocrinology",
                        "ENT (Otorhinolaryngology)");

        @FXML
        private Button Continue;

        @FXML
        private TextField ID;

        @FXML
        private TextField patientname;

        @FXML
        private ComboBox<String> department;

        @FXML
        private ComboBox<String> patientNameBox;

        @FXML
        private DatePicker date;

        @FXML
        private ComboBox<String> doctorID;

        @FXML
        private ComboBox<String> venue;

        @FXML
        private ComboBox<String> appointmentTime;

        @FXML

        //setItems
        public void initialize() {

                doctorID.setItems(fetchAvailableDoctorId());
                appointmentTime.setItems(appointmentTimeOptions);
                venue.setItems(venueOptions);
                department.setItems(departmentOptions);
                patientNameBox.setItems(fetchAvailablePatientName());
        }

        //addNewAppointment

        @FXML
        void addAppointment(ActionEvent event) {

                if (patientNameBox.getSelectionModel().isEmpty() ||
                                doctorID.getSelectionModel().isEmpty() || date.getValue().toString().equals("")
                                || venue.getSelectionModel().isEmpty() || appointmentTime.getSelectionModel().isEmpty()
                                || department.getSelectionModel().isEmpty()) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Missing Information");
                        alert.setContentText("Please fill in all the required fields.");
                        alert.showAndWait();

                } else {
                        try {
                                List<appointment> appointmentList = appointmentData.loadAppointmentDataFromDatabase();
                                int newAppointmentID = Integer
                                                .parseInt(appointmentList.get(appointmentList.size() - 1)
                                                                .getAppointmentID()
                                                                .substring(1))
                                                + 1;

                                String newAppointmentIdFormatted = String.format("A0%2d", newAppointmentID);

                                List<patient> patientList = patientData.loadPatientDataFromDatabase();

                                String patientId = " ";
                                for (int i = 0; i < patientList.size(); i++) {

                                        if (patientList.get(i).getName().equals(patientNameBox.getSelectionModel()
                                                        .getSelectedItem().toString())) {

                                                patientId = patientList.get(i).getPatientID();
                                                break;
                                        }

                                }

                                appointment newAppointment = new appointment(newAppointmentIdFormatted,
                                                patientId,
                                                doctorID.getSelectionModel().getSelectedItem().toString(),
                                                date.getValue().toString(),
                                                venue.getSelectionModel().getSelectedItem().toString(),
                                                appointmentTime.getSelectionModel().getSelectedItem(),
                                                department.getSelectionModel().getSelectedItem().toString());
                                //below is writing into the file

                                FileWriter account = new FileWriter(
                                                "src/main/resources/carecircle/assets/database/appointment.txt", true);

                                PrintWriter accountWriter = new PrintWriter(account);
                                accountWriter.println(
                                                newAppointmentIdFormatted + "," + newAppointment.getPatientID() + ","
                                                                + newAppointment.getDoctorID() + ","
                                                                +
                                                                newAppointment.getDate() + ","
                                                                + newAppointment.getVenue() + ","
                                                                + newAppointment.getTime() + ","
                                                                + newAppointment.getDepartment());
                                accountWriter.close();
                                Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("New Appointment Added!");
                                alert.setHeaderText("New appointment has been added");
                                alert.showAndWait();

                                App.setRoot("appointmentScreenGeneral");

                        } catch (Exception e) {

                        }
                }

        }

        ObservableList<String> fetchAvailableDoctorId() {

                List<doctor> doctorList = doctorData.loadDoctorDataFromDatabase();
                ObservableList<String> doctorIdOptions = FXCollections.observableArrayList("Choose doctor ID");

                for (int i = 0; i < doctorList.size(); i++) {

                        doctorIdOptions.add(doctorList.get(i).getDoctorID());

                }

                return doctorIdOptions;
        }

        ObservableList<String> fetchAvailablePatientName() {

                List<patient> patientList = patientData.loadPatientDataFromDatabase();
                ObservableList<String> patientIdOptions = FXCollections.observableArrayList("Choose patient name");

                for (int i = 0; i < patientList.size(); i++) {

                        patientIdOptions.add(patientList.get(i).getName());

                }

                return patientIdOptions;
        }

        @FXML
        void backToAppointmentScreen(MouseEvent event) throws IOException {
                App.setRoot("appointmentScreenGeneral");
        }

}
