package carecircle.classes;

import java.util.Date;

public class bloodAnalysis extends analysis {
    double bloodGlucose;
    double rbcCount;
    double wbcCount;

    public bloodAnalysis(String analysisID, Date date, String description, String patientID, String doctorID,
            double bloodGlucose, double rbcCount, double wbcCount) {
        super(analysisID, date, description, patientID, doctorID);
        this.bloodGlucose = bloodGlucose;
        this.rbcCount = rbcCount;
        this.wbcCount = wbcCount;

    }
}