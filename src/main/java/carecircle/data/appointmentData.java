package carecircle.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import carecircle.classes.appointment;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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


    public static void deleteAppointment(String appointmentID) throws IOException {

        List<appointment> appointmentList = appointmentData.loadAppointmentDataFromDatabase();
        for (int i = 0; i < appointmentList.size(); i++) {
            if (appointmentList.get(i).getAppointmentID().equals(appointmentID)) {

                appointmentList.remove(i);
                break;

            }

        }

        try (FileWriter account = new FileWriter(
                "src/main/resources/carecircle/assets/database/appointment.txt",
                false)) {
            PrintWriter accountWriter = new PrintWriter(account);

            for (int i = 0; i < appointmentList.size(); i++) {

                accountWriter.println(
                        appointmentList.get(i).getAppointmentID() + "," + appointmentList.get(i).getPatientID() + ","
                                + appointmentList.get(i).getDoctorID() + ","
                                + appointmentList.get(i).getDate() + ","
                                + appointmentList.get(i).getTime() + ","
                                + appointmentList.get(i).getVenue()
                                + "," + appointmentList.get(i).getDepartment());
                               

            }
            accountWriter.close();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Appointment Deleted!");
            alert.setHeaderText("Appointment record has been deleted");
            alert.showAndWait();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    public static appointment initAppointmentData = new appointment(" ", " ", " ", " ", " ", " ", " ");
}
