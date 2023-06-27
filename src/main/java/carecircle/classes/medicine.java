package carecircle.classes;

public class medicine {

    private String medicineID;
    private String patientId;
    private String doctorId;
    private String medicineName;
    private int quantity;
    private double dosage;

    public medicine(String medicineID, String patientId, String doctorId, String medicineName, int quantity, double dosage) {
        this.medicineID = medicineID;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.dosage = dosage;
    }

    //belows are setters and getters
    
    public String getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(String medicineID) {
        this.medicineID = medicineID;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int d) {
        this.quantity = d;
    }

    public double getDosage() {
        return dosage;
    }

    public void setDosage(double dosage) {
        this.dosage = dosage;
    }
}

