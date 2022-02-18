package com.example.dev_j140_all.models;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AuthorizationStage extends Stage {

    private Scene registrationScene;
    private Scene loginScene;

    public AuthorizationStage () {
        init();
    }

    private void init() {
        setTitle("Authorization");

        HBox top = new HBox();
        Button registrationButton = new Button("registration");
        Button loginButton = new Button("login");
        top.getChildren().addAll(registrationButton, loginButton);


        VBox mainPane = new VBox();
        mainPane.getChildren().add(top);

    }
}
