package carecircle.tableModels;

import java.util.List;

import carecircle.classes.treatment;
import carecircle.data.treatmentData;
import carecircle.data.patientData;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class patientTreatmentTableModel {

    private SimpleStringProperty treatmentID;
    private SimpleStringProperty doctorID;
    private SimpleStringProperty patientID;
    private SimpleStringProperty date;
    private SimpleStringProperty description;

    public patientTreatmentTableModel(String treatmentID, String doctorID, String patientID, String date,
            String description) {
        this.treatmentID = new SimpleStringProperty(treatmentID);
        this.doctorID = new SimpleStringProperty(doctorID);
        this.patientID = new SimpleStringProperty(patientID);
        this.date = new SimpleStringProperty(date);
        this.description = new SimpleStringProperty(description);
    }

    public static ObservableList<patientTreatmentTableModel> convertSelectedPatientTreatmentDataToModel() {
        List<treatment> treatmentList = treatmentData.loadTreatmentDataFromDatabase();
        ObservableList<patientTreatmentTableModel> observableTreatmentList = FXCollections.observableArrayList();

        for (int i = 0; i < treatmentList.size(); i++) {
            if (treatmentList.get(i).getPatientID().equals(patientData.initPatientData.getPatientID())) {
                String treatmentID = treatmentList.get(i).getTreatmentID();
                String doctorID = treatmentList.get(i).getDoctorID();
                String patientID = treatmentList.get(i).getPatientID();
                String date = treatmentList.get(i).getDate();
                String description = treatmentList.get(i).getDescription();

                patientTreatmentTableModel treatmentTableModel = new patientTreatmentTableModel(
                        treatmentID, doctorID, patientID, date, description);
                observableTreatmentList.add(treatmentTableModel);
            }
        }
        return observableTreatmentList;
    }

    public String getTreatmentID() {
        return treatmentID.get();
    }

    public SimpleStringProperty treatmentIDProperty() {
        return treatmentID;
    }

    public void setTreatmentID(String treatmentID) {
        this.treatmentID.set(treatmentID);
    }

    public String getDoctorID() {
        return doctorID.get();
    }

    public SimpleStringProperty doctorIDProperty() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID.set(doctorID);
    }

    public String getPatientID() {
        return patientID.get();
    }

    public SimpleStringProperty patientIDProperty() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID.set(patientID);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}
