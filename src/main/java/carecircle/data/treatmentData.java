package carecircle.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import carecircle.classes.treatment;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class treatmentData {
    public static void main(String[] args) {
        loadTreatmentDataFromDatabase();

    }

    public static String fileName = "src/main/resources/carecircle/assets/database/treatment.txt";

    public static List<treatment> loadTreatmentDataFromDatabase() {
        List<treatment> treatmentList = new ArrayList<>();

        try (

                BufferedReader reader = new BufferedReader(new FileReader(fileName))

        ) {
            String newLine;
            while ((newLine = reader.readLine()) != null) {

                // Reading data from .txt file
                String[] treatmentData = newLine.split(",");
                String treatmentId = treatmentData[0].trim();
                String doctorId = treatmentData[1].trim();
                String patientId = treatmentData[2].trim();
                String date = treatmentData[3].trim();
                String description = treatmentData[4].trim();

                treatment newTreatment = new treatment(treatmentId, doctorId, patientId, date, description);
                treatmentList.add(newTreatment);

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return treatmentList;
    }

    public static void deleteTreatment(String treatmentId) {
        List<treatment> treatmentList = treatmentData.loadTreatmentDataFromDatabase();

        for (int i = 0; i < treatmentList.size(); i++) {
            if (treatmentList.get(i).getTreatmentID().equals(treatmentId)) {

                // Remove selected data from list
                treatmentList.remove(i);
                break;
            }
        }

        try (FileWriter fileWriter = new FileWriter("src/main/resources/carecircle/assets/database/diagnosis.txt",
                false)) {
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (int i = 0; i < treatmentList.size(); i++) {
                treatment treatment = treatmentList.get(i);

                // Overwrite .txt file with newly edited data
                printWriter.println(
                        treatment.getTreatmentID() + ","
                                + treatment.getDoctorID() + ","
                                + treatment.getPatientID() + ","
                                + treatment.getDate() + ","
                                + treatment.getDescription() + ",");
            }

            printWriter.close();

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Treatment Deleted!");
            alert.setHeaderText("Treatment record has been deleted");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Initialise a treatment object that can be referenced else where later
    public static treatment initTreatment = new treatment("treatmentID ", "doctorID ", "patientID ", "date ",
            "description ");
}
