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

    /*
    * Lay toan bo sach
    * neu ko co van tra ve list co size = 0
    * */
    public List<Book> viewAllBooks () {
        return null;
    }

    // tra ve 1 sach theo id
    public Book getBookDetail (String bookID) {
        return null;
    }


    /*
    * lay het book tu database ve r search ??
    * Neu ko co thi tra ve 1 list size = 0
    * */
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

    /*
    * Kiem tra:
    *   - co sach trong book case chua ko
    *   - Sach co ton tai ko
    * Ok thi return true, va them sach vao bookcase
    * Ko thi false
    * */
    public boolean addBookToBookCase (String bookCaseID,String bookID) {
        return false;
    }

    /*
    * Kiem tra xem sach co trong book chua
    * Co thi return false
    * Ko co thi remove va return true
    * */
    public boolean removeBookInBookCase (String bookCaseID, String bookID) {
        return false;
    }


    /*
    * Hien thi book theo format tren console
    * */
    public void displayBook(List<Book> books) {

    }


}
