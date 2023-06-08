package carecircle.classes;

import java.util.Date;

public class diagnosis {
    public String diagnosisID;
    public String doctorID;
    public String patientID;
    public Date date;
    public String description;

    public diagnosis(String diagnosisID, String doctorID, String patientID, Date date,String description){
        this.diagnosisID=diagnosisID;
        this.doctorID=doctorID;
        this.patientID=patientID;
        this.date=date;
        this.description=description;
    }
}
