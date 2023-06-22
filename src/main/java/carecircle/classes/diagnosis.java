package carecircle.classes;
public class diagnosis {
    public String diagnosisID;
    public String doctorID;
    public String patientID;
    public String date;
    public String description;

    public diagnosis(String diagnosisID, String doctorID, String patientID, String date,String description){
        this.diagnosisID=diagnosisID;
        this.doctorID=doctorID;
        this.patientID=patientID;
        this.date=date;
        this.description=description;
    }

    public void setDiagnosisID (String diagnosisID) {
        this.diagnosisID = diagnosisID;
    }

    public String getDiagnosisID() {
        return diagnosisID;
    }

    public void setDoctorID(String doctorId) {
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

    public static Object getText() {
        return null;
    }
}
