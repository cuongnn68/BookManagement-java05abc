package abc.java05.controller;

import abc.java05.model.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InputBookController {
    private Book book;

    @FXML
    private TextField title;

    @FXML
    private TextField author;

    @FXML
    private TextField publisher;

    @FXML
    private TextField category;

    @FXML
    private TextArea brief;

    @FXML
    private TextArea content;

    @FXML
    private Button okButton;

    public void setBook(Book b) {
        book = b;
    }

    public void showBook () {
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        publisher.setText(book.getPublisher());
        category.setText(book.getCategory());
        content.setText(book.getContent());
        brief.setText(book.getBrief());
    }

    @FXML
    private void returnBook() {
//        book.setId("");
        book.setTitle(title.getText());
        book.setAuthor(author.getText());
        book.setPublisher(publisher.getText());
        book.setCategory(category.getText());
        book.setContent(content.getText());
        book.setBrief(brief.getText());
        ((Stage)okButton.getScene().getWindow()).close();
    }



}
