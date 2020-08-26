package abc.java05.db;

import abc.java05.model.Book;

import java.util.List;

public class DBBook {

    /*
    * Lay toan bo sach trong database
    * */
    public List<Book> getAll() {
        return null;
    }

    /*
    * Lay 1 sach theo id
    * Neu khong tim thay return null
    * */
    public Book getBookByID(String id) {
        return null;
    }


    /*
    * Them sach vao database
    * return true neu save thanh cong
    * return false neu save khong thanh cong
    * */
    public boolean save(Book book) {
        return false;
    }

    /*
    * Cap nhat thong tin sach theo id
    * */
    public void update(Book book) {

    }

    /*
    * Xoa sach trong bang Book va bang Contain
    * */
    public void delete(Book book) {

    }
}
