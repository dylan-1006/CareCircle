package carecircle.classes;


public class appointment {
    private String appointmentID;
    private String patientID;
    private String doctorID;
    private String date;
    private String time;
    private String venue;
    private String department;
    
    public appointment(String appointmentID, String patientID, String doctorID, String date, String time, String venue, String department) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.department = department;
    }
    
    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }
    
    public String getAppointmentID() {
        return appointmentID;
    }
    
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }
    
    public String getPatientID() {
        return patientID;
    }
    
    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }
    
    public String getDoctorID() {
        return doctorID;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public String getTime() {
        return time;
    }
    
    public void setVenue(String venue) {
        this.venue = venue;
    }
    
    public String getVenue() {
        return venue;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getDepartment() {
        return department;
    }
}
