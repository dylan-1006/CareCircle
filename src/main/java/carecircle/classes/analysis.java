package carecircle.classes;

public class analysis {
    private String analysisID;
    private String doctorID;
    private String patientID;
    private String date;
    private String description;

    public analysis(String analysisID, String doctorID, String patientID, String date, String description) {
        this.analysisID = analysisID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.date = date;
        this.description = description;

    }

    //belows are setters and getters

    public void setAnalysisID(String analysisID) {
        this.analysisID = analysisID;
    }

    public String getAnalysisID() {
        return analysisID;
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
