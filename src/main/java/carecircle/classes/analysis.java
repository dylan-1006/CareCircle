package carecircle.classes;

import java.util.Date;

public class analysis {
    String analysisID;
    Date date;
    String description;
    String patientID;
    String doctorID;

    public analysis(String analysisID, Date date,String description,String patientID, String doctorID){
        this.analysisID=analysisID;
        this.date=date;
        this.description=description;
        this.patientID=patientID;
        this.doctorID=doctorID;
    }
}
