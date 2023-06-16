package carecircle.tableModels;

import java.util.List;

import carecircle.classes.nurse;
import carecircle.classes.doctor;
import carecircle.data.doctorData;
import carecircle.data.nurseData; 
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public class staffTableModel {
    private SimpleStringProperty doctorID;
    private SimpleStringProperty nurseID;
    private SimpleStringProperty name;
    private SimpleStringProperty phoneNo;
    private SimpleStringProperty email;
    private SimpleStringProperty dateOfBirth;
    private SimpleStringProperty gender;
    private SimpleStringProperty specialization;
    private Button details;

    public staffTableModel(String doctorID, String nurseID, String name, String phoneNo, String email, String dateOfBirth, String gender,
    String specialization) {
        this.doctorID = new SimpleStringProperty(doctorID);
        this.nurseID = new SimpleStringProperty(nurseID);
        this.name = new SimpleStringProperty(name);
        this.phoneNo = new SimpleStringProperty(phoneNo);
        this.email = new SimpleStringProperty(email);
        this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
        this.gender = new SimpleStringProperty(gender);
        this.specialization = new SimpleStringProperty(specialization);
        this.details = new Button("Details");
        this.details.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-padding: 0 0 5 0px");
    }

    public static ObservableList<staffTableModel> convertStaffDataToStaffDataModel() {

        List<doctor> doctorList = doctorData.loadDoctorDataFromDatabase();
        List<nurse> nurseList = nurseData.loadNurseDataFromDatabase();
        ObservableList<staffTableModel> observableStaffList = FXCollections.observableArrayList();

        for (int i = 0; i < doctorList.size(); i++) {

            String doctorID = doctorList.get(i).getPatientID();
            String patientName = patientList.get(i).getName();
            String ic = patientList.get(i).getIc();
            String phoneNo = patientList.get(i).getPhoneNo();
            String dateOfBirth = patientList.get(i).getDateOfBirth();
            double height = patientList.get(i).getHeight();
            double weight = patientList.get(i).getWeight();
            String bloodType = patientList.get(i).getBloodType();

            patientTableModel patientTableModel = new patientTableModel(patientId, patientName, ic,
                    phoneNo,
                    dateOfBirth, height, weight, bloodType);

            observablePatientList.add(patientTableModel);

        }

        return observablePatientList;

    }
    public String getDoctorID() {
        return doctorID.get();
    }

    public void setDoctorID(String patientId) {
        this.patientId.set(patientId);
    }

    public SimpleStringProperty patientIdProperty() {
        return patientId;
    }

    public String getPatientName() {
        return patientName.get();
    }

    public void setPatientName(String patientName) {
        this.patientName.set(patientName);
    }

    public SimpleStringProperty patientNameProperty() {
        return patientName;
    }

    public String getIc() {
        return ic.get();
    }

    public void setIc(String ic) {
        this.ic.set(ic);
    }

    public SimpleStringProperty icProperty() {
        return ic;
    }

    public String getPhoneNo() {
        return phoneNo.get();
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo.set(phoneNo);
    }

    public SimpleStringProperty phoneNoProperty() {
        return phoneNo;
    }

    public String getDateOfBirth() {
        return dateOfBirth.get();
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    public SimpleStringProperty dateOfBirthProperty() {
        return dateOfBirth;
    }

    public double getHeight() {
        return height.get();
    }

    public void setHeight(double height) {
        this.height.set(height);
    }

    public SimpleDoubleProperty heightProperty() {
        return height;
    }

    public double getWeight() {
        return weight.get();
    }

    public void setWeight(double weight) {
        this.weight.set(weight);
    }

    public SimpleDoubleProperty weightProperty() {
        return weight;
    }

    public String getBloodType() {
        return bloodType.get();
    }

    public void setBloodType(String bloodType) {
        this.bloodType.set(bloodType);
    }

    public SimpleStringProperty bloodTypeProperty() {
        return bloodType;
    }

    public Button getDetails() {
        return details;
    }

    public void setDetails(Button details) {
        this.details = details;
    }
}
