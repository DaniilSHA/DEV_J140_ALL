module com.example.dev_j140_all {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires javafx.base;
    requires javafx.graphics;


    opens com.example.dev_j140_all to javafx.fxml, javafx.base, javafx.controls, javafx.graphics;
    opens com.example.dev_j140_all.models to javafx.base;
    exports com.example.dev_j140_all;
}