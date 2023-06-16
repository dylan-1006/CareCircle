package carecircle.classes;

public class appointment {

    private String doctorID;
    private String venue;
    private String patientName;
    private String patientID;
    private String date;
    private String time;
    private String department;
    private String appointmentID;

    public appointment(String doctorID, String venue, String patientName, String patientID, String date, String time,
            String department, String appointmentID) {
        this.doctorID = doctorID;
        this.venue = venue;
        this.patientName = patientName;
        this.patientID = patientID;
        this.date = date;
        this.time = time;
        this.department = department;
        this.appointmentID = appointmentID;
            }

public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

   public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

       public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

}