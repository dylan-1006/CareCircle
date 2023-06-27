package carecircle.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import carecircle.classes.medicine;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class medicineData {
    public static void main(String[] args) {
        loadMedicineDataFromDatabase();
    }

    public static String fileName = "src/main/resources/carecircle/assets/database/medicine.txt";

    public static List<medicine> loadMedicineDataFromDatabase() {
        List<medicine> medicineList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String newLine;
            while ((newLine = reader.readLine()) != null) {

                // Reads data from .txt file
                String[] medicineData = newLine.split(",");
                String medicineID = medicineData[0].trim();
                String patientId = medicineData[1].trim();
                String doctorId = medicineData[2].trim();
                String medicineName = medicineData[3].trim();
                int quantity = Integer.parseInt(medicineData[4].trim());
                double dosage = Double.parseDouble(medicineData[5].trim());

                medicine newMedicine = new medicine(medicineID, patientId, doctorId, medicineName, quantity, dosage);

                medicineList.add(newMedicine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return medicineList;
    }

    public static void deleteMedicine(String medicineID) {

        List<medicine> medicineList = medicineData.loadMedicineDataFromDatabase();

        for (int i = 0; i < medicineList.size(); i++) {
            if (medicineList.get(i).getMedicineID().equals(medicineID)) {

                // Remove selected data
                medicineList.remove(i);
                break;
            }
        }

        try (FileWriter fileWriter = new FileWriter("src/main/resources/carecircle/assets/database/medicine.txt",
                false)) {
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (int i = 0; i < medicineList.size(); i++) {
                medicine medicine = medicineList.get(i);

                // Rewrite newly edited data into .txt file
                printWriter.println(
                        medicine.getMedicineID() + "," +
                                medicine.getPatientId() + "," + medicine.getDoctorId() + ","
                                + medicine.getMedicineName() + ","
                                + medicine.getQuantity() + ","
                                + medicine.getDosage());
            }

            printWriter.close();

            // Alert that deletion was succesful
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Medicine Deleted!");
            alert.setHeaderText("Medicine record has been deleted");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Initialise a medicine object that can be referenced elsewhere later
    public static medicine initMedicineData = new medicine(fileName, fileName, fileName, fileName, 0, 0);
}
