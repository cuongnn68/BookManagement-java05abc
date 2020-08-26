package abc.java05.service;

import abc.java05.db.DBBook;
import abc.java05.model.Book;

import java.util.List;

public class AdminService {
    private DBBook dbBook = new DBBook();
    /*
    * Tao 1 sach, goi thang db cung dc
    * Neu sach da ton tai tra ve false, ko add dc
    * Neu sach add dc them thi tra ve true
    * */
    public boolean createBook() {
        Book book = new Book();

        /*
        Enter book info:
        */

        if(dbBook.save(book)){
            return true;
        }
        return false;
    }
    /*
    * Xoa 1 q sach
    * Xoa dc thi true
    * Sach ko ton tai tra ve false
    * */
    public boolean deleteBook(String bookID) {
        Book book = dbBook.getBookByID(bookID);
        if(book != null){
            dbBook.delete(book);
            return true;
        }
        return false;
    }

    /*
    * Cap nhat nd quyen sach
    * Sach ko ton tai tra ve false
    * */
    public boolean updateBook(String bookID, String bookContent) {
        Book book = dbBook.getBookByID(bookID);
        if(book != null){
            book.setContent(bookContent);
            return true;
        }
        return false;
    }
}
