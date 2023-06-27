package carecircle.tableModels;

import java.util.List;

import carecircle.classes.analysis;
import carecircle.data.analysisData;
import carecircle.data.patientData;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class patientAnalysisTableModel {

    private SimpleStringProperty analysisID;
    private SimpleStringProperty doctorID;
    private SimpleStringProperty patientID;
    private SimpleStringProperty date;
    private SimpleStringProperty description;

    public patientAnalysisTableModel(String analysisID, String doctorID, String patientID, String date,
            String description) {
        this.analysisID = new SimpleStringProperty(analysisID);
        this.doctorID = new SimpleStringProperty(doctorID);
        this.patientID = new SimpleStringProperty(patientID);
        this.date = new SimpleStringProperty(date);
        this.description = new SimpleStringProperty(description);
    }

    public static ObservableList<patientAnalysisTableModel> convertSelectedPatientAnalysisDataToModel() {
        List<analysis> analysisList = analysisData.loadAnalysisDataFromDatabase();
        ObservableList<patientAnalysisTableModel> observableAnalysisList = FXCollections.observableArrayList();

        // Load analysis data
        for (int i = 0; i < analysisList.size(); i++) {
            if (analysisList.get(i).getPatientID().equals(patientData.initPatientData.getPatientID())) {

                String analysisID = analysisList.get(i).getAnalysisID();
                String doctorID = analysisList.get(i).getDoctorID();
                String patientID = analysisList.get(i).getPatientID();
                String date = analysisList.get(i).getDate();
                String description = analysisList.get(i).getDescription();

                patientAnalysisTableModel analysisTableModel = new patientAnalysisTableModel(analysisID, doctorID,
                        patientID, date, description);

                // Add converted analysis data from string into list
                observableAnalysisList.add(analysisTableModel);
            }
        }
        return observableAnalysisList;
    }

    // The following are getters and setters that get n set the data of this class
    public String getAnalysisID() {
        return analysisID.get();
    }

    public SimpleStringProperty analysisIDProperty() {
        return analysisID;
    }

    public void setAnalysisID(String analysisID) {
        this.analysisID.set(analysisID);
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
