package carecircle.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import carecircle.classes.medicalHistory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class medicalHistoryData {
    public static void main(String[] args) {
        loadMedicalHistoryDataFromDatabase();

    }

    public static String fileName = "src/main/resources/carecircle/assets/database/medicalHistory.txt";

    public static List<medicalHistory> loadMedicalHistoryDataFromDatabase() {
        List<medicalHistory> medicalHistoryList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String newLine;
            while ((newLine = reader.readLine()) != null) {
                String[] medicalHistoryData = newLine.split(",");
                String medicalHistoryId = medicalHistoryData[0].trim();
                String patientId = medicalHistoryData[1].trim();
                String diagnosisId = medicalHistoryData[2].trim();
                String treatmentId = medicalHistoryData[3].trim();
                String procedureId = medicalHistoryData[4].trim();
                String description = medicalHistoryData[5].trim();
                String allergies = medicalHistoryData[6].trim();
                String pastMedicationId = medicalHistoryData[7].trim();

                medicalHistory newMedicalHistory = new medicalHistory(medicalHistoryId, patientId, diagnosisId,
                        treatmentId, procedureId,
                        description, allergies, pastMedicationId);

                medicalHistoryList.add(newMedicalHistory);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return medicalHistoryList;
    }

    public static void deleteMedicalHistory(String medicalHistoryId) {

        List<medicalHistory> medicalHistoryList = medicalHistoryData.loadMedicalHistoryDataFromDatabase();

        for (int i = 0; i < medicalHistoryList.size(); i++) {
            if (medicalHistoryList.get(i).getMedicalHistoryId().equals(medicalHistoryId)) {
                medicalHistoryList.remove(i);
                break;
            }
        }

        try (FileWriter fileWriter = new FileWriter("src/main/resources/carecircle/assets/database/medicalHistory.txt",
                false)) {
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (int i = 0; i < medicalHistoryList.size(); i++) {
                medicalHistory medicalHistory = medicalHistoryList.get(i);

                printWriter.println(
                        medicalHistory.getMedicalHistoryId() + "," +
                                medicalHistory.getPatientId() + "," + medicalHistory.getDiagnosisId() + ","
                                + medicalHistory.getTreatmentId() + ","
                                + medicalHistory.getProcedureId() + ","
                                + medicalHistory.getDescription() + ","
                                + medicalHistory.getAllergies() + ","
                                + medicalHistory.getPastMedicationId());
            }

            printWriter.close();

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Medical History Deleted!");
            alert.setHeaderText("Medical history record has been deleted");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static medicalHistory initMedicalHistoryData = new medicalHistory(" ", " ", " ", " ",
            " ", " ", " ", " ");
}
