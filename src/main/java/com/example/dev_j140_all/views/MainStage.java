package com.example.dev_j140_all.views;

import com.example.dev_j140_all.StartApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainStage extends Stage {

    public static String DEFAULT_VIEW = "/default_view.css";
    private Scene mainScene;
    private String currentStylesheetPath;

    public MainStage() {
        currentStylesheetPath = DEFAULT_VIEW;
        init();
    }

    private void init() {
        setTitle("Database reader");
        initMainScene();
        this.getScene().getStylesheets().add(currentStylesheetPath);
        show();
    }

    private void initMainScene() {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("main-scene.fxml"));
        try {
            mainScene = new Scene(fxmlLoader.load(), 1000, 1000);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: could not load main scene from fxml");
        }
        setScene(mainScene);
    }

    public void setCurrentStylesheetPath(String currentStylesheetPath) {
        this.currentStylesheetPath = currentStylesheetPath;
    }

    public String getCurrentStylesheetPath() {
        return currentStylesheetPath;
    }
}
