package de.fynder.m4b_tagger;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setTitle("m4b-tagger");
//
//        StackPane layout = new StackPane();
//
//        Scene scene = new Scene(layout, 300, 200);
//        primaryStage.setScene(scene);
//        primaryStage.show();

        // http://www.mkyong.com/java/java-properties-file-examples/
//        String homeDir = System.getProperty("user.home");
//        String configDir = Paths.get(homeDir + "/.m4b-tagger");

        String executable = "graft";

        if(executableExists(executable)) {
            System.out.println(executable + " exists");
        }else {
            System.out.println(executable + " is missing");
        }



        System.exit(0);
    }

    private boolean executableExists(String executable) {
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(executable);
            proc.waitFor();
            return proc.exitValue() == 0;
        } catch(Exception e) {
            return false;
        }
    }
}