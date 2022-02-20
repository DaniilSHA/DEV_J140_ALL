package com.example.dev_j140_all.views;

import com.example.dev_j140_all.database.DatabaseStorage;
import com.example.dev_j140_all.models.Account;
import com.example.dev_j140_all.services.JdbcDatabaseService;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainStage extends Stage {

    private Scene mainScene;

    public MainStage() {
        init();
    }

    private void init() {
        setTitle("Database reader");
        initMainScene();
        show();
    }

    private void initMainScene() {
        VBox mainPane = new VBox();
        mainPane.setSpacing(10);

        MenuBar menuBar = createMainMenuBar();

        HBox infoLabelPane = new HBox();
        infoLabelPane.setAlignment(Pos.CENTER);
        Label infoLabel = new Label("Database information");
        infoLabel.setAlignment(Pos.CENTER);
        infoLabelPane.getChildren().add(infoLabel);

        HBox notesTablePane = new HBox();
        notesTablePane.setAlignment(Pos.CENTER);
        TableView<Account> infoTable = createTableView();
        loadDataFromDatabase(infoTable);
        notesTablePane.getChildren().add(infoTable);

        HBox buttonsPane = new HBox();
        buttonsPane.setAlignment(Pos.CENTER);
        buttonsPane.setSpacing(40);
        Button addNote = new Button("Add note");
        buttonsPane.getChildren().addAll(addNote);

        mainPane.getChildren().addAll(menuBar, infoLabelPane, notesTablePane, buttonsPane);
        mainScene = new Scene(mainPane, 1000, 1000);
        setScene(mainScene);
    }

    private void loadDataFromDatabase(TableView<Account> infoTable) {
        try (DatabaseStorage databaseStorage = new JdbcDatabaseService()) {
            ArrayList<Account> accounts = databaseStorage.loadAccounts();
            infoTable.setItems(FXCollections.observableArrayList(accounts));
        } catch (Exception e) {
            System.out.println("Error: could not load info from database");
        }
    }

    private TableView<Account> createTableView() {
        TableView<Account> infoTable = new TableView<>();

        TableColumn<Account, String> firstNameCol = new TableColumn<>("First name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        infoTable.getColumns().add(firstNameCol);

        TableColumn<Account, String> lastNameCol = new TableColumn<>("Last name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        infoTable.getColumns().add(lastNameCol);

        TableColumn<Account, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        infoTable.getColumns().add(emailCol);

        TableColumn<Account, String> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        infoTable.getColumns().add(genderCol);

        TableColumn<Account, String> creditCardCol = new TableColumn<>("Credit card");
        creditCardCol.setCellValueFactory(new PropertyValueFactory<>("creditCard"));
        infoTable.getColumns().add(creditCardCol);

        TableColumn<Account, String> balanceCol = new TableColumn<>("Balance");
        balanceCol.setCellValueFactory(new PropertyValueFactory<>("balance"));
        infoTable.getColumns().add(balanceCol);

        infoTable.setMinSize(570, 900);

        return infoTable;
    }

    private MenuBar createMainMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        MenuItem saveDatabaseNote = new Menu("add note");
        fileMenu.getItems().add(saveDatabaseNote);

        Menu viewMenu = new Menu("View");
        MenuItem defaultView = new Menu("default");
        viewMenu.getItems().add(defaultView);

        menuBar.getMenus().addAll(fileMenu, viewMenu);
        return menuBar;
    }
}
