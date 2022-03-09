package com.example.dev_j140_all.controllers;

import com.example.dev_j140_all.StartApplication;
import com.example.dev_j140_all.database.DatabaseStorage;
import com.example.dev_j140_all.models.Account;
import com.example.dev_j140_all.services.JdbcDatabaseService;
import com.example.dev_j140_all.views.AddNoteStage;
import com.example.dev_j140_all.views.MainStage;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainStageController {

    MainStage mainStage;

    @FXML
    public TableView<Account> accountTable;
    @FXML
    private TableColumn<Account, String> firstNameCol;
    @FXML
    private TableColumn<Account, String> lastNameCol;
    @FXML
    private TableColumn<Account, String> emailCol;
    @FXML
    private TableColumn<Account, String> genderCol;
    @FXML
    private TableColumn<Account, String> creditCardCol;
    @FXML
    private TableColumn<Account, String> balanceCol;

    @FXML
    private void initialize() {
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        creditCardCol.setCellValueFactory(new PropertyValueFactory<>("creditCard"));
        balanceCol.setCellValueFactory(new PropertyValueFactory<>("balance"));
        loadDataFromDatabase();
    }

    @FXML
    public void loadDataFromDatabase() {
        try (DatabaseStorage databaseStorage = new JdbcDatabaseService()) {
            ArrayList<Account> accounts = databaseStorage.loadAccounts();
            accountTable.setItems(FXCollections.observableArrayList(accounts));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: could not load info from database");
        }
    }

    @FXML
    public void onAddNoteButtonClick() {
        String currentStylesheetPath = accountTable.getScene().getStylesheets().get(0);
        Stage addNoteStage = new AddNoteStage(currentStylesheetPath, this);
    }

    @FXML
    public void onCustomCssViewClick() {
        initStage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select onw css stylesheet");
        File file = fileChooser.showOpenDialog(mainStage);
        if (file != null && file.getPath().endsWith(".css")) {
            String currentStylesheetPath = "file:/" + file.getAbsolutePath().replaceAll("\\\\", "/");
            mainStage.setCurrentStylesheetPath(currentStylesheetPath);
            mainStage.getScene().getStylesheets().clear();
            mainStage.getScene().getStylesheets().add(currentStylesheetPath);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setContentText("Selected file error");
            alert.show();
        }
    }


    @FXML
    public void onCustomFxmlViewClick() {
        initStage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select onw fxml file");
        File file = fileChooser.showOpenDialog(mainStage);
        if (file != null && file.getPath().endsWith(".fxml")) {
            String currentFxmlFilePath = file.getAbsolutePath().replaceAll("\\\\", "/");
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(new File(currentFxmlFilePath).toURL());
                Scene customScene = new Scene(fxmlLoader.load(), 1000, 1000);
                customScene.getStylesheets().add(mainStage.getCurrentStylesheetPath());
                mainStage.setScene(customScene);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error: could not load custom scene from fxml");
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setContentText("Selected file error");
            alert.show();
        }
    }

    @FXML
    public void onDefaultViewClick() {
        initStage();
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("main-scene.fxml"));
        Scene mainScene = null;
        try {
            mainScene = new Scene(fxmlLoader.load(), 1000, 1000);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: could not load main scene from fxml");
        }
        mainStage.setScene(mainScene);
        mainStage.getScene().getStylesheets().clear();
        mainStage.getScene().getStylesheets().add(MainStage.DEFAULT_VIEW);
    }

    private void initStage() {
        if (accountTable != null) mainStage = (MainStage) accountTable.getScene().getWindow();
    }

}
