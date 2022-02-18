package com.example.dev_j140_all.models;

import com.example.dev_j140_all.event_hadlers.ValidateRegistrationEventHandler;
import javafx.geometry.Pos;
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
        Label statusMessage = new Label();

        Button switchToRegistrationModeButton = new Button("go to registration page");
        switchToRegistrationModeButton.setOnAction((e) -> {
            setRegistrationScene();
        });

        Label info = new Label("Welcome to login page. Please login");

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
        mainPane.getChildren().addAll(switchToRegistrationModeButton, info, login, password, statusMessage,  makeRegistrationButton);

        loginScene = new Scene(mainPane, 400, 300);
    }

    private void initRegistrationScene() {
        Label statusMessage = new Label();

        Button switchToLoginModeButton = new Button("go to login page");
        switchToLoginModeButton.setOnAction((e) -> {
            setLoginScene();
            statusMessage.setText("");
        });
        Label info = new Label("Welcome to registration page. Please create you account ");

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
        makeRegistrationButton.setOnAction(new ValidateRegistrationEventHandler(this, statusMessage, loginField, passwordField));

        VBox mainPaneRegistrationScene = new VBox();
        mainPaneRegistrationScene.setAlignment(Pos.CENTER);
        mainPaneRegistrationScene.setSpacing(30);
        mainPaneRegistrationScene.getChildren().addAll(switchToLoginModeButton, info, login, password, statusMessage, makeRegistrationButton);

        registrationScene = new Scene(mainPaneRegistrationScene, 400, 300);
    }

    public void setLoginScene(){
        setScene(loginScene);
    }

    public void setRegistrationScene(){
        setScene(registrationScene);
    }
}
