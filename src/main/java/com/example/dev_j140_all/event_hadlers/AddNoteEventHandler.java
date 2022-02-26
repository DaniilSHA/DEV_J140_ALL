package com.example.dev_j140_all.event_hadlers;

import com.example.dev_j140_all.views.AddNoteStage;
import com.example.dev_j140_all.views.MainStage;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class AddNoteEventHandler implements EventHandler {

    private String currentStylesheetPath;
    private MainStage stage;
    public AddNoteEventHandler(MainStage stage, String currentStylesheetPath) {
        this.stage = stage;
        this.currentStylesheetPath = currentStylesheetPath;

    }

    @Override
    public void handle(Event event) {
        currentStylesheetPath = stage.getCurrentStylesheetPath();
        Stage addNoteStage = new AddNoteStage(currentStylesheetPath);
    }
}
