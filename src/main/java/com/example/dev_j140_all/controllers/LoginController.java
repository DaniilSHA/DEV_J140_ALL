package com.example.dev_j140_all.controllers;

import com.example.dev_j140_all.database.DatabaseStorage;
import com.example.dev_j140_all.services.JdbcDatabaseService;
import com.example.dev_j140_all.views.AuthorizationStage;
import com.example.dev_j140_all.views.MainStage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    private AuthorizationStage stage;

    @FXML
    private Label statusMessage;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    public void onActiveButtonClick() {
        initStage();
        String login = this.login.getText();
        String password = this.password.getText();

        try (DatabaseStorage databaseStorage = new JdbcDatabaseService()) {
            if (databaseStorage.checkUserLoginAndPassword(login, password)) {
                new MainStage();
                stage.close();
            } else statusMessage.setText("Invalid login/password");
        } catch (Exception e) {
            e.printStackTrace();
            statusMessage.setText("Error: could not validate user");
            System.out.println("Error: could not validate user");
        }
    }

    @FXML
    public void onSwitchButtonClick() {
        initStage();
        login.setText("");
        password.setText("");
        stage.setRegistrationScene();
    }

    private void initStage() {
        if (statusMessage != null) stage = (AuthorizationStage) statusMessage.getScene().getWindow();
    }

}
