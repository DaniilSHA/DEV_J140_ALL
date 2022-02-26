package com.example.dev_j140_all.controllers;

import com.example.dev_j140_all.database.DatabaseStorage;
import com.example.dev_j140_all.models.Account;
import com.example.dev_j140_all.services.JdbcDatabaseService;
import com.example.dev_j140_all.views.AddNoteStage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddNoteStageController {

    private MainStageController mainStageController;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField genderTextField;

    @FXML
    private TextField creditCardTextField;

    @FXML
    private TextField balanceTextField;

    public void onAddNoteButtonClick() {
        try (DatabaseStorage databaseStorage = new JdbcDatabaseService()) {
            Account newAccount = new Account(
                    firstNameTextField.getText(),
                    lastNameTextField.getText(),
                    emailTextField.getText(),
                    genderTextField.getText(),
                    creditCardTextField.getText(),
                    balanceTextField.getText()
            );
            databaseStorage.saveAccount(newAccount);
            onCloseButtonClick();
            mainStageController.loadDataFromDatabase();
        } catch (Exception e) {
            System.out.println("Error: could not save Account in database");
        }
    }

    public void onCloseButtonClick() {
        if (firstNameTextField != null) {
            AddNoteStage stage = (AddNoteStage)firstNameTextField.getScene().getWindow();
            stage.close();
        }
    }

    public void onClearButtonClick() {
        if (firstNameTextField != null) firstNameTextField.setText("");
        if (lastNameTextField != null) lastNameTextField.setText("");
        if (emailTextField != null) emailTextField.setText("");
        if (genderTextField != null) genderTextField.setText("");
        if (creditCardTextField != null) creditCardTextField.setText("");
        if (balanceTextField != null) balanceTextField.setText("");
    }

    public void setMainStageController(MainStageController mainStageController) {
        this.mainStageController = mainStageController;
    }

}
