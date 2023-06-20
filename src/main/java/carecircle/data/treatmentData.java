package carecircle.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import carecircle.classes.treatment;

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

}
