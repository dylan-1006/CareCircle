package carecircle.tableModels;

import java.util.List;

import carecircle.classes.medicine;
import carecircle.data.medicineData;
import carecircle.data.patientData;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public class patientMedicineTableModel {

    private SimpleStringProperty medicineID;
    private SimpleStringProperty patientId;
    private SimpleStringProperty doctorId;
    private SimpleStringProperty medicineName;
    private SimpleDoubleProperty quantity;
    private SimpleDoubleProperty dosage;

    public patientMedicineTableModel(String medicineID, String patientId, String doctorId, String medicineName,
            double quantity, double dosage) {
        this.medicineID = new SimpleStringProperty(medicineID);
        this.patientId = new SimpleStringProperty(patientId);
        this.doctorId = new SimpleStringProperty(doctorId);
        this.medicineName = new SimpleStringProperty(medicineName);
        this.quantity = new SimpleDoubleProperty(quantity);
        this.dosage = new SimpleDoubleProperty(dosage);
    }

    public static ObservableList<patientMedicineTableModel> convertSelectedPatientMedicineDataToModel() {

        List<medicine> medicineList = medicineData.loadMedicineDataFromDatabase();
        ObservableList<patientMedicineTableModel> observableMedicineList = FXCollections.observableArrayList();

        for (int i = 0; i < medicineList.size(); i++) {

            if (medicineList.get(i).getPatientId().equals(patientData.initPatientData.getPatientID())) {

                String medicineID = medicineList.get(i).getMedicineID();
                String patientId = medicineList.get(i).getPatientId();
                String doctorId = medicineList.get(i).getDoctorId();
                String medicineName = medicineList.get(i).getMedicineName();
                double quantity = medicineList.get(i).getQuantity();
                double dosage = medicineList.get(i).getDosage();

                patientMedicineTableModel medicineTableModel = new patientMedicineTableModel(medicineID, patientId,
                        doctorId, medicineName, quantity, dosage);

                observableMedicineList.add(medicineTableModel);

            }

        }
        return observableMedicineList;

    }

    public String getMedicineID() {
        return medicineID.get();
    }

    public SimpleStringProperty medicineIDProperty() {
        return medicineID;
    }

    public void setMedicineID(String medicineID) {
        this.medicineID.set(medicineID);
    }

    public String getPatientId() {
        return patientId.get();
    }

    public SimpleStringProperty patientIdProperty() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId.set(patientId);
    }

    public String getDoctorId() {
        return doctorId.get();
    }

    public SimpleStringProperty doctorIdProperty() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId.set(doctorId);
    }

    public String getMedicineName() {
        return medicineName.get();
    }

    public SimpleStringProperty medicineNameProperty() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName.set(medicineName);
    }

    public int getQuantity() {
        return (int) quantity.get();
    }

    public SimpleDoubleProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity.set(quantity);
    }

    public double getDosage() {
        return dosage.get();
    }

    public SimpleDoubleProperty dosageProperty() {
        return dosage;
    }

    public void setDosage(double dosage) {
        this.dosage.set(dosage);
    }
}