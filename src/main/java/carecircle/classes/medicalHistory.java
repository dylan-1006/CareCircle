package carecircle.classes;

public class medicalHistory {

    private String medicalHistoryId;
    private String patientId;
    private String diagnosisId;
    private String treatmentId;
    private String procedureId;
    private String description;
    private String allergies;
    private String pastMedicationId;

    public medicalHistory(String medicalHistoryId, String patientId, String diagnosisId, String treatmentId,
            String procedureId,
            String description, String allergies, String pastMedicationId) {
        this.patientId = patientId;
        this.diagnosisId = diagnosisId;
        this.treatmentId = treatmentId;
        this.procedureId = procedureId;
        this.description = description;
        this.allergies = allergies;
        this.pastMedicationId = pastMedicationId;
        this.medicalHistoryId = medicalHistoryId;
    }

    //belows are setters and getters

    public String getMedicalHistoryId() {
        return medicalHistoryId;
    }

    public void setMedicalHistoryId(String medicalHistoryId) {
        this.medicalHistoryId = medicalHistoryId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(String diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public String getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(String treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(String procedureId) {
        this.procedureId = procedureId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getPastMedicationId() {
        return pastMedicationId;
    }

    public void setPastMedicationId(String pastMedicationId) {
        this.pastMedicationId = pastMedicationId;
    }
}