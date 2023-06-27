package carecircle.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import carecircle.classes.patient;
import carecircle.classes.user;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class userData {

    public static String fileName = "src/main/resources/carecircle/assets/database/user.txt";

    public static List<user> loadUserDataFromDatabase() {
        List<user> userList = new ArrayList<>();

        try (

                BufferedReader reader = new BufferedReader(new FileReader(fileName))

        ) {
            String newLine;
            while ((newLine = reader.readLine()) != null) {

                // Read data from .txt file
                String[] userData = newLine.split(",");
                String name = userData[0].trim();
                String userName = userData[1].trim();
                String userPassword = userData[2].trim();

                user newUser = new user(name, userName, userPassword);
                userList.add(newUser);

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return userList;
    }

    public static void deleteUser(String userName) throws IOException {

        List<user> userList = userData.loadUserDataFromDatabase();
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(userName)) {

                // Delete selected user from list
                userList.remove(i);
                break;

            }

        }

        try (FileWriter account = new FileWriter(
                "src/main/resources/carecircle/assets/database/user.txt",
                false)) {
            PrintWriter accountWriter = new PrintWriter(account);

            for (int i = 0; i < userList.size(); i++) {

                // Rewrite newly edited data into .txt file
                accountWriter.println(
                        userList.get(i).getName() + "," + userList.get(i).getUsername() + ","
                                + userList.get(i).getPassword());

            }
            accountWriter.close();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("User Deleted!");
            alert.setHeaderText("User record has been deleted");
            alert.showAndWait();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // Initialise an user object tht can be used for reference elsewhere
    public static user initUserData = new user(" ", null, null);

}
