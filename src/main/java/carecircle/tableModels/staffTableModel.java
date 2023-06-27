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
    private SimpleStringProperty staffID;
    private SimpleStringProperty name;
    private SimpleStringProperty phoneNo;
    private SimpleStringProperty email;
    private SimpleStringProperty dateOfBirth;
    private SimpleStringProperty gender;
    private SimpleStringProperty department;
    private Button details;

    public staffTableModel(String staffID, String name, String phoneNo, String email, String dateOfBirth, String gender,
            String department) {
        this.staffID = new SimpleStringProperty(staffID);
        this.name = new SimpleStringProperty(name);
        this.phoneNo = new SimpleStringProperty(phoneNo);
        this.email = new SimpleStringProperty(email);
        this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
        this.gender = new SimpleStringProperty(gender);
        this.department = new SimpleStringProperty(department);
        this.details = new Button("Details");
        this.details.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-padding: 0 0 5 0px");
    }

    public static ObservableList<staffTableModel> convertStaffDataToStaffDataModel() {

        // Load both doctor and nurse data from database
        List<doctor> doctorList = doctorData.loadDoctorDataFromDatabase();
        List<nurse> nurseList = nurseData.loadNurseDataFromDatabase();
        ObservableList<staffTableModel> observableStaffList = FXCollections.observableArrayList();

        for (int i = 0; i < doctorList.size(); i++) {

            String doctorID = doctorList.get(i).getDoctorID();
            String name = doctorList.get(i).getName();
            String phoneNo = doctorList.get(i).getPhoneNo();
            String email = doctorList.get(i).getEmail();
            String dateOfBirth = doctorList.get(i).getDateOfBirth();
            String gender = doctorList.get(i).getGender();
            String department = "Doctor";

            staffTableModel staffDoctorTableModel = new staffTableModel(doctorID, name, phoneNo, email, dateOfBirth,
                    gender,
                    department);

            // Add all the doctor data that is converted into staffTableModel into the list
            observableStaffList.add(staffDoctorTableModel);

        }

        for (int i = 0; i < nurseList.size(); i++) {

            String nurseID = nurseList.get(i).getNurseID();
            String name = nurseList.get(i).getName();
            String phoneNo = nurseList.get(i).getPhoneNo();
            String email = nurseList.get(i).getEmail();
            String dateOfBirth = nurseList.get(i).getDateOfBirth();
            String gender = nurseList.get(i).getGender();
            String department = "Nurse";

            staffTableModel staffNurseTableModel = new staffTableModel(nurseID, name, phoneNo, email, dateOfBirth,
                    gender,
                    department);

            // Add all the nurse data that is converted into staffTableModel into the list after doctor data
            observableStaffList.add(staffNurseTableModel);

        }

        return observableStaffList;

    }

    // The following are getters and setters that get n set the data of this class
    public String getStaffID() {
        return staffID.get();
    }

    public SimpleStringProperty staffIDProperty() {
        return staffID;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo.get();
    }

    public SimpleStringProperty phoneNoProperty() {
        return phoneNo;
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public String getDateOfBirth() {
        return dateOfBirth.get();
    }

    public SimpleStringProperty dateOfBirthProperty() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public String getDepartment() {
        return department.get();
    }

    public SimpleStringProperty departmentProperty() {
        return department;
    }

    public Button getDetails() {
        return details;
    }

    public void setDetails(Button details) {
        this.details = details;
    }
}
