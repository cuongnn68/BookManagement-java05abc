package abc.java05.controller.ui;

import abc.java05.model.Book;
import abc.java05.service.AdminService;
import abc.java05.service.UserService;
import com.sun.xml.internal.bind.v2.runtime.property.PropertyFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private Label adminLabel;

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> cID;
    @FXML
    private TableColumn<Book, String> cTitle;
    @FXML
    private TableColumn<Book, String> cAuthor;
    @FXML
    private TableColumn<Book, String> cBrief;
    @FXML
    private TableColumn<Book, String> cPublisher;
    @FXML
    private TableColumn<Book, String> cCategory;

    @FXML
    private Button createButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button logoutButton;


    private AdminService aService = new AdminService();
    private UserService uService = new UserService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cID.setCellValueFactory(new PropertyValueFactory<>("id"));
        cTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        cAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        cBrief.setCellValueFactory(new PropertyValueFactory<>("brief"));
        cPublisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        cCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        bookTable.setItems(getBooks());

        // multi select
    }

    private ObservableList<Book> getBooks() {
        ObservableList<Book> olBooks = FXCollections.observableArrayList();
        List<Book> lBooks = uService.viewAllBooks();
        olBooks.addAll(lBooks);
        return olBooks;
    }

    public void createBook() {

    }

    public void deleteBook() {
        Book book = bookTable.getSelectionModel().getSelectedItem();
        aService.deleteBook(book.getId());
        bookTable.getItems().remove(book);
    }

    public void updateBook() {

    }

    public void logut () throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/abc/java05/view/Login.fxml"));
        Stage now =  (Stage) adminLabel.getScene().getWindow();
        now.setScene(new Scene(p));
    }
}
