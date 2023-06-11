module carecircle {
    requires javafx.controls;
    requires javafx.fxml;

    opens carecircle.controllers to javafx.fxml;
    opens carecircle.tableModels;
    exports carecircle;
}
