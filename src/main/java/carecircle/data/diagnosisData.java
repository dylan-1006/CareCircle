package carecircle.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import carecircle.classes.diagnosis;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class diagnosisData {
    public static void main(String[] args) {
        loadDiagnosisDataFromDatabase();

    }

    public static String fileName = "src/main/resources/carecircle/assets/database/diagnosis.txt";

    public static List<diagnosis> loadDiagnosisDataFromDatabase() {
        // Creates new array list for diagnosis
        List<diagnosis> diagnosisList = new ArrayList<>();

        try (
                //Loads data from .txt file
                BufferedReader reader = new BufferedReader(new FileReader(fileName))

        ) {

            String newLine;
            while ((newLine = reader.readLine()) != null) {
                //Read content from .txt fi;e
                String[] diagnosisData = newLine.split(",");
                String diagnosisId = diagnosisData[0].trim();
                String doctorId = diagnosisData[1].trim();
                String patientId = diagnosisData[2].trim();
                String date = diagnosisData[3].trim();
                String description = diagnosisData[4].trim();

                diagnosis newDiagnosis = new diagnosis(diagnosisId, doctorId, patientId, date, description);
                //Adding data to previously created list
                diagnosisList.add(newDiagnosis);

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Return the created list
        return diagnosisList;
    }

    public static void deleteDiagnosis(String diagnosisId) {

        //Fetch the latest data about diagnosis
        List<diagnosis> diagnosisList = diagnosisData.loadDiagnosisDataFromDatabase();

        for (int i = 0; i < diagnosisList.size(); i++) {
            if (diagnosisList.get(i).getDiagnosisID().equals(diagnosisId)) {

                //Remove selected diagnosis data
                diagnosisList.remove(i);
                break;
            }
        }

        try (FileWriter fileWriter = new FileWriter("src/main/resources/carecircle/assets/database/diagnosis.txt",
                false)) {
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (int i = 0; i < diagnosisList.size(); i++) {
                diagnosis diagnosis = diagnosisList.get(i);


                //Write newly edited data into .txt file
                printWriter.println(
                        diagnosis.getDiagnosisID() + "," +
                                "," + diagnosis.getDoctorID() + ","
                                + diagnosis.getPatientID() + ","
                                + diagnosis.getDate() + ","
                                + diagnosis.getDescription() + ",");
            }

            printWriter.close();

            Alert alert = new Alert(AlertType.CONFIRMATION);
            //Alert that the deletion was successful and has been completed
            alert.setTitle("Diagnosis Deleted!");
            alert.setHeaderText("Diagnosis record has been deleted");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Initialising a diagnosis object that can be referenced elsewhere 
    public static diagnosis initDiagnosis = new diagnosis(" ", " ", " ", " ",
            "description ");

}
