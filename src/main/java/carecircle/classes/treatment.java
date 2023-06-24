package carecircle.classes;

public class treatment {
    private String treatmentID;
    private String patientID;
    private String doctorID;
    private String date;
    private String description;

    public treatment(String treatmentID, String doctorID, String patientID, String date, String description) {
        this.treatmentID = treatmentID;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.date = date;
        this.description = description;
    }

    public void setTreatmentID(String treatmentID) {
        this.treatmentID = treatmentID;
    }

    public String getTreatmentID() {
        return treatmentID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
