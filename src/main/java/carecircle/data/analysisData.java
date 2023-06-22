package carecircle.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import carecircle.classes.analysis;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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

    public static void deleteAnalysis(String analysisId) {

        List<analysis> analysisList = analysisData.loadAnalysisDataFromDatabase();

        for (int i = 0; i < analysisList.size(); i++) {
            if (analysisList.get(i).getAnalysisID().equals(analysisId)) {
                analysisList.remove(i);
                break;
            }
        }

        try (FileWriter fileWriter = new FileWriter("src/main/resources/carecircle/assets/database/analysis.txt",
                false)) {
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (int i = 0; i < analysisList.size(); i++) {
                analysis analysis = analysisList.get(i);

                printWriter.println(
                        analysis.getAnalysisID() + "," +
                                analysis.getPatientID() + "," + analysis.getDoctorID() + ","
                                + analysis.getDate() + ","
                                + analysis.getDescription() + ",");
            }

            printWriter.close();

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Analysis Deleted!");
            alert.setHeaderText("Analysis record has been deleted");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static analysis initAnalysis = new analysis("analysisID ", "doctorID ", "patientID ", "date ",
            "description ");
}
