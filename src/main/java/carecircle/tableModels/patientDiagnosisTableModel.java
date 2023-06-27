package carecircle.tableModels;

import java.util.List;

import carecircle.classes.diagnosis;
import carecircle.data.diagnosisData;
import carecircle.data.patientData;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class patientDiagnosisTableModel {

    private SimpleStringProperty diagnosisID;
    private SimpleStringProperty doctorID;
    private SimpleStringProperty patientID;
    private SimpleStringProperty date;
    private SimpleStringProperty description;

    public patientDiagnosisTableModel(String diagnosisID, String doctorID, String patientID, String date,
            String description) {
        this.diagnosisID = new SimpleStringProperty(diagnosisID);
        this.doctorID = new SimpleStringProperty(doctorID);
        this.patientID = new SimpleStringProperty(patientID);
        this.date = new SimpleStringProperty(date);
        this.description = new SimpleStringProperty(description);
    }

    public static ObservableList<patientDiagnosisTableModel> convertSelectedPatientDiagnosisDataToModel() {

        // Load data from database
        List<diagnosis> diagnosisList = diagnosisData.loadDiagnosisDataFromDatabase();
        ObservableList<patientDiagnosisTableModel> observableDiagnosisList = FXCollections.observableArrayList();

        for (int i = 0; i < diagnosisList.size(); i++) {

            if (diagnosisList.get(i).getPatientID().equals(patientData.initPatientData.getPatientID())) {

                String diagnosisID = diagnosisList.get(i).getDiagnosisID();
                String doctorID = diagnosisList.get(i).getDoctorID();
                String patientID = diagnosisList.get(i).getPatientID();
                String date = diagnosisList.get(i).getDate();
                String description = diagnosisList.get(i).getDescription();

                patientDiagnosisTableModel diagnosisTableModel = new patientDiagnosisTableModel(diagnosisID, doctorID,
                        patientID, date, description);

                // Add created diagnosisTableModel into list
                observableDiagnosisList.add(diagnosisTableModel);

            }

        }
        return observableDiagnosisList;
    }

    // The following are getters and setters that get n set the data of this class
    public String getDiagnosisID() {
        return diagnosisID.get();
    }

    public SimpleStringProperty diagnosisIDProperty() {
        return diagnosisID;
    }

    public void setDiagnosisID(String diagnosisID) {
        this.diagnosisID.set(diagnosisID);
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
