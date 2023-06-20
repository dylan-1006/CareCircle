package carecircle.classes;

public class medication {
    public String medicationID;
    public String doctorID;
    public String patientID;
    public String medicationName;
    public String quantity;
    public String dosage;

    public medication(String medicationID, String doctorID,String patientID, String medicationName, String quantity, String dosage){
        this.medicationID = medicationID;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.medicationName = medicationName;
        this.quantity = quantity;
        this.dosage = dosage;
    }

    public void setMedicationID (String medicationID){
        this.medicationID = medicationID;
    }

    public String getMedicationID(){
        return medicationID;
    }

    public void setdoctorID (String doctorID){
        this.doctorID = doctorID;
    }

    public String getDoctorID(){
        return doctorID;
    }

    public void setPatientID (String patientID){
        this.patientID = patientID;
    }

    public String getPatientID(){
        return patientID;
    }

    public void setMedicationName (String medicationName){
        this.medicationName = medicationName;
    }

    public String getMedicationName(){
        return medicationName;
    }

    public void setQuantity (String quantity){
        this.quantity = quantity;
    }

    public String getQuantity(){
        return quantity;
    }

    public void setDosage (String dosage){
        this.dosage = dosage;
    }

    public String getDosage(){
        return dosage;
    }
}
