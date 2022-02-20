package com.example.dev_j140_all.event_hadlers;

import com.example.dev_j140_all.views.AddNoteStage;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class AddNoteEventHandler implements EventHandler {
    @Override
    public void handle(Event event) {
        Stage addNoteStage = new AddNoteStage();
    }
}
