package carecircle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.SocketTimeoutException;

import carecircle.data.patientData;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {


        scene = new Scene(loadFXML("loginScreen"), 1200, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("CareCircle");
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        System.out.println("set root to " + fxml + " successfully");
    }

    public static Parent loadFXML(String fxml) throws IOException {
        System.out.println(App.class.getClassLoader());
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("assets/fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();

    }

}