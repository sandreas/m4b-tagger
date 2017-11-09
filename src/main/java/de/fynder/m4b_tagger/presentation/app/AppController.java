package de.fynder.m4b_tagger.presentation.app;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    @FXML
    TextField noteName;
    @FXML
    TextArea noteContent;
//    @Inject
//    NotesStore store;

    @FXML
    AnchorPane noteList;
    @FXML
    AnchorPane statusPane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void noteNameEntered() {
        System.out.println("AppPresender.noteNameEntered()");

    }

    public void save() {
        System.out.println("AppPresender.save()");
    }
}
