package carecircle.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import carecircle.App;
import carecircle.classes.patient;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

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

    public static void deletePatient(String patientId) throws IOException {

        List<patient> patientList = patientData.loadPatientDataFromDatabase();
        for (int i = 0; i < patientList.size(); i++) {
            if (patientList.get(i).getPatientID().equals(patientId)) {

                patientList.remove(i);
                break;

            }

        }

        try (FileWriter account = new FileWriter(
                "src/main/resources/carecircle/assets/database/patient.txt",
                false)) {
            PrintWriter accountWriter = new PrintWriter(account);

            for (int i = 0; i < patientList.size(); i++) {

                accountWriter.println(
                        patientList.get(i).getPatientID() + "," + patientList.get(i).getName() + ","
                                + patientList.get(i).getIc() + ","
                                + patientList.get(i).getPhoneNo() + ","
                                + patientList.get(i).getEmail() + ","
                                + patientList.get(i).getDateOfBirth()
                                + "," + patientList.get(i).getGender()
                                + ","
                                + patientList.get(i).getAddress() + "," + patientList.get(i).getHeight() + ","
                                + patientList.get(i).getWeight() + ","
                                + patientList.get(i).getBloodType());

            }
            accountWriter.close();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Patient Deleted!");
            alert.setHeaderText("Patient record has been deleted");
            alert.showAndWait();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static patient initPatientData = new patient(" ", " ", " ", " ", " ", " ", " ", " ", 0, 0, " ");

}
