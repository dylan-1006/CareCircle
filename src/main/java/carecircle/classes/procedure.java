package carecircle.classes;

public class procedure {

    private String procedureId;
    private String doctorId;
    private String patientId;
    private String description;
    private String date;

    public procedure(String procedureId, String doctorId, String patientId, String date, String description) {
        this.procedureId = procedureId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.description = description;
        this.date = date;
    }

    //belows are setters and getters

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}