package carecircle.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import carecircle.classes.nurse;

public class nurseData {
    public static void main(String[] args) {
        loadNurseDataFromDatabase();
    }

    public static String fileName = "src/main/resources/carecircle/assets/database/nurse.txt";

    public static List<nurse> loadNurseDataFromDatabase() {
        List<nurse> nurseList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String newLine;
            while ((newLine = reader.readLine()) != null) {
                String[] nurseData = newLine.split(",");
                String nurseID = nurseData[0].trim();
                String name = nurseData[1].trim();
                String phoneNo = nurseData[2].trim();
                String email = nurseData[3].trim();
                String dateOfBirth = nurseData[4].trim();
                String gender = nurseData[5].trim();

                nurse newNurse = new nurse(nurseID, name, phoneNo, email, dateOfBirth, gender);

                nurseList.add(newNurse);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return nurseList;
    }
}
