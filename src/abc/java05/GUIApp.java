package abc.java05;

import abc.java05.controller.ConfigStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIApp  extends Application {
    static public void run(String... args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/abc/java05/view/Login.fxml"));
        Scene scene = new Scene(root);
        ConfigStage.loginWindow(primaryStage, scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
