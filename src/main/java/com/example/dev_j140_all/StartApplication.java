package com.example.dev_j140_all;

import com.example.dev_j140_all.models.AuthorizationStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        AuthorizationStage authorizationStage = new AuthorizationStage();
        authorizationStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}