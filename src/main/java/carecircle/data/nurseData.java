package carecircle.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import carecircle.classes.nurse;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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

    public static void deleteData(String nurseID) throws IOException {

        List<nurse> nurseList = nurseData.loadNurseDataFromDatabase();
        for (int i = 0; i < nurseList.size(); i++) {
            if (nurseList.get(i).getNurseID().equals(nurseID)) {

                nurseList.remove(i);
                break;

            }

        }

        try (FileWriter account = new FileWriter(
                "src/main/resources/carecircle/assets/database/nurse.txt",
                false)) {
            PrintWriter accountWriter = new PrintWriter(account);

            for (int i = 0; i < nurseList.size(); i++) {

                accountWriter.println(
                        nurseList.get(i).getNurseID() + "," + nurseList.get(i).getName() + ","
                                + nurseList.get(i).getPhoneNo() + ","
                                + nurseList.get(i).getEmail() + ","
                                + nurseList.get(i).getDateOfBirth()
                                + "," + nurseList.get(i).getGender());
            }
            accountWriter.close();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Nurse Deleted!");
            alert.setHeaderText("Nurse record has been deleted");
            alert.showAndWait();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static nurse initNurseData = new nurse(" ", " ", " ", " ", " ", " ");

}