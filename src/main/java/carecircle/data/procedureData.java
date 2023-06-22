package carecircle.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import carecircle.classes.procedure;

public class procedureData {
    public static void main(String[] args) {
        loadProcedureDataFromDatabase();

    }

    public static String fileName = "src/main/resources/carecircle/assets/database/procedure.txt";

    public static List<procedure> loadProcedureDataFromDatabase() {
        List<procedure> procedureList = new ArrayList<>();

        try (

                BufferedReader reader = new BufferedReader(new FileReader(fileName))

        ) {
            String newLine;
            while ((newLine = reader.readLine()) != null) {
                String[] procedureData = newLine.split(",");
                String procedureId = procedureData[0].trim();
                String doctorId = procedureData[1].trim();
                String patientId = procedureData[2].trim();
                String date = procedureData[3].trim();
                String description = procedureData[4].trim();

                procedure newProcedure = new procedure(procedureId, doctorId, patientId, date, description);
                procedureList.add(newProcedure);

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return procedureList;
    }

}
