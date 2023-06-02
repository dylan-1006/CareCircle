module carecircle {
    requires javafx.controls;
    requires javafx.fxml;

    opens carecircle to javafx.fxml;
    exports carecircle;
}
