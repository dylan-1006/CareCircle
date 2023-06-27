package carecircle.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import carecircle.classes.doctor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class doctorData {
    public static void main(String[] args) {
        loadDoctorDataFromDatabase();
    }

    public static String fileName = "src/main/resources/carecircle/assets/database/doctor.txt";

    public static List<doctor> loadDoctorDataFromDatabase() {
        List<doctor> doctorList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String newLine;
            while ((newLine = reader.readLine()) != null) {
                // Reading data from .txt file
                String[] doctorData = newLine.split(",");
                String doctorID = doctorData[0].trim();
                String name = doctorData[1].trim();
                String phoneNo = doctorData[2].trim();
                String email = doctorData[3].trim();
                String dateOfBirth = doctorData[4].trim();
                String gender = doctorData[5].trim();
                String specialization = doctorData[6].trim();

                doctor newDoctor = new doctor(doctorID, name, phoneNo, email, dateOfBirth, gender, specialization);

                // Adding data into previously created list
                doctorList.add(newDoctor);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return doctorList;
    }

    public static void deleteData(String doctorID) throws IOException {

        List<doctor> doctorList = doctorData.loadDoctorDataFromDatabase();
        for (int i = 0; i < doctorList.size(); i++) {
            if (doctorList.get(i).getDoctorID().equals(doctorID)) {

                // Remove selected doctor data
                doctorList.remove(i);
                break;

            }

        }

        try (FileWriter account = new FileWriter(
                "src/main/resources/carecircle/assets/database/doctor.txt",
                false)) {
            PrintWriter accountWriter = new PrintWriter(account);

            for (int i = 0; i < doctorList.size(); i++) {

                // Rewrite newly edited data into .txt file
                accountWriter.println(
                        doctorList.get(i).getDoctorID() + "," + doctorList.get(i).getName() + ","
                                + doctorList.get(i).getPhoneNo() + ","
                                + doctorList.get(i).getEmail() + ","
                                + doctorList.get(i).getDateOfBirth()
                                + "," + doctorList.get(i).getGender()
                                + ","
                                + doctorList.get(i).getSpecialization());

            }
            accountWriter.close();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Doctor Deleted!");
            alert.setHeaderText("Doctor record has been deleted");
            alert.showAndWait();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // Initialising a doctor object that can be referenced else where later
    public static doctor initDoctorData = new doctor(" ", " ", " ", " ", " ", " ", " ");

}