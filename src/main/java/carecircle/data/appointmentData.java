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

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String newLine;
            while ((newLine = reader.readLine()) != null) {
                String[] appointmentData = newLine.split(",");
                String doctorID = appointmentData[0].trim();
                String venue = appointmentData[1].trim();
                String patientName = appointmentData[2].trim();
                String patientID = appointmentData[3].trim();
                String date = appointmentData[4].trim();
                String time = appointmentData[5].trim();
                String department = appointmentData[6].trim();
                String appointmentID = appointmentData[7].trim();


                appointment newAppointment = new appointment(doctorID, venue, patientName, patientID, date, time,
                        department, appointmentID);

                appointmentList.add(newAppointment);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return appointmentList;
    }
}
