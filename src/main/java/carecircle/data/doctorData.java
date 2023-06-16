package carecircle.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import carecircle.classes.doctor;

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
                String[] doctorData = newLine.split(",");
                String doctorID = doctorData[0].trim();
                String name = doctorData[1].trim();
                String phoneNo = doctorData[2].trim();
                String email = doctorData[3].trim();
                String dateOfBirth = doctorData[4].trim();
                String gender = doctorData[5].trim();
                String specialization = doctorData[6].trim();

                doctor newDoctor = new doctor(doctorID, name, phoneNo, email, dateOfBirth, gender, specialization);

                doctorList.add(newDoctor);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return doctorList;
    }
}
