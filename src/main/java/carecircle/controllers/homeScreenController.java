package carecircle.controllers;

<<<<<<< HEAD

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
=======
import java.io.IOException;

import carecircle.App;
import carecircle.classes.user;
import carecircle.data.userData;
import javafx.application.Preloader.StateChangeNotification;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
>>>>>>> 31b78fa4c790a517fa4fd0ff44b18934c04d95fe
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class HomeScreenController {

    @FXML
    private static TextField homeScreenUserName;

    @FXML
    private Pane backgroundPane;

    @FXML
    private Pane navigationSideBar;

    @FXML
    private Pane sideBarAppointmentButton;

    @FXML
    private Pane sideBarDashboardButton;

    @FXML
    private Pane sideBarHelpCenterButton;

    @FXML
    private Pane sideBarLogoutButton;

    @FXML
    private Pane sideBarMedicalStuffButton;

    @FXML
    private Pane sideBarPatientButton;

    @FXML
    private Pane sideBarSettingsButton;

    @FXML
    private VBox menuBar;

    @FXML
    private TextFlow textFlowTitle;

    @FXML
    private Text titleText1;

    @FXML
    private Text titleText2;

    @FXML
<<<<<<< HEAD
    private TableView<?> patientTable;


    @FXML
    public void initialize(){
        
       
=======
    public void initialize() {
        System.out.println(userData.initUserData.name);
        homeScreenUserName.setText("name2212");
    }

    @FXML
    public void setUsername(String username) {
        homeScreenUserName.setText(username);
    }

    @FXML
    void switchToAppoitmentScreen(MouseEvent event) throws IOException {
        App.setRoot("appointmentScreenGeneral");

    }

    @FXML
    void switchToHelpCenterScreen(MouseEvent event) throws IOException {
        App.setRoot("helpCenterScreen");

    }

    @FXML
    void switchToHomeScreen(MouseEvent event) throws IOException {
        App.setRoot("homeScreen");

    }

    @FXML
    void switchToLoginScreen(MouseEvent event) throws IOException {
        App.setRoot("loginScreen");

    }

    @FXML
    void switchToMedicalStaffScreen(MouseEvent event) throws IOException {
        App.setRoot("medicalStaffScreenGeneral");

    }

    @FXML
    void switchToPatientScreen(MouseEvent event) throws IOException {
        App.setRoot("patientScreenGeneral");

    }

    @FXML
    void switchToSettingsScreen(MouseEvent event) throws IOException {
        // Have to merge settings screen with master first
        // App.setRoot("appointmentScreenGeneral");

    }

>>>>>>> 31b78fa4c790a517fa4fd0ff44b18934c04d95fe
}
}