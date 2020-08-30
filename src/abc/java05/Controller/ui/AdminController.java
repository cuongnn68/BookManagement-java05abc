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
import javafx.stage.Modality;
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

    @FXML
    private void createBook() throws IOException {
        Book newBook = new Book();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/abc/java05/view/InputBook.fxml"));
        Parent root = fxmlLoader.load();

        InputBookController ibController = fxmlLoader.getController();
        ibController.setBook(newBook);

        Stage popup = new Stage();
        popup.setTitle("Create new book");
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setScene(new Scene(root));
        popup.showAndWait();

        aService.createBook(newBook);
        bookTable.setItems(getBooks());
    }

    @FXML
    private void deleteBook() {
        Book book = bookTable.getSelectionModel().getSelectedItem();
        if(book == null) {
            return;
        }
        aService.deleteBook(book.getId());
        bookTable.getItems().remove(book);
    }

    @FXML
    private void updateBook() throws IOException {
        Book book = bookTable.getSelectionModel().getSelectedItem();
        if(book == null) {
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/abc/java05/view/InputBook.fxml"));
        Parent root = fxmlLoader.load();

        InputBookController ibController = fxmlLoader.getController();
        ibController.setBook(book);
        ibController.showBook();

        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setScene(new Scene(root));
        popup.setTitle("Update book");
        popup.showAndWait();
        aService.updateBook(book);
        bookTable.setItems(getBooks());
    }

    @FXML
    private void logout() throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/abc/java05/view/Login.fxml"));
        Stage now =  (Stage) adminLabel.getScene().getWindow();
        now.setTitle("Login");
        now.setScene(new Scene(p));
    }
}
