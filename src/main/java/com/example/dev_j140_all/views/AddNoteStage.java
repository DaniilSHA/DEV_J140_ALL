package com.example.dev_j140_all.views;

import com.example.dev_j140_all.StartApplication;
import com.example.dev_j140_all.controllers.AddNoteStageController;
import com.example.dev_j140_all.controllers.MainStageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddNoteStage extends Stage {

    private String currentStylesheetPath;
    private MainStageController mainStageController;
    private Scene addNoteScene;

    public AddNoteStage(String currentStylesheetPath, MainStageController mainStageController) {
        this.currentStylesheetPath = currentStylesheetPath;
        this.mainStageController=mainStageController;
        init();
    }

    private void init() {
        setTitle("Add note information");
        initAddNoteScene();
        show();
    }

    private void initAddNoteScene() {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("add-note-scene.fxml"));
        try {
            addNoteScene = new Scene(fxmlLoader.load(), 400, 600);
            AddNoteStageController addNoteStageController = fxmlLoader.getController();
            addNoteStageController.setMainStageController(mainStageController);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: could not load add note scene from fxml");
        }
        addNoteScene.getStylesheets().add(currentStylesheetPath);
        setScene(addNoteScene);
    }

}
