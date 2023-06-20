package carecircle.classes;

public class medicalHistory {
    String medicalHistoryID;
    String patientID;
    String diagnosisID;
    String treatmentID;
    String procedureID;
    String allergies;
    String pastMedicine;
    String description;

    public medicalHistory(String medicalHistoryID, String patientID, String diagnosisID, String treatmentID,
            String procedureID, String allergies, String pastMedicine, String description) {
        this.medicalHistoryID = medicalHistoryID;
        this.patientID = patientID;
        this.diagnosisID = diagnosisID;
        this.treatmentID = treatmentID;
        this.procedureID = procedureID;
        this.allergies = allergies;
        this.pastMedicine = pastMedicine;
        this.description = description;
    }

    public void setMedicalHistoryID(String patientID) {
        this.medicalHistoryID = medicalHistoryID;
    }

    public String getMedicalHistoryID() {
        return medicalHistoryID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setDiagnosisID(String diagnosisID) {
        this.diagnosisID = diagnosisID;
    }

    public String getDiagnosisID() {
        return diagnosisID;
    }

    public void setTreatmentID(String treatmentID) {
        this.treatmentID = treatmentID;
    }

    public String getTreatmentID() {
        return treatmentID;
    }

    public void setProcedureID(String procedureID) {
        this.procedureID = procedureID;
    }

    public String getProcedureID() {
        return procedureID;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setPastMedicine(String pastMedicine) {
        this.pastMedicine = pastMedicine;
    }

    public String getPastMedicine() {
        return pastMedicine;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
