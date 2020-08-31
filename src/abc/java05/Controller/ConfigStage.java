package abc.java05.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ConfigStage {
    public static void loginWindow(Stage s, Scene scene) {
        s.setTitle("Login");
        s.setMinWidth(600);
        s.setMinHeight(400);
        s.setWidth(600);
        s.setHeight(400);
        s.setResizable(false);
        scene.getStylesheets().add("/abc/java05/view/FlatBee.css");
    }
    public static void adminWindow(Stage s, Scene scene) {
        s.setTitle("Reading Book Management (Admin)");
        s.setMinWidth(800);
        s.setMinHeight(500);
        s.setWidth(800);
        s.setHeight(500);
        s.setResizable(true);
        scene.getStylesheets().add("/abc/java05/view/FlatBee.css");
    }
    public static void userWindow(Stage s, Scene scene) {
        s.setTitle("Reading Book Management");
        s.setMinWidth(800);
        s.setMinHeight(500);
        s.setWidth(800);
        s.setHeight(500);
        s.setResizable(true);
        scene.getStylesheets().add("/abc/java05/view/FlatBee.css");
    }
}
