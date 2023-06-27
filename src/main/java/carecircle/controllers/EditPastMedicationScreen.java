package carecircle.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import carecircle.App;
import carecircle.classes.doctor;
import carecircle.classes.medicalHistory;
import carecircle.classes.medicine;
import carecircle.data.doctorData;
import carecircle.data.medicineData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class EditPastMedicationScreen {

    @FXML
    private Text backToAppointmentButton;

    @FXML
    private Button continueButton;

    @FXML
    private ComboBox<String> doctorIdComboBox;

    @FXML
    private TextField dosage;

    @FXML
    private TextField medicineName;

    @FXML
    private TextField quantitiy;

    public void initialize() {

        doctorIdComboBox.setItems(fetchAvailableDoctorId());
        setPastMedicationDetails();

    }

    void setPastMedicationDetails() {

        // Setting necessary details
        doctorIdComboBox.setPromptText(medicineData.initMedicineData.getDoctorId());
        medicineName.setText(medicineData.initMedicineData.getMedicineName());
        quantitiy.setText(Integer.toString(medicineData.initMedicineData.getQuantity()));
        dosage.setText(Double.toString(medicineData.initMedicineData.getDosage()));

    }

    @FXML
    void backToAppointmentScreen(MouseEvent event) throws IOException {

        App.setRoot("PatientDetailsScreenMedicalHistory");

    }

    @FXML
    void saveChangesPastMedication(ActionEvent event) {

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Are you sure you want to proceed?");
        confirmation.setContentText("Click OK to continue or Cancel to abort.");

        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.get() == ButtonType.OK) {

            List<medicine> medicineList = medicineData.loadMedicineDataFromDatabase();

            for (int i = 0; i < medicineList.size(); i++) {

                if (medicineList.get(i).getMedicineID()
                        .equals(medicineData.initMedicineData.getMedicineID())) {

                    System.out.println("Entered get text");

                    // Setting the updated details

                    if (doctorIdComboBox.getSelectionModel().getSelectedItem() == null) {

                        medicineList.get(i).setDoctorId(doctorIdComboBox.getPromptText());
                    } else {
                        medicineList.get(i)
                                .setDoctorId(doctorIdComboBox.getSelectionModel().getSelectedItem().toString());
                    }
                    medicineList.get(i).setMedicineName(medicineName.getText());
                    medicineList.get(i).setQuantity(Integer.parseInt(quantitiy.getText()));
                    medicineList.get(i).setDosage(Double.parseDouble(dosage.getText()));

                    break;
                }

            }
            //below is writing the updated data into the file
            try (FileWriter account = new FileWriter(
                    "src/main/resources/carecircle/assets/database/medicine.txt",
                    false)) {
                PrintWriter accountWriter = new PrintWriter(account);

                for (int i = 0; i < medicineList.size(); i++) {

                    accountWriter.println(
                            medicineList.get(i).getMedicineID() + ","
                                    + medicineList.get(i).getPatientId() + ","
                                    + medicineList.get(i).getDoctorId() + ","
                                    + medicineList.get(i).getMedicineName() + ","
                                    + medicineList.get(i).getQuantity() + ","
                                    + medicineList.get(i).getDosage());

                }
                accountWriter.close();
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Past medication Edited!");
                alert.setHeaderText("Past medication record has been edited");
                alert.showAndWait();

                App.setRoot("patientGeneralDetailsScreen");

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    ObservableList<String> fetchAvailableDoctorId() {

        // Fetching necessary data from database
        List<doctor> doctorList = doctorData.loadDoctorDataFromDatabase();
        ObservableList<String> doctorIdOptions = FXCollections.observableArrayList("Choose doctor ID");

        for (int i = 0; i < doctorList.size(); i++) {

            doctorIdOptions.add(doctorList.get(i).getDoctorID());

        }

        return doctorIdOptions;
    }

}
