package com.example.dev_j140_all.event_hadlers;

import com.example.dev_j140_all.views.MainStage;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class CustomViewEventHandler implements EventHandler {

    private MainStage stage;
    public CustomViewEventHandler(MainStage stage) {
        this.stage=stage;
    }

    @Override
    public void handle(Event event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select onw css stylesheet");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null && file.getPath().endsWith(".css")) {
            String currentStylesheetPath = "file:/" + file.getAbsolutePath().replaceAll("\\\\", "/");
            stage.setCurrentStylesheetPath(currentStylesheetPath);
            stage.getScene().getStylesheets().clear();
            stage.getScene().getStylesheets().add(currentStylesheetPath);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setContentText("Selected file error");
            alert.show();
        }
    }
}
