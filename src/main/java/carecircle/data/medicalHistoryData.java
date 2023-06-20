package carecircle.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import carecircle.classes.medicalHistory;

public class medicalHistoryData {
    public static void main(String[] args) {
        loadMedicalHistoryDataFromDatabase();

    }

    public static String fileName = "src/main/resources/carecircle/assets/database/medicalHistory.txt";

    public static List<medicalHistory> loadMedicalHistoryDataFromDatabase() {
        List<medicalHistory> medicalHistoryList = new ArrayList<>();

        try (

                BufferedReader reader = new BufferedReader(new FileReader(fileName))

        ) {
            String newLine;
            while ((newLine = reader.readLine()) != null) {
                String[] medicalHistoryData = newLine.split(",");
                String medicalHistoryId = medicalHistoryData[0].trim();
                String patientId = medicalHistoryData[1].trim();
                String diagnosisId = medicalHistoryData[2].trim();
                String treatmentId = medicalHistoryData[3].trim();
                String procedureId = medicalHistoryData[4].trim();
                String allergies = medicalHistoryData[4].trim();
                String pastMedication = medicalHistoryData[5].trim();
                String description = medicalHistoryData[6].trim();

              medicalHistory newMedicalHistory = new medicalHistory(medicalHistoryId, patientId, diagnosisId, treatmentId, procedureId, allergies, pastMedication, description);
                medicalHistoryList.add(newMedicalHistory);

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return medicalHistoryList;
    }

}
