package carecircle.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import carecircle.App;
import carecircle.classes.patient;
import carecircle.classes.user;
import carecircle.data.userData;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class SettingsController {

    @FXML
    private Button saveEditedPassword;

    @FXML
    private Button cancel;

    @FXML
    private Button deleteUser;

    @FXML
    private Text name;

    @FXML
    private Pane navigationSideBar;

    @FXML
    private Button resetPassword;

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
    private TextFlow textFlowTitle;

    @FXML
    private Text titleText1;

    @FXML
    private Text titleText2;

    @FXML
    private TextField userName;

    @FXML
    private TextField userPassword;

    public void initialize() {
        name.setText(userData.initUserData.getName());
        userName.setText(userData.initUserData.getUsername());
        userPassword.setText(userData.initUserData.getPassword());
    }

    @FXML
    void cancelEdit(MouseEvent event) throws IOException {
        App.setRoot("settingsScreen");
    }

    @FXML
    void savePassword(MouseEvent event) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Are you sure you want to proceed?");
        confirmation.setContentText("Click OK to continue or Cancel to abort.");

        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.get() == ButtonType.OK) {

            List<user> userList = userData.loadUserDataFromDatabase();

            for (int i = 0; i < userList.size(); i++) {

                if (userList.get(i).getUsername().equals(userData.initUserData.getUsername())) {

                    // Setting the updated details
                    userList.get(i).setPassword(userPassword.getText());
                    userData.initUserData.setPassword(userPassword.getText());

                    break;
                }

            }

            try (FileWriter account = new FileWriter(
                    "src/main/resources/carecircle/assets/database/user.txt",
                    false)) {
                PrintWriter accountWriter = new PrintWriter(account);

                for (int i = 0; i < userList.size(); i++) {

                    accountWriter.println(
                            userList.get(i).getName() + "," + userList.get(i).getUsername() + ","
                                    + userList.get(i).getPassword());

                }
                accountWriter.close();
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("User Edited!");
                alert.setHeaderText("User record has been edited");
                alert.showAndWait();

                App.setRoot("loginScreen");

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    @FXML
    void deleteUser(MouseEvent event) throws IOException {

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation
                .setHeaderText("Are you sure you want to proceed? You will have to register again to use CareCircle.");
        confirmation.setContentText("Click OK to continue or Cancel to abort.");

        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.get() == ButtonType.OK) {

            userData.deleteUser(userData.initUserData.getUsername());
            App.setRoot("loginScreen");
        } else {

            App.setRoot("settingsScreen");
        }

    }

    @FXML
    void resetPassword(MouseEvent event) {

        resetPassword.setVisible(false);

        saveEditedPassword.setVisible(true);
        cancel.setVisible(true);
        deleteUser.setVisible(false);

        userPassword.setEditable(true);
        userPassword.setStyle("-fx-control-inner-background: #F6F6F6;");

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

        App.setRoot("settingsScreen");

    }

}
