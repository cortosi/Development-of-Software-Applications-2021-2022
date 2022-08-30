module com.unito.catering {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.unito.catering.ui to javafx.fxml;
    opens com.unito.catering.ui.menu to javafx.fxml;
    opens com.unito.catering to javafx.fxml;
    exports com.unito.catering;
}