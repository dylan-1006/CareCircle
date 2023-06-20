package carecircle.tableModels;

import java.util.List;

import carecircle.classes.medicalHistory;
import carecircle.classes.patient;
import carecircle.data.medicalHistoryData;
import carecircle.data.patientData;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public class patientMedicalHistoryTableModel {

    private SimpleStringProperty medicalHistoryId;
    private SimpleStringProperty patientId;
    private SimpleStringProperty diagnosisId;
    private SimpleStringProperty treatmentId;
    private SimpleStringProperty procedureId;
    private SimpleStringProperty description;
    private SimpleStringProperty allergies;
    private SimpleStringProperty pastMedicationId;

    public patientMedicalHistoryTableModel(String medicalHistoryId, String patientId, String diagnosisId,
            String treatmentId, String procedureId,
            String description, String allergies, String pastMedicationId) {

        this.medicalHistoryId = new SimpleStringProperty(medicalHistoryId);
        this.patientId = new SimpleStringProperty(patientId);
        this.diagnosisId = new SimpleStringProperty(diagnosisId);
        this.treatmentId = new SimpleStringProperty(treatmentId);
        this.procedureId = new SimpleStringProperty(procedureId);
        this.description = new SimpleStringProperty(description);
        this.allergies = new SimpleStringProperty(allergies);
        this.pastMedicationId = new SimpleStringProperty(pastMedicationId);
    }

    public static ObservableList<patientMedicalHistoryTableModel> convertSelectedPatientMedicalHistoryDataToModel() {

        List<medicalHistory> medicalHistoryList = medicalHistoryData.loadMedicalHistoryDataFromDatabase();
        ObservableList<patientMedicalHistoryTableModel> observableMedicalHistoryList = FXCollections
                .observableArrayList();

        for (int i = 0; i < medicalHistoryList.size(); i++) {

            if (medicalHistoryList.get(i).getPatientId().equals(patientData.initPatientData.getPatientID())) {

                String medicalHistoryId = medicalHistoryList.get(i).getMedicalHistoryId();
                String patientId = medicalHistoryList.get(i).getPatientId();
                String diagnosisId = medicalHistoryList.get(i).getDiagnosisId();
                String treatmentId = medicalHistoryList.get(i).getTreatmentId();
                String procedureId = medicalHistoryList.get(i).getProcedureId();
                String description = medicalHistoryList.get(i).getDescription();
                String allergies = medicalHistoryList.get(i).getAllergies();
                String pastMedicationId = medicalHistoryList.get(i).getPastMedicationId();

                patientMedicalHistoryTableModel medicalHistoryTableModel = new patientMedicalHistoryTableModel(
                        medicalHistoryId,
                        patientId,
                        diagnosisId, treatmentId, procedureId, description, allergies, pastMedicationId);

                observableMedicalHistoryList.add(medicalHistoryTableModel);

            }

        }
        return observableMedicalHistoryList;

    }

    public String getPatientId() {
        return patientId.get();
    }

    public SimpleStringProperty patientIdProperty() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId.set(patientId);
    }

    public String getMedicalHistoryId() {
        return medicalHistoryId.get();
    }

    public SimpleStringProperty medicalHistoryIdProperty() {
        return medicalHistoryId;
    }

    public void setMedicalHistoryId(String medicalHistoryId) {
        this.medicalHistoryId.set(medicalHistoryId);
    }

    public String getDiagnosisId() {
        return diagnosisId.get();
    }

    public SimpleStringProperty diagnosisIdProperty() {
        return diagnosisId;
    }

    public void setDiagnosisId(String diagnosisId) {
        this.diagnosisId.set(diagnosisId);
    }

    public String getTreatmentId() {
        return treatmentId.get();
    }

    public SimpleStringProperty treatmentIdProperty() {
        return treatmentId;
    }

    public void setTreatmentId(String treatmentId) {
        this.treatmentId.set(treatmentId);
    }

    public String getProcedureId() {
        return procedureId.get();
    }

    public SimpleStringProperty procedureIdProperty() {
        return procedureId;
    }

    public void setProcedureId(String procedureId) {
        this.procedureId.set(procedureId);
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

    public String getAllergies() {
        return allergies.get();
    }

    public SimpleStringProperty allergiesProperty() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies.set(allergies);
    }

    public String getPastMedicationId() {
        return pastMedicationId.get();
    }

    public SimpleStringProperty pastMedicationIdProperty() {
        return pastMedicationId;
    }

    public void setPastMedicationId(String pastMedicationId) {
        this.pastMedicationId.set(pastMedicationId);
    }
}
