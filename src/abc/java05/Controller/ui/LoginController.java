package abc.java05.controller.ui;

import abc.java05.model.User;
import abc.java05.service.Login;
import abc.java05.util.Role;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
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

    public void login (ActionEvent event) throws IOException {
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
                System.out.println("jump to new windows");

//                Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
                Stage stageTheLabelBelongs = (Stage) usernameField.getScene().getWindow();
                Parent p = FXMLLoader.load(getClass().getResource("/abc/java05/view/AdminUI.fxml"));
                stageTheLabelBelongs.setScene(new Scene(p));
//                stageTheLabelBelongs.setScene(p.getScene()); // not work but dont know why


            } else if(user.getRole() == Role.USER) {

            } else {
                error.setText("account cant access");
            }

        }
    }
}
