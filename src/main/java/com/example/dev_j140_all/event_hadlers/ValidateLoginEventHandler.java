package com.example.dev_j140_all.event_hadlers;

import com.example.dev_j140_all.database.DatabaseStorage;
import com.example.dev_j140_all.services.JdbcDatabaseService;
import com.example.dev_j140_all.views.AuthorizationStage;
import com.example.dev_j140_all.views.MainStage;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class ValidateLoginEventHandler implements EventHandler {
    private AuthorizationStage stage;
    private Label statusMessage;
    private TextField login;
    private PasswordField password;

    public ValidateLoginEventHandler(AuthorizationStage stage, Label statusMessage, TextField login, PasswordField password) {
        this.stage = stage;
        this.statusMessage = statusMessage;
        this.login = login;
        this.password = password;
    }

    @Override
    public void handle(Event event) {
        String login = this.login.getText();
        String password = this.password.getText();

        try(DatabaseStorage databaseStorage = new JdbcDatabaseService()) {
            if (databaseStorage.checkUserLoginAndPassword(login, password)) {
                new MainStage();
                stage.close();
            } else statusMessage.setText("Invalid login/password");
        } catch (Exception e) {
            statusMessage.setText("Error: could not validate user");
            System.out.println("Error: could not validate user");
        }


    }
}
