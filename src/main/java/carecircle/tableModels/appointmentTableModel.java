package carecircle.tableModels;

import java.util.List;

import carecircle.classes.appointment;
import carecircle.data.appointmentData; 
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public class appointmentTableModel {
    private SimpleStringProperty appointmentID;
    private SimpleStringProperty patientID;
    private SimpleStringProperty doctorID;
    private SimpleStringProperty date;
    private SimpleStringProperty time;
    private SimpleStringProperty venue;
    private SimpleStringProperty department; 
    private Button details;
    
    public appointmentTableModel(String appointmentID, String patientID, String doctorID, String date, String time,
        String venue, String department) {
        this.appointmentID = new SimpleStringProperty(appointmentID);
        this.patientID = new SimpleStringProperty(patientID);
        this.doctorID = new SimpleStringProperty(doctorID);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.venue = new SimpleStringProperty(venue);
        this.department = new SimpleStringProperty(department);
        this.details = new Button("Details");
        this.details.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-padding: 0 0 5 0px");
    }

    public static ObservableList<appointmentTableModel> convertAppointmentDataToAppointmentDataModel() {

        List<appointment> appointmentList = appointmentData.loadAppointmentDataFromDatabase();
        ObservableList<appointmentTableModel> observableAppointmentList = FXCollections.observableArrayList();

        for (int i = 0; i < appointmentList.size(); i++) {

            String appointmentID = appointmentList.get(i).getAppointmentID();
            String patientID = appointmentList.get(i).getPatientID();
            String doctorID = appointmentList.get(i).getDoctorID();
            String date = appointmentList.get(i).getDate();
            String time = appointmentList.get(i).getTime();
            String venue = appointmentList.get(i).getVenue();
            String department = appointmentList.get(i).getDepartment();

            appointmentTableModel appointmentTableModel = new appointmentTableModel(appointmentID, patientID, doctorID, date, 
            time, venue, department);

            observableAppointmentList.add(appointmentTableModel);

        }

        return observableAppointmentList;

    }

    
        public String getAppointmentID() {
            return appointmentID.get();
        }
    
        public void setAppointmentID(String appointmentID) {
            this.appointmentID.set(appointmentID);
        }
    
        public String getPatientID() {
            return patientID.get();
        }
    
        public void setPatientID(String patientID) {
            this.patientID.set(patientID);;
        }
    
        public String getDoctorID() {
            return doctorID.get();
        }
    
        public void setDoctorID(String doctorID) {
            this.doctorID.set(doctorID);;
        }
    
        public String getDate() {
            return date.get();
        }
    
        public void setDate(String date) {
            this.date.set(date);;
        }
    
        public String getTime() {
            return time.get();
        }
    
        public void setTime(String time) {
            this.time.set(time);;
        }
    
        public String getVenue() {
            return venue.get();
        }
    
        public void setVenue(String venue) {
            this.venue.set(venue);;
        }
    
        public String getDepartment() {
            return department.get();
        }
    
        public void setDepartment(String department) {
            this.department.set(department);;
        }

        public Button getDetails() {
            return details;
        }
    
        public void setDetails(Button details) {
            this.details = details;
        }
    }
    
