package com.example.dev_j140_all.event_hadlers;

import com.example.dev_j140_all.models.AuthorizationStage;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ValidateRegistrationEventHandler implements EventHandler {
    private AuthorizationStage stage;
    private Label statusMessage;
    private TextField login;
    private PasswordField password;

    public ValidateRegistrationEventHandler(AuthorizationStage stage, Label statusMessage, TextField login, PasswordField password) {
        this.stage = stage;
        this.statusMessage = statusMessage;
        this.login = login;
        this.password = password;
    }

    @Override
    public void handle(Event event) {
        if (!validateLogin()) {
            statusMessage.setText("invalid login");
            return;
        }
        if (!validatePassword()) {
            statusMessage.setText("invalid password");
            return;
        }
        statusMessage.setText("Your account was successfully created. Try to login on login page");
    }

    private boolean validatePassword() {
        return true;
    }

    private boolean validateLogin() {
        return true;
    }
}
