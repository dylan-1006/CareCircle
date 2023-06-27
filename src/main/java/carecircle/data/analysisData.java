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

        //Creates new array list for analysis
        List<analysis> analysisList = new ArrayList<>();

        try (

                //Reads .txt file from database
                BufferedReader reader = new BufferedReader(new FileReader(fileName))

        ) {
            //Adds data from the .txt file into array list created just now
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

        //Return array list with data from .txt file
        return analysisList;
    }


    public static void deleteAnalysis(String analysisId) {


        //Retrieves data from .txt file
        List<analysis> analysisList = analysisData.loadAnalysisDataFromDatabase();


        //Removes selected data
        for (int i = 0; i < analysisList.size(); i++) {
            if (analysisList.get(i).getAnalysisID().equals(analysisId)) {
                analysisList.remove(i);
                break;
            }
        }

        //Overwrite the .txt file with the newly edited data
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

            //Alert that the deletion of data has been completed
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Analysis Deleted!");
            alert.setHeaderText("Analysis record has been deleted");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Initialise an analysis object that can be referenced else where
    public static analysis initAnalysis = new analysis("analysisID ", "doctorID ", "patientID ", "date ",
            "description ");
}
