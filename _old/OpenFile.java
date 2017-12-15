package de.fynder.m4b_tagger.roots;

import de.fynder.m4b_tagger.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;

public class OpenFile extends BorderPane {
    private final Main app;
    private final Stage stage;

    public OpenFile(Main application, Stage stg) {
        app = application;
        stage = stg;
        Button openFile = new Button("Open file...");

        openFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audiobooks (*.m4b)", "*.m4b"));

                File file = fileChooser.showOpenDialog(stage);
                if(file == null) {
                    return;
                }

                String mimetype = "";
                try {
                    mimetype = Files.probeContentType(file.toPath());
                } catch(Exception probeContentException) {
                    app.showAlert("Could not open file", "", "File must be a valid m4b");
                }

                if(mimetype.equals("mp4")) {
                    app.showAlert("Invalid file type", "", "File must be a valid m4b");
                    return;
                }

                app.switchRoot(new ChaptersRoot(app, stage, file));
            }
        });



        setCenter(openFile);
    }

}
