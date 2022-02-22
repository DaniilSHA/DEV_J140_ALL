package com.example.dev_j140_all.views;

import com.example.dev_j140_all.database.DatabaseStorage;
import com.example.dev_j140_all.models.Account;
import com.example.dev_j140_all.services.JdbcDatabaseService;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddNoteStage extends Stage {

    private String currentStylesheetPath;

    public AddNoteStage(String currentStylesheetPath) {
        this.currentStylesheetPath = currentStylesheetPath;
        init();
    }

    private void init() {
        setTitle("Add note information");
        initAddNoteScene();
        show();
    }

    private void initAddNoteScene() {
        VBox mainPane = new VBox();
        mainPane.setSpacing(10);

        HBox infoLabelPane = new HBox();
        infoLabelPane.setAlignment(Pos.CENTER);
        Label infoLabel = new Label("Add new node's information");
        infoLabel.setAlignment(Pos.CENTER);
        infoLabelPane.getChildren().add(infoLabel);

        HBox firstNamePane = new HBox();
        firstNamePane.setAlignment(Pos.CENTER);
        Label firstNameLabel = new Label("Add first name: ");
        TextField firstNameTextField = new TextField();
        firstNamePane.getChildren().addAll(firstNameLabel, firstNameTextField);

        HBox lastNamePane = new HBox();
        lastNamePane.setAlignment(Pos.CENTER);
        Label lastNameLabel = new Label("Add last name: ");
        TextField lastNameTextField = new TextField();
        lastNamePane.getChildren().addAll(lastNameLabel, lastNameTextField);

        HBox emailPane = new HBox();
        emailPane.setAlignment(Pos.CENTER);
        Label emailLabel = new Label("Add email: ");
        TextField emailTextField = new TextField();
        emailPane.getChildren().addAll(emailLabel, emailTextField);

        HBox genderNamePane = new HBox();
        genderNamePane.setAlignment(Pos.CENTER);
        Label genderLabel = new Label("Add gender: ");
        TextField genderTextField = new TextField();
        genderNamePane.getChildren().addAll(genderLabel, genderTextField);

        HBox creditCardPane = new HBox();
        creditCardPane.setAlignment(Pos.CENTER);
        Label creditCardLabel = new Label("Add credit card: ");
        TextField creditCardTextField = new TextField();
        creditCardPane.getChildren().addAll(creditCardLabel, creditCardTextField);

        HBox balancePane = new HBox();
        balancePane.setAlignment(Pos.CENTER);
        Label balanceLabel = new Label("Add first name: ");
        TextField balanceTextField = new TextField();
        balancePane.getChildren().addAll(balanceLabel, balanceTextField);

        HBox buttonsPane = new HBox();
        buttonsPane.setAlignment(Pos.CENTER);
        buttonsPane.setSpacing(20);
        Button addNote = new Button("Add note");
        addNote.setOnAction((event -> {
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
                this.close();
                MainStage.loadDataFromDatabase();
            } catch (Exception e) {
                System.out.println("Error: could not save Account in database");
            }

        }));
        Button clear = new Button("Clear");
        clear.setOnAction((event -> {
            firstNameTextField.setText("");
            lastNameTextField.setText("");
            emailTextField.setText("");
            genderTextField.setText("");
            creditCardTextField.setText("");
            balanceTextField.setText("");
        }));
        Button close = new Button("Close");
        close.setOnAction((event -> close()));
        buttonsPane.getChildren().addAll(addNote, clear, close);

        mainPane.getChildren().addAll(
                infoLabelPane,
                firstNamePane,
                lastNamePane,
                emailPane,
                genderNamePane,
                creditCardPane,
                balancePane,
                buttonsPane
        );

        Scene scene = new Scene(mainPane, 400, 600);
        scene.getStylesheets().add(currentStylesheetPath);
        setScene(scene);
    }
}
