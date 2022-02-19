module com.example.dev_j140_all {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;


    opens com.example.dev_j140_all to javafx.fxml;
    exports com.example.dev_j140_all;
}