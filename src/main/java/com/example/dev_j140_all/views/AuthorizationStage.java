package com.example.dev_j140_all.views;

import com.example.dev_j140_all.StartApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthorizationStage extends Stage {

    private Scene registrationScene;
    private Scene loginScene;

    public AuthorizationStage() {
        init();
    }

    private void init() {
        setTitle("Authorization");
        initRegistrationScene();
        initLoginScene();
        setScene(registrationScene);
    }

    private void initLoginScene() {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("login-scene.fxml"));
        try {
       loginScene = new Scene(fxmlLoader.load(), 500, 400);
        } catch (IOException e) {
            System.out.println("Error: could not load login scene from fxml");
        }
        loginScene.getStylesheets().add(MainStage.DEFAULT_VIEW);
    }

    private void initRegistrationScene() {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("registration-scene.fxml"));
        try {
            registrationScene = new Scene(fxmlLoader.load(), 500, 400);
        } catch (IOException e) {
            System.out.println("Error: could not load registration scene from fxml");
        }
        registrationScene.getStylesheets().add(MainStage.DEFAULT_VIEW);
    }

    public void setLoginScene() {
        setScene(loginScene);
    }

    public void setRegistrationScene() {
        setScene(registrationScene);
    }
}
