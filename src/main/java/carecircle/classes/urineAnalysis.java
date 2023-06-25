package carecircle.classes;

import java.util.Date;

public class urineAnalysis extends analysis {
    private double ureaContent;
    private double urinePH;
    private double sugarContent;

    public urineAnalysis(String analysisID, String date, String description, String patientID, String doctorID,
            double ureaContent, double urinePH, double sugarContent)

    {
        super(analysisID, doctorID, patientID, date, description);
        this.ureaContent = ureaContent;
        this.urinePH = urinePH;
        this.sugarContent = sugarContent;

    }

    public String getDescription() {

        return "Urine Analysis: " + super.getDescription();
    }
}
