package abc.java05.service;

import abc.java05.db.DBBook;
import abc.java05.model.Book;
import java.util.Scanner;

public class AdminService {
//    private final DBBook dbBook = new DBBook();
    /*
    * Tao 1 sach, goi thang db cung dc
    * Neu sach da ton tai tra ve false, ko add dc
    * Neu sach add dc them thi tra ve true
    * */
    public boolean createBook(Book book) {
        if(book.getTitle() == null || book.getTitle().isEmpty()) return false;
        return DBBook.save(book);
    }
    /*
    * Xoa 1 q sach
    * Xoa dc thi true
    * Sach ko ton tai tra ve false
    * */
    public boolean deleteBook(String bookID) {
        Book book = DBBook.getBookByID(bookID);
        if(book != null){
            return DBBook.delete(book.getId());
        }
        return false;
    }

    /*
    * Cap nhat nd quyen sach
    * Sach ko ton tai tra ve false
    * */
    public boolean updateBookContent(String bookID, String bookContent) {
        Book book = DBBook.getBookByID(bookID);
        if(book != null){
            book.setContent(bookContent);
            return DBBook.update(book);
        }
        return false;
    }

    public boolean updateBook(Book book) {
        if(book == null) {
            return false;
        }
        return DBBook.update(book);
    }
}
