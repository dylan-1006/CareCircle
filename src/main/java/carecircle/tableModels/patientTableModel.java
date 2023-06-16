package carecircle.tableModels;

import java.util.List;

import carecircle.classes.patient;
import carecircle.data.patientData;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public class patientTableModel {
    private SimpleStringProperty patientId;
    private SimpleStringProperty patientName;
    private SimpleStringProperty ic;
    private SimpleStringProperty phoneNo;
    private SimpleStringProperty dateOfBirth;
    private SimpleDoubleProperty height;
    private SimpleDoubleProperty weight;
    private SimpleStringProperty bloodType;
    private Button details;

    public patientTableModel(String patientId, String patientName, String ic, String phoneNo, String dateOfBirth,
            double height, double weight, String bloodType) {
        this.patientId = new SimpleStringProperty(patientId);
        this.patientName = new SimpleStringProperty(patientName);
        this.ic = new SimpleStringProperty(ic);
        this.phoneNo = new SimpleStringProperty(phoneNo);
        this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
        this.height = new SimpleDoubleProperty(height);
        this.weight = new SimpleDoubleProperty(weight);
        this.bloodType = new SimpleStringProperty(bloodType);
        this.details = new Button("Details");
        this.details.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-padding: 0 0 5 0px");
    }

    public static ObservableList<patientTableModel> convertPatientDataToPatientDataModel() {

        List<patient> patientList = patientData.loadPatientDataFromDatabase();
        ObservableList<patientTableModel> observablePatientList = FXCollections.observableArrayList();

        for (int i = 0; i < patientList.size(); i++) {

            String patientId = patientList.get(i).getPatientID();
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

    public String getPatientId() {
        return patientId.get();
    }

    public void setPatientId(String patientId) {
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
