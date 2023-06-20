package carecircle.controllers;

import java.io.IOException;
import java.util.List;

import carecircle.App;
import carecircle.classes.doctor;
import carecircle.data.doctorData;
import carecircle.data.medicineData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    }

    ObservableList<String> fetchAvailableDoctorId() {

        List<doctor> doctorList = doctorData.loadDoctorDataFromDatabase();
        ObservableList<String> doctorIdOptions = FXCollections.observableArrayList("Choose doctor ID");

        for (int i = 0; i < doctorList.size(); i++) {

            doctorIdOptions.add(doctorList.get(i).getDoctorID());

        }

        return doctorIdOptions;
    }

}
