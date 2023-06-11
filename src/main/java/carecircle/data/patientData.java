package carecircle.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import carecircle.classes.patient;

public class patientData {
    public static void main(String[] args) {
        loadPatientDataFromDatabase();

    }

    public static String fileName = "src/main/resources/carecircle/assets/database/patient.txt";

    public static List<patient> loadPatientDataFromDatabase() {
        List<patient> patientList = new ArrayList<>();

        try (

                BufferedReader reader = new BufferedReader(new FileReader(fileName))

        ) {
            String newLine;
            while ((newLine = reader.readLine()) != null) {
                String[] patientData = newLine.split(",");
                String patientId = patientData[0].trim();
                String name = patientData[1].trim();
                String ic = patientData[2].trim();
                String phoneNo = patientData[3].trim();
                String email = patientData[4].trim();
                String dateOfBirth = patientData[5].trim();
                String gender = patientData[6].trim();
                String address = patientData[7].trim();
                double height = Double.parseDouble(patientData[8].trim());
                double weight = Double.parseDouble(patientData[9].trim());
                String bloodType = patientData[10].trim();

                patient newPatient = new patient(patientId, name, ic, phoneNo, email, dateOfBirth, gender, address,
                        height,
                        weight, bloodType);

                patientList.add(newPatient);

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return patientList;
    }

}
