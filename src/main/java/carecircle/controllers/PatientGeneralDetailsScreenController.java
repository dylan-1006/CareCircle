package carecircle.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import carecircle.App;
import carecircle.classes.appointment;
import carecircle.classes.patient;
import carecircle.data.appointmentData;
import carecircle.data.patientData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class PatientGeneralDetailsScreenController {

    @FXML
    private Button saveButton;

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

    @FXML
    private Button cancelButton;

    @FXML
    private Pane patientDetailsSidePane;

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
        try {
            lastName.setText(name[1]);
        } catch (Exception e) {
            lastName.setText("-");

        }
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
    void deleteThisPatient(ActionEvent event) throws IOException {

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Are you sure you want to proceed?");
        confirmation.setContentText("Click OK to continue or Cancel to abort.");

        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.get() == ButtonType.OK) {

            patientData.deletePatient(patientData.initPatientData.getPatientID());
            App.setRoot("patientScreenGeneral");
        } else {

            App.setRoot("patientGeneralDetailsScreen");
        }

    }

    @FXML
    void editPatientDetails(ActionEvent event) {

        // Setting details to be editable
        firstName.setEditable(true);
        lastName.setEditable(true);
        dateOfBirth.setEditable(true);
        personalDetailsGender.setEditable(true);
        bloodType2.setEditable(true);
        contactNo.setEditable(true);
        emailAddress.setEditable(true);
        height.setEditable(true);
        weight.setEditable(true);

        // Changing background colour of textfield
        firstName.setStyle("-fx-control-inner-background: #F6F6F6");
        lastName.setStyle("-fx-control-inner-background: #F6F6F6;");
        dateOfBirth.setStyle("-fx-control-inner-background: #F6F6F6;");
        personalDetailsGender.setStyle("-fx-control-inner-background: #F6F6F6;");
        bloodType2.setStyle("-fx-control-inner-background: #F6F6F6;");
        contactNo.setStyle("-fx-control-inner-background: #F6F6F6;");
        emailAddress.setStyle("-fx-control-inner-background: #F6F6F6;");
        height.setStyle("-fx-control-inner-background: #F6F6F6;");
        weight.setStyle("-fx-control-inner-background: #F6F6F6;");

        // Set save button to be visible
        saveButton.setVisible(true);

        // Set cancel button to be visible
        cancelButton.setVisible(true);

        // Set edit button to be not visible
        editButton.setVisible(false);

    }

    @FXML
    void cancelEditPatient(ActionEvent event) throws IOException {
        App.setRoot("patientGeneralDetailsScreen");

    }

    @FXML
    void savePatientDetails(ActionEvent event) throws IOException {

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Are you sure you want to proceed?");
        confirmation.setContentText("Click OK to continue or Cancel to abort.");

        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.get() == ButtonType.OK) {

            List<patient> patientList = patientData.loadPatientDataFromDatabase();

            for (int i = 0; i < patientList.size(); i++) {

                if (patientList.get(i).getPatientID().equals(patientData.initPatientData.getPatientID())) {

                    // Setting the updated details
                    patientList.get(i).setName(firstName.getText() + " " + lastName.getText());
                    patientList.get(i).setDateOfBirth(dateOfBirth.getText());
                    patientList.get(i).setGender(personalDetailsGender.getText());
                    patientList.get(i).setBloodType(bloodType2.getText());
                    patientList.get(i).setPhoneNo(contactNo.getText());
                    patientList.get(i).setEmail(emailAddress.getText());

                    // Special formatting and conversion to dobule required for height and weight
                    double editedHeight = Double.parseDouble(height.getText().replace("cm", ""));
                    double editedWeight = Double.parseDouble(weight.getText().replace("kg", ""));

                    // Setting updated & formatted details for height and weight
                    patientList.get(i).setHeight(editedHeight);
                    patientList.get(i).setWeight(editedWeight);

                    break;
                }

            }

            String patientId = patientData.initPatientData.getPatientID();
            //below is writing into the file
            try (FileWriter account = new FileWriter(
                    "src/main/resources/carecircle/assets/database/patient.txt",
                    false)) {
                PrintWriter accountWriter = new PrintWriter(account);

                for (int i = 0; i < patientList.size(); i++) {

                    accountWriter.println(
                            patientList.get(i).getPatientID() + "," + patientList.get(i).getName() + ","
                                    + patientList.get(i).getIc() + ","
                                    + patientList.get(i).getPhoneNo() + ","
                                    + patientList.get(i).getEmail() + ","
                                    + patientList.get(i).getDateOfBirth()
                                    + "," + patientList.get(i).getGender()
                                    + ","
                                    + patientList.get(i).getAddress() + "," + patientList.get(i).getHeight() + ","
                                    + patientList.get(i).getWeight() + ","
                                    + patientList.get(i).getBloodType());

                }
                accountWriter.close();
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Patient Edited!");
                alert.setHeaderText("Patient record has been edited");
                alert.showAndWait();

                App.setRoot("patientScreenGeneral");

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

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
