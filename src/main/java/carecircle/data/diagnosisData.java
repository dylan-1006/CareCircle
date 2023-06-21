package carecircle.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import carecircle.classes.diagnosis;


public class diagnosisData {
    public static void main(String[] args) {
        loadDiagnosisDataFromDatabase();

    }

    public static String fileName = "src/main/resources/carecircle/assets/database/diagnosis.txt";

    public static List<diagnosis> loadDiagnosisDataFromDatabase() {
        List<diagnosis> diagnosisList = new ArrayList<>();

        try (

                BufferedReader reader = new BufferedReader(new FileReader(fileName))

        ) {
            String newLine;
            while ((newLine = reader.readLine()) != null) {
                String[] diagnosisData = newLine.split(",");
                String diagnosisId = diagnosisData[0].trim();
                String doctorId = diagnosisData[1].trim();
                String patientId = diagnosisData[2].trim();
                String date = diagnosisData[3].trim();
                String description = diagnosisData[4].trim();

                diagnosis newDiagnosis = new diagnosis(diagnosisId, doctorId, patientId, date, description);
                diagnosisList.add(newDiagnosis);

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return diagnosisList;
    }

}
