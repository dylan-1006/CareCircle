package carecircle.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import carecircle.App;
import carecircle.classes.doctor;
import carecircle.classes.patient;
import carecircle.classes.analysis;
import carecircle.data.doctorData;
import carecircle.data.patientData;
import carecircle.data.analysisData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddAnalysisScreenController {

    @FXML
    private Button Continue;

    @FXML
    private Text backToAnalysisButton;

    @FXML
    private DatePicker date;

    @FXML
    private TextField description;

    @FXML
    private ComboBox<String> doctorID;

    @FXML
    private ComboBox<String> patientNameBox;

    @FXML
    public void initialize() {

        // Set combox box selection items
        doctorID.setItems(fetchAvailableDoctorId());
        patientNameBox.setItems(fetchAvailablePatientName());
    }


    //addNewAnalysis
    @FXML
    void addNewAnalysis(ActionEvent event) {

        // Make sure all required details are filled in
        if (patientNameBox.getSelectionModel().isEmpty() ||
                doctorID.getSelectionModel().isEmpty() || date.getValue().toString().equals("")
                || description.getText().equals("event")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Please fill in all the required fields.");
            alert.showAndWait();

        } else {

            try {
                List<analysis> analysisList = analysisData.loadAnalysisDataFromDatabase();

                // Generate new id based on last id in the database
                int newAnalysisID = Integer
                        .parseInt(analysisList.get(analysisList.size() - 1)
                                .getAnalysisID()
                                .substring(1))
                        + 1;

                String newAnalysisIdFormatted = String.format("AN0%2d", newAnalysisID);

                List<patient> patientList = patientData.loadPatientDataFromDatabase();

                String patientId = " ";
                for (int i = 0; i < patientList.size(); i++) {

                    if (patientList.get(i).getName().equals(patientNameBox.getSelectionModel()
                            .getSelectedItem().toString())) {

                        patientId = patientList.get(i).getPatientID();
                        break;
                    }

                }

                // Get filled in details
                analysis newAnalysis = new analysis(newAnalysisIdFormatted,
                        doctorID.getSelectionModel().getSelectedItem().toString(),
                        patientData.initPatientData.getPatientID(),
                        date.getValue().toString(),
                        description.getText());


                        //below is writing into the file
                FileWriter account = new FileWriter(
                        "src/main/resources/carecircle/assets/database/analysis.txt", true);

                // Append new data to the .txt file
                PrintWriter accountWriter = new PrintWriter(account);
                accountWriter.println(
                        newAnalysisIdFormatted + "," + newAnalysis.getDoctorID() + "," + newAnalysis.getPatientID()
                                + "," + newAnalysis.getDate() + "," + newAnalysis.getDescription());

                accountWriter.close();
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("New Analysis Added!");
                alert.setHeaderText("New analysis has been added");
                alert.showAndWait();

                App.setRoot("patientDetailsScreenAnalysis");

            } catch (Exception e) {

            }
        }

    }

    ObservableList<String> fetchAvailableDoctorId() {

        // Get data from database
        List<doctor> doctorList = doctorData.loadDoctorDataFromDatabase();
        ObservableList<String> doctorIdOptions = FXCollections.observableArrayList("Choose doctor ID");

        for (int i = 0; i < doctorList.size(); i++) {

            doctorIdOptions.add(doctorList.get(i).getDoctorID());

        }

        return doctorIdOptions;
    }

    ObservableList<String> fetchAvailablePatientName() {

        // Get data from database
        List<patient> patientList = patientData.loadPatientDataFromDatabase();
        ObservableList<String> patientIdOptions = FXCollections.observableArrayList("Choose patient name");

        for (int i = 0; i < patientList.size(); i++) {

            patientIdOptions.add(patientList.get(i).getName());

        }

        return patientIdOptions;
    }

    @FXML
    void backToAnalysisScreen(MouseEvent event) throws IOException {

        // Return to previous screen
        App.setRoot("patientDetailsScreenAnalysis");
    }
}
