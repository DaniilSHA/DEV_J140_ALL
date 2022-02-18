package com.example.dev_j140_all.models;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
        initRegistrationScene();
        initLoginScene();
        setScene(registrationScene);
    }

    private void initLoginScene() {
        Button switchToRegistrationModeButton = new Button("go to registration page");
        switchToRegistrationModeButton.setOnAction( (e) -> {
            this.setScene(registrationScene);
        });

        Label info = new Label("Please login");

        HBox login = new HBox();
        login.setAlignment(Pos.CENTER);
        login.setSpacing(20);
        Label loginLabel = new Label("User name: ");
        TextField loginField = new TextField();
        login.getChildren().addAll(loginLabel, loginField);

        HBox password = new HBox();
        password.setAlignment(Pos.CENTER);
        password.setSpacing(20);
        Label passwordLabel = new Label("Password: ");
        PasswordField passwordField = new PasswordField();
        password.getChildren().addAll(passwordLabel, passwordField);

        Button makeRegistrationButton = new Button("make login");

        VBox mainPane = new VBox();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setSpacing(30);
        mainPane.getChildren().addAll(switchToRegistrationModeButton, info, login, password, makeRegistrationButton);

        loginScene = new Scene(mainPane, 400, 300);
    }

    private void initRegistrationScene() {
        Button switchToLoginModeButton = new Button("go to login page");
        switchToLoginModeButton.setOnAction( (e) -> {
            this.setScene(loginScene);
        });
        Label info = new Label("Please create you account ");

        HBox login = new HBox();
        login.setAlignment(Pos.CENTER);
        login.setSpacing(20);
        Label loginLabel = new Label("User name: ");
        TextField loginField = new TextField();
        login.getChildren().addAll(loginLabel, loginField);

        HBox password = new HBox();
        password.setAlignment(Pos.CENTER);
        password.setSpacing(20);
        Label passwordLabel = new Label("Password: ");
        PasswordField passwordField = new PasswordField();
        password.getChildren().addAll(passwordLabel, passwordField);

        Button makeRegistrationButton = new Button("make registration");

        VBox mainPaneRegistrationScene = new VBox();
        mainPaneRegistrationScene.setAlignment(Pos.CENTER);
        mainPaneRegistrationScene.setSpacing(30);
        mainPaneRegistrationScene.getChildren().addAll(switchToLoginModeButton, info, login, password, makeRegistrationButton);

        registrationScene = new Scene(mainPaneRegistrationScene, 400, 300);
    }
}
