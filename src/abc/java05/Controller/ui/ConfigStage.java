package abc.java05.controller.ui;

import javafx.stage.Stage;

public class ConfigStage {
    public static void loginWindow(Stage s) {
        s.setTitle("Login");
        s.setMinWidth(600);
        s.setMinHeight(400);
        s.setWidth(600);
        s.setHeight(400);
//        s.setResizable(false);
    }
    public static void adminWindow(Stage s) {
        s.setTitle("Reading Book Management (Admin)");
        s.setMinWidth(800);
        s.setMinHeight(500);
        s.setWidth(800);
        s.setHeight(500);
        s.setResizable(true);
    }
    public static void userWindow(Stage s) {
        s.setTitle("Reading Book Management");
        s.setMinWidth(800);
        s.setMinHeight(500);
        s.setWidth(800);
        s.setHeight(500);
        s.setResizable(true);
    }
}
