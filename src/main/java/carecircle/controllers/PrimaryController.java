package carecircle.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import carecircle.App;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
