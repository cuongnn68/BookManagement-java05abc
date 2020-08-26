package abc.java05.service;

import abc.java05.model.Book;
import abc.java05.model.BookCase;

import java.util.List;

public class UserService {


    /*
    * Tu id book lay Book tu data base xong tra ve content
    * */
    public String readBook (String bookID) {
        return null;
    }

    // ds book trong db
    public List<Book> viewAllBooks () {
        return null;
    }

    // tra ve 1 sach theo id
    public Book getBookDetail (String bookID) {
        return null;
    }

    // lay het book tu database ve r search
    public List<Book> searchBookByName (String bookName) {
        return null;
    }
    public List<Book> searchBookByAuthor (String author) {
        return null;
    }
    public List<Book> searchBookByCategory (String category) {
        return null;
    }

    // lay book case voi bookcase id cua user
    public BookCase viewBookCase (String userID) {
        return null;
    }

    /* TODO: db nao??
    * Kiem tra xem co sach ko
    * Co thi return true, va them sach vao bookcase
    * Ko thi false
    * */
    public boolean addBookToBookCase (String bookID) {
        return false;
    }

    /*
    * 
    * */
    public boolean removeBookInBookCase (String bookID) {
        return false;
    }


}
