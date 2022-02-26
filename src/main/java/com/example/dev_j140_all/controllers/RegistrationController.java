package com.example.dev_j140_all.controllers;

import com.example.dev_j140_all.database.DatabaseStorage;
import com.example.dev_j140_all.models.User;
import com.example.dev_j140_all.services.JdbcDatabaseService;
import com.example.dev_j140_all.views.AuthorizationStage;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController {
    private AuthorizationStage stage;

    @FXML
    private Label statusMessage;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    public void onActiveButtonClick() {
        switch (makeLoginValidation()) {
            case 0 : break;
            case 1 : statusMessage.setText("invalid login - the length should be between 4 and 16"); return;
            case 2 : statusMessage.setText("invalid login - login should contain only latin characters in lower case."); return;
            case 3 : statusMessage.setText("invalid login - login is busy."); return;
        }

        switch (makePasswordValidation()) {
            case 0 : break;
            case 1 : statusMessage.setText("invalid password - the length should be between 4 and 16"); return;
            case 2 : statusMessage.setText("invalid password - login should contain only " +
                    "latin characters in lower and upper case, numbers from 0 to 9, also specific characters like: #,_,$"); return;
        }

        try (DatabaseStorage databaseStorage = new JdbcDatabaseService()){
            User newUser = new User(login.getText(), password.getText());
            databaseStorage.saveUser(newUser);
            statusMessage.setText("Your account was successfully created. Try to login on login page");
        } catch (Exception e) {
            statusMessage.setText("Error: could not save User");
            System.out.println("Error: could not save User");
        }
    }

    @FXML
    public void onSwitchButtonClick() {
        initStage();
        login.setText("");
        password.setText("");
        stage.setLoginScene();
    }

    private int makePasswordValidation() {
        String password = this.password.getText();
        if (password.length()<4 || password.length()>16) return 1;
        if (!password.matches("[a-zA-z0-9#_$]+")) return 2;
        return 0;
    }

    private int makeLoginValidation() {
        String login = this.login.getText();
        if (login.length()<4 || login.length()>16) return 1;
        if (!login.matches("[a-z]+")) return 2;

        try (DatabaseStorage databaseStorage = new JdbcDatabaseService()){
            if (!databaseStorage.isLoginFree(login)) return 3;
        } catch (Exception e) {
            System.out.println("Error: could not find User by login");
        }

        return 0;
    }

    private void initStage() {
        if (statusMessage != null) stage = (AuthorizationStage) statusMessage.getScene().getWindow();
    }

}
