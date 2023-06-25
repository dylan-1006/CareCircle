package carecircle.classes;

import java.util.Date;

public class bloodAnalysis extends analysis {
    private double bloodGlucose;
    private double rbcCount;
    private double wbcCount;

    public bloodAnalysis(String analysisID, String date, String description, String patientID, String doctorID,
            double bloodGlucose, double rbcCount, double wbcCount)

    {

        super(analysisID, doctorID, patientID, date, description);
        this.bloodGlucose = bloodGlucose;
        this.rbcCount = rbcCount;
        this.wbcCount = wbcCount;

    }

    public String getDescription() {

        return "Blood Analysis: " + super.getDescription();
    }

}