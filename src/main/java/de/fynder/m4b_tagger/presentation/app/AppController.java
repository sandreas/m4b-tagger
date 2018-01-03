package de.fynder.m4b_tagger.presentation.app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;

import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import static javafx.application.Platform.runLater;

public class AppController implements Initializable {

    @FXML
    public AnchorPane containerPane;

    @FXML
    public MediaView mediaView;

    @FXML
    public ProgressBar progressBar;

    @FXML
    VBox container;

    @FXML
    public MenuBar menuBar;

    @FXML
    TextArea noteContent;
//    @Inject
//    NotesStore store;

    @FXML
    AnchorPane noteList;
    @FXML
    AnchorPane statusPane;
    private File audioFile;
    private MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // mediaplayer fit with
//        final MediaPlayer videoPlayer = new MediaPlayer(
//                new Media(new File(videoPath).toURI().toString()));
//        MediaView mv = new MediaView(videoPlayer);
//        DoubleProperty mvw = mv.fitWidthProperty();
//        DoubleProperty mvh = mv.fitHeightProperty();
//        mvw.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
//        mvh.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));
//        mv.setPreserveRatio(true);

    }

    public void noteNameEntered() {
        System.out.println("AppPresender.noteNameEntered()");
    }

    public void save() {
        System.out.println("AppPresender.save()");
    }

    public void quitApplication() {
        System.exit(0);
    }

    public void openFile(/*ActionEvent actionEvent*/) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Audiobook (*.m4b)", "*.m4b");
        fileChooser.getExtensionFilters().add(filter);
        fileChooser.setTitle("Open Resource File");


        Window theStage = container.getScene().getWindow();
        audioFile = fileChooser.showOpenDialog(theStage);
        if (audioFile != null) {
            handleOpenFileEvent();
        }
    }

    private void handleOpenFileEvent(/*ActionEvent actionEvent*/) {


        // mediaplayer fit with
//        final MediaPlayer videoPlayer = new MediaPlayer(
//                new Media(new File(videoPath).toURI().toString()));
//        MediaView mv = new MediaView(videoPlayer);
//        DoubleProperty mvw = mv.fitWidthProperty();
//        DoubleProperty mvh = mv.fitHeightProperty();
//        mvw.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
//        mvh.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));
//        mv.setPreserveRatio(true);

        mediaPlayer = new MediaPlayer(new Media(audioFile.toURI().toString()));

        mediaPlayer.currentTimeProperty().addListener(ov -> updateValues());

        progressBar.setOnMouseClicked(event -> {
            System.out.println("x: " + event.getX());
        });
        //progressBar.setProgress(0.5);
        // this.mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }

    private void handleProgressBarClick(MouseEvent e) {

    }


    protected void updateValues() {
        runLater(() -> {
            double progress = (100 / (double)mediaPlayer.getTotalDuration().toMillis()) * (double)mediaPlayer.getCurrentTime().toMillis();

//            30000 => 100%
//            1 => 100 / 30000

            progressBar.setProgress(progress / 100);
        });
    }

    public void handleRewind15() {
        seekOffsetMillis(-15 * 1000);
    }

    public void handleToggle() {
        if (mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.play();
        }
    }

    public void handleForward15() {
        seekOffsetMillis(15 * 1000);
    }

    private void seekOffsetMillis(int offsetMillis) {
        mediaPlayer.seek(new Duration(mediaPlayer.getCurrentTime().toMillis() + offsetMillis));
    }

}
