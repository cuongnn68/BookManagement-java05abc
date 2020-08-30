package abc.java05.controller.ui;

import abc.java05.model.User;
import abc.java05.service.Login;
import abc.java05.util.Role;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private VBox loginBox;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label error;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        ((Stage) usernameField.getScene().getWindow()).setTitle("Login"); //not working
    }

    @FXML
    private void login (ActionEvent event) throws IOException {
//        System.out.println("login");
//        System.out.println(usernameField.getText());
//        System.out.println(passwordField.getText());
        String username = usernameField.getText();
        String password = passwordField.getText();
        if(username.isEmpty() || password.isEmpty()) {
            error.setText("Please enter user name and password");
        } else {
            Login login = new Login();
            User user = login.checkAccount(username, password);
            if(user == null) {
                error.setText("Account dont exists");
            } else if(user.getRole() == Role.ADMIN) {
                error.setText("");
                Stage stageNow = (Stage) usernameField.getScene().getWindow();
                Parent p = FXMLLoader.load(getClass().getResource("/abc/java05/view/AdminUI.fxml"));
                stageNow.setTitle("Admin");
                stageNow.setScene(new Scene(p));
            } else if(user.getRole() == Role.USER) {
                error.setText("");
                Stage stageNow = (Stage) usernameField.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/abc/java05/view/UserUI.fxml"));
                Parent p = fxmlLoader.load();

                UserController uController = fxmlLoader.getController();
//                uController.setUser(user);
                uController.realInitialize(user);

                stageNow.setTitle(user.getUserName());
                stageNow.setScene(new Scene(p));
            } else {
                error.setText("account cant access");
            }

        }
    }
}
