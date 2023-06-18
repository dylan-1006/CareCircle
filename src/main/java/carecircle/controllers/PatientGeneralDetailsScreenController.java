package carecircle.controllers;

import java.io.IOException;
import java.time.Year;
import java.util.List;

import carecircle.App;
import carecircle.classes.appointment;
import carecircle.data.appointmentData;
import carecircle.data.patientData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class PatientGeneralDetailsScreenController {

    @FXML
    private ImageView backButton;

    @FXML
    private Pane appointmentPane;

    @FXML
    private TextField age;

    @FXML
    private Button analysisButton;

    @FXML
    private TextField appointmentID;

    @FXML
    private TextField bloodType;

    @FXML
    private TextField bloodType2;

    @FXML
    private TextField contactNo;

    @FXML
    private TextField date;

    @FXML
    private TextField dateOfBirth;

    @FXML
    private Button deletePatientButton;

    @FXML
    private Button diagnosisButton;

    @FXML
    private TextField doctorID;

    @FXML
    private Button editButton;

    @FXML
    private TextField emailAddress;

    @FXML
    private TextField firstName;

    @FXML
    private TextField gender;

    @FXML
    private TextField personalDetailsGender;

    @FXML
    private TextField height;

    @FXML
    private Button historyButton;

    @FXML
    private TextField lastName;

    @FXML
    private TextArea notes;

    @FXML
    private TextField patientID;

    @FXML
    private TextField patientName;

    @FXML
    private TextFlow textFlowTitle;

    @FXML
    private TextField time;

    @FXML
    private Text titleText1;

    @FXML
    private Text titleText2;

    @FXML
    private Button treatmentButton;

    @FXML
    private TextField venue;

    @FXML
    private TextField weight;

    public void initialize() {
        setSideBarPatientDetails();
        setGeneralPatientDetails();
        setPatientAppointmentDetails();
    }

    void setSideBarPatientDetails() {

        // Calculating the patient age
        int year = Year.now().getValue();
        int patientAge = year - Integer.parseInt(patientData.initPatientData.getDateOfBirth().substring(0, 4));

        // Setting the patient details
        patientName.setText(patientData.initPatientData.getName());
        patientID.setText(patientData.initPatientData.getPatientID());
        gender.setText(patientData.initPatientData.getGender());
        age.setText(Integer.toString(patientAge));
        height.setText(Double.toString(patientData.initPatientData.getHeight()) + "cm");
        weight.setText(Double.toString(patientData.initPatientData.getWeight()) + "kg");
        bloodType.setText(patientData.initPatientData.getBloodType());

    }

    void setGeneralPatientDetails() {

        // Splitting first & last name
        String[] name = patientData.initPatientData.getName().split(" ");

        // Setting name
        lastName.setText(name[1]);
        firstName.setText(name[0]);

        // Setting rest of the details
        dateOfBirth.setText(patientData.initPatientData.getDateOfBirth());
        personalDetailsGender.setText(patientData.initPatientData.getGender());
        bloodType2.setText(patientData.initPatientData.getBloodType());
        contactNo.setText(patientData.initPatientData.getPhoneNo());
        emailAddress.setText(patientData.initPatientData.getEmail());

    }

    void setPatientAppointmentDetails() {

        List<appointment> appointmentList = appointmentData.loadAppointmentDataFromDatabase();

        for (int i = 0; i < appointmentList.size(); i++) {

            if (appointmentList.get(i).getPatientID().equals(patientData.initPatientData.getPatientID())) {
                System.out.println("Patient Found");

                appointmentPane.setVisible(false);

                appointmentID.setText(appointmentList.get(i).getAppointmentID());
                date.setText(appointmentList.get(i).getDate());
                time.setText(appointmentList.get(i).getTime());
                doctorID.setText(appointmentList.get(i).getDoctorID());
                venue.setText(appointmentList.get(i).getVenue());

                break;

            } else {
                appointmentPane.setVisible(true);

            }

        }

    }

    @FXML
    void editPatientDetails(ActionEvent event) {

    }

    @FXML
    void switchToAnalysisSection(ActionEvent event) throws IOException {
        App.setRoot("patientDetailsScreenAnalysis");
    }

    @FXML
    void switchToDiagnosisSection(ActionEvent event) throws IOException {
        App.setRoot("patientDetailsScreenDiagnosis");
    }

    @FXML
    void switchToHistorySection(ActionEvent event) throws IOException {
        App.setRoot("patientDetailsScreenMedicalHistory");
    }

    @FXML
    void switchToTreatmentSection(ActionEvent event) throws IOException {
        App.setRoot("patientDetailsScreenTreatment");
    }

    @FXML
    void switchToPatient(MouseEvent event) throws IOException {

        App.setRoot("patientScreenGeneral");

    }

}
