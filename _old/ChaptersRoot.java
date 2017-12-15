package de.fynder.m4b_tagger.roots;

import de.fynder.m4b_tagger.Main;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;

public class ChaptersRoot extends Parent {
    public ChaptersRoot(Main app, Stage stage, File file) {
        HBox h = new HBox();
        Button b = new Button("test");
        h.getChildren().addAll(b);
        getChildren().add(h);
    }
}
