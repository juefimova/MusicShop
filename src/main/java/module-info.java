module com.example.music {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;


    opens com.example.music to javafx.fxml;
    exports com.example.music;
}