package carecircle.classes;

import java.util.Date;

public class urineAnalysis extends analysis {
    double ureaContent;
    double urinePH;
    double sugarContent;

    public urineAnalysis(String analysisID, Date date, String description, String patientID, String doctorID,
            double ureaContent, double urinePH, double sugarContent) {
        super(analysisID, date, description, patientID, doctorID);
        this.ureaContent = ureaContent;
        this.urinePH = urinePH;
        this.sugarContent = sugarContent;

    }
}
