package com.example.dev_j140_all.views;

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

    private static final int REGISTRATION = 1;
    private static final int LOGIN = 2;
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
        loginScene = createScene(
                "Welcome to login page. Please login",
                "make login",
                "go to registration page",
                AuthorizationStage.REGISTRATION
        );
    }

    private void initRegistrationScene() {
        registrationScene = createScene(
                "Welcome to registration page. Please create you account ",
                "make registration",
                "go to login page",
                AuthorizationStage.LOGIN
        );
    }

    public Scene createScene(String welcomeMessage, String activeButtonMessage, String switchButtonMessage, int sceneMode) {
        Label statusMessage = new Label();
        statusMessage.setMaxWidth(300);
        statusMessage.setWrapText(true);

        Label info = new Label(welcomeMessage);

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

        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(20);
        Button activeButton = new Button(activeButtonMessage);
        Button switchButton = new Button(switchButtonMessage);
        switch (sceneMode) {
            case 1: {
                switchButton.setOnAction((e) -> setRegistrationScene());
                break;
            }
            case 2: {
                activeButton.setOnAction(new ValidateRegistrationEventHandler(this, statusMessage, loginField, passwordField));
                switchButton.setOnAction((e) -> setLoginScene());
                break;
            }
        }

        buttons.getChildren().addAll(activeButton, switchButton);

        VBox mainPane = new VBox();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setSpacing(30);
        mainPane.getChildren().addAll(info, login, password, statusMessage, buttons);

        return new Scene(mainPane, 400, 500);
    }

    public void setLoginScene() {
        setScene(loginScene);
    }

    public void setRegistrationScene() {
        setScene(registrationScene);
    }
}
