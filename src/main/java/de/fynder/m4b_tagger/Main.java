package de.fynder.m4b_tagger;


import com.airhacks.afterburner.injection.Injector;
import de.fynder.m4b_tagger.presentation.app.AppController;
import de.fynder.m4b_tagger.presentation.app.AppView;
import de.fynder.m4b_tagger.utils.AudioBook;
import de.fynder.m4b_tagger.utils.AudioBookAnalyser;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import net.bramp.ffmpeg.FFmpeg;

import java.io.File;


public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {

//        File f = new File("tmp/modified.m4b");
//
//        AudioBookAnalyser analyser = new AudioBookAnalyser();
//
//        AudioBook book = analyser.analyse(f);





        setUserAgentStylesheet(STYLESHEET_MODENA);
        AppView appView = new AppView();
        Scene scene = new Scene(appView.getView());
        Parent view = appView.getView();
        MenuBar menuBar = (MenuBar)view.lookup("MenuBar");
        final String os = System.getProperty ("os.name");
        if (os != null && os.startsWith ("Mac")) {
            System.out.println("native menu bar");
            menuBar.useSystemMenuBarProperty ().set (true);
        } else {
            System.out.println("default menu bar");
        }


        // registerAccelerators(appView, scene);
        stage.setTitle("m4b-tagger");
        final String uri = getClass().getResource("main.css").toExternalForm();
        scene.getStylesheets().add(uri);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void stop() throws Exception {
        Injector.forgetAll();
    }

    public static void main(String[] args) {
        launch(args);
    }

//    private void registerAccelerators(AppView appView, Scene scene) {
//        final AppController presenter = (AppController) appView.getPresenter();
//        scene.getAccelerators().put(
//                new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY),
//                presenter::save);
//    }
}