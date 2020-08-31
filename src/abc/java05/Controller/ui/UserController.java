package abc.java05.controller.ui;

import abc.java05.model.Book;
import abc.java05.model.BookCase;
import abc.java05.model.User;
import abc.java05.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    private User user;
    private BookCase bookCase;
    private UserService uService;

    @FXML
    private Button buttonTabBookshelf;
    @FXML
    private Button buttonTabAll;
    @FXML
    private Button buttonLogout;

    @FXML
    private VBox paneBookshelf;
    @FXML
    private VBox paneAll;


    // ====================================================================

    @FXML
    private TableView<Book> bookTableCase;

    @FXML
    private TableColumn<Book, String> cIDCase;
    @FXML
    private TableColumn<Book, String> cTitleCase;
    @FXML
    private TableColumn<Book, String> cAuthorCase;
    @FXML
    private TableColumn<Book, String> cBriefCase;
    @FXML
    private TableColumn<Book, String> cPublisherCase;
    @FXML
    private TableColumn<Book, String> cCategoryCase;

    @FXML
    private Button buttonReadCase;
    @FXML
    private Button buttonRemoveCase;

    // ======================================================================




    @FXML
    private TableView<Book> bookTableAll;

    @FXML
    private TableColumn<Book, String> cIDAll;
    @FXML
    private TableColumn<Book, String> cTitleAll;
    @FXML
    private TableColumn<Book, String> cAuthorAll;
    @FXML
    private TableColumn<Book, String> cBriefAll;
    @FXML
    private TableColumn<Book, String> cPublisherAll;
    @FXML
    private TableColumn<Book, String> cCategoryAll;

    @FXML
    private Button buttonReadAll;
    @FXML
    private Button buttonAdd2Case;
    @FXML
    private Region space;
    @FXML
    private TextField searchField;
    @FXML
    private Button buttonSearch;




//    public void setUser(User u) {
//        user = u;
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cIDCase.setCellValueFactory(new PropertyValueFactory<>("id"));
        cTitleCase.setCellValueFactory(new PropertyValueFactory<>("title"));
        cAuthorCase.setCellValueFactory(new PropertyValueFactory<>("author"));
        cBriefCase.setCellValueFactory(new PropertyValueFactory<>("brief"));
        cPublisherCase.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        cCategoryCase.setCellValueFactory(new PropertyValueFactory<>("category"));


        cIDAll.setCellValueFactory(new PropertyValueFactory<>("id"));
        cTitleAll.setCellValueFactory(new PropertyValueFactory<>("title"));
        cAuthorAll.setCellValueFactory(new PropertyValueFactory<>("author"));
        cBriefAll.setCellValueFactory(new PropertyValueFactory<>("brief"));
        cPublisherAll.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        cCategoryAll.setCellValueFactory(new PropertyValueFactory<>("category"));

    }

    public void realInitialize (User u) {
        user = u;
        uService = new UserService();
        bookCase = uService.viewBookCase(user.getId());
        bookSelfTab();
        VBox.setVgrow(bookTableCase, Priority.ALWAYS);
        VBox.setVgrow(bookTableAll, Priority.ALWAYS);
        HBox.setHgrow(space, Priority.ALWAYS);
    }

    private ObservableList<Book> getAllBooks() {
        ObservableList<Book> olBooks = FXCollections.observableArrayList();
        List<Book> lBooks = uService.viewAllBooks();
        olBooks.addAll(lBooks);
        return olBooks;
    }

    private ObservableList<Book> getBooksCase() {
        ObservableList<Book> olBooks = FXCollections.observableArrayList();
        bookCase = uService.viewBookCase(user.getId());
        olBooks.addAll(bookCase.getBooks());
        return olBooks;
    }

    private void readBook(TableView<Book> table) {
        Book book =  table.getSelectionModel().getSelectedItem();
        if(book == null) {
            return;
        }
        //TODO: do read
        Stage read = new Stage();
        Label content = new Label(book.getContent());
        content.setMaxWidth(400);
        content.setWrapText(true);
        Scene readScene = new Scene(content);
        read.setTitle(book.getTitle());
        read.setScene(readScene);
        read.initModality(Modality.APPLICATION_MODAL);
        read.showAndWait();
    }

    // =======================================================================

    @FXML
    private void readCase() {
        readBook(bookTableCase);
    }
    @FXML
    private void removeCase() {
        Book book =  bookTableCase.getSelectionModel().getSelectedItem();
        if(book == null) {
            return;
        }
        bookCase.getBooks().removeIf(b -> b.getId().equalsIgnoreCase(book.getId()));
        uService.removeBookInBookCase(user.getBookCaseID(),book.getId());
        bookSelfTab();
    }


    @FXML
    private void readAll() {
        readBook(bookTableAll);
    }
    @FXML
    private void add2Case() {
        Book book =  bookTableAll.getSelectionModel().getSelectedItem();
        if(book == null) {
            return;
        }
        uService.addBookToBookCase(user.getBookCaseID(), book.getId());
    }
    @FXML
    private void searchBook() {
        String keyword = searchField.getText();
        if(keyword == null || keyword.isEmpty()) {
            return;
        }
        ObservableList<Book> list = FXCollections.observableArrayList();
        list.addAll(uService.searchBookByAuthor(keyword));
        list.addAll(uService.searchBookByName(keyword));
        list.addAll(uService.searchBookByCategory(keyword));
        bookTableAll.setItems(list);

    }




    @FXML
    private void bookSelfTab() {
        bookTableCase.setItems(getBooksCase());
        paneBookshelf.setVisible(true);
        paneAll.setVisible(false);
    }

    @FXML
    private void allBookTab() {
        bookTableAll.setItems(getAllBooks());
        paneBookshelf.setVisible(false);
        paneAll.setVisible(true);
    }


    @FXML
    private void logout() throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/abc/java05/view/Login.fxml"));
        Stage now =  (Stage) buttonLogout.getScene().getWindow();
        ConfigStage.loginWindow(now);
        now.setScene(new Scene(p));
    }

}
