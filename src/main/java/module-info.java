module org.example.ssnake {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;

    opens org.example.ssnake to javafx.fxml;
    exports org.example.ssnake;
}