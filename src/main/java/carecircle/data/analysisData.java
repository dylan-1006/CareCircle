package carecircle.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import carecircle.classes.analysis;


public class analysisData {
    public static void main(String[] args) {
        loadAnalysisDataFromDatabase();

    }

    public static String fileName = "src/main/resources/carecircle/assets/database/analysis.txt";

    public static List<analysis> loadAnalysisDataFromDatabase() {
        List<analysis> analysisList = new ArrayList<>();

        try (

                BufferedReader reader = new BufferedReader(new FileReader(fileName))

        ) {
            String newLine;
            while ((newLine = reader.readLine()) != null) {
                String[] analysisData = newLine.split(",");
                String analysisId = analysisData[0].trim();
                String doctorId = analysisData[1].trim();
                String patientId = analysisData[2].trim();
                String date = analysisData[3].trim();
                String description = analysisData[4].trim();

                analysis newAnalysis = new analysis(analysisId, doctorId, patientId, date, description);
                analysisList.add(newAnalysis);

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return analysisList;
    }

}
