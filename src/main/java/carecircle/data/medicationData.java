package carecircle.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import carecircle.classes.medication;

public class medicationData {
    public static void main(String[] args) {
        loadMedicationDataFromDatabase();

    }

    public static String fileName = "src/main/resources/carecircle/assets/database/medication.txt";

    public static List<medication> loadMedicationDataFromDatabase() {
        List<medication> medicationList = new ArrayList<>();

        try (

                BufferedReader reader = new BufferedReader(new FileReader(fileName))

        ) {
            String newLine;
            while ((newLine = reader.readLine()) != null) {
                String[] medicationData = newLine.split(",");
                String medicationId = medicationData[0].trim();
                String doctorId = medicationData[1].trim();
                String patientId = medicationData[2].trim();
                String medicationName = medicationData[3].trim();
                String quantity = medicationData[4].trim();
                String dosage = medicationData[4].trim();

                medication newMedication = new medication(medicationId, doctorId, patientId, medicationName, quantity, dosage);
                medicationList.add(newMedication);

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return medicationList;
    }

}
