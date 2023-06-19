package carecircle.classes;

public class procedure {

    private String procedureId;
    private String description;
    private String date;
    private String doctorId;
    private String patientId;

    public procedure(String procedureId, String description, String date, String doctorId, String patientId) {
        this.procedureId = procedureId;
        this.description = description;
        this.date = date;
        this.doctorId = doctorId;
        this.patientId = patientId;
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