package carecircle.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import carecircle.classes.appointment;

public class appointmentData {
    public static void main(String[] args) {
        loadAppointmentDataFromDatabase();

    }

    public static String fileName = "src/main/resources/carecircle/assets/database/appointment.txt";

    public static List<appointment> loadAppointmentDataFromDatabase() {
        List<appointment> appointmentList = new ArrayList<>();

        try (

                BufferedReader reader = new BufferedReader(new FileReader(fileName))

        ) {
            String newLine;
            while ((newLine = reader.readLine()) != null) {
                String[] appointmentData = newLine.split(",");
                String appointmentId = appointmentData[0].trim();
                String patientId = appointmentData[1].trim();
                String doctorId = appointmentData[2].trim();
                String date = appointmentData[3].trim();
                String time = appointmentData[4].trim();
                String venue = appointmentData[5].trim();
                String department = appointmentData[6].trim();

                appointment newAppointment = new appointment(appointmentId, patientId, doctorId, date, 
                time, venue, department);

                appointmentList.add(newAppointment);

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return appointmentList;
    }
    public static appointment initAppointmentData = new appointment(" ", " ", " ", " ", " ", " ", " ");
}
