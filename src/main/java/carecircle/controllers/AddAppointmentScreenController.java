package carecircle.controllers;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import carecircle.App;
import carecircle.classes.appointment;
import carecircle.data.appointmentData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddAppointmentScreenController {
    ObservableList<String> doctorIDOptions = FXCollections.observableArrayList(
            "Choose Doctor ID", "A-", "A+", "B-", "B+", "O-", "O+", "AB-", "AB+");
    ObservableList<String> appointmentTimeOptions = FXCollections.observableArrayList(
            "Choose Time", "8:00AM", "10:00AM", "12:00PM", "2:00PM", "4:00PM", "6:00PM");
    ObservableList<String> venueOptions = FXCollections.observableArrayList(
            "Choose Venue", "D4.01", "D4.02", "D4.03", "D4.04", "D4.05", "D4.06");

    @FXML
    private Button Continue;

    @FXML
    private TextField ID;

    @FXML
    private TextField patientname;

    @FXML
    private TextField department;

    @FXML
    private TextField patientID;

    @FXML
    private DatePicker date;

    @FXML
    private ComboBox<String> doctorID;

    @FXML
    private ComboBox<String> venue;

    @FXML
    private ComboBox<String> appointmentTime;

    @FXML
    public void initialize() {
        doctorID.setItems(doctorIDOptions);
        appointmentTime.setItems(appointmentTimeOptions);
        venue.setItems(venueOptions);
    }

    @FXML
    void addNewAppointment(ActionEvent event) {
        try {
            List<appointment> appointmentList = appointmentData.loadAppointmentDataFromDatabase();
            int newAppointmentID = Integer
                    .parseInt(appointmentList.get(appointmentList.size() - 1).getAppointmentID().substring(1))
                    + 1;

            String newAppointmentIdFormatted = String.format("P%04d", newAppointmentID);

            appointment newAppointment = new appointment(newAppointmentIdFormatted, patientID.getText(),
                    doctorID.getSelectionModel().getSelectedItem().toString(), date.getValue().toString(),
                    venue.getPromptText(),
                    appointmentTime.getSelectionModel().getSelectedItem(), department.getText());

            FileWriter account = new FileWriter("src/main/resources/carecircle/assets/database/appointment.txt", true);

            PrintWriter accountWriter = new PrintWriter(account);
            accountWriter.println(
                    newAppointment.getDoctorID() + "," + newAppointment.getPatientID() + ","
                            + newAppointment.getDoctorID() + "," +
                            newAppointment.getDate() + ","
                            + newAppointment.getVenue() + ","
                            + newAppointment.getTime() + "," + newAppointment.getDepartment());
            accountWriter.close();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("New Appointment Added!");
            alert.setHeaderText(newAppointmentIdFormatted + " has been added");
            alert.showAndWait();

            App.setRoot("appointmentScreenGeneral");

        } catch (Exception e) {

        }
    }
}
