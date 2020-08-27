package abc.java05.db;

import abc.java05.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBBook {

    /*
     * Lay toan bo sach trong database
     * */
    public static List<Book> getAll() {
        Connection cons = DBConnect.getConnection();
        String sql = "SELECT * FROM Book";
        List<Book> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getString("book_id"));
                book.setCategory(rs.getString("category"));
                book.setContent(rs.getString("content"));
                book.setPublisher(rs.getString("publisher"));
                book.setBrief(rs.getString("brief"));
                book.setAuthor(rs.getString("author"));
                book.setTitle(rs.getString("book_title"));

                list.add(book);
            }
            ps.close();
            cons.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /*
     * Lay 1 sach theo id
     * Neu khong tim thay return null
     * */
    public static Book getBookByID(String id) {
        Connection cons = DBConnect.getConnection();
        String sql = "SELECT * FROM book_id =" + id;
        Book book = new Book();
        try {
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            book.setId(rs.getString("book_id"));
            book.setCategory(rs.getString("category"));
            book.setContent(rs.getString("content"));
            book.setPublisher(rs.getString("publisher"));
            book.setBrief(rs.getString("brief"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("book_title"));
            ps.close();
            cons.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }
    // tìm bằng author
    public static List<Book> getBookByAuthor(String author) {
        Connection cons = DBConnect.getConnection();
        String sql = "SELECT * FROM author=" + author;
        List<Book> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getString("book_id"));
                book.setCategory(rs.getString("category"));
                book.setContent(rs.getString("content"));
                book.setPublisher(rs.getString("publisher"));
                book.setBrief(rs.getString("brief"));
                book.setAuthor(rs.getString("author"));
                book.setTitle(rs.getString("book_title"));
                list.add(book);
            }
            ps.close();
            cons.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    // tìm bằng title
    public static List<Book> getBookByName(String name) {
        Connection cons = DBConnect.getConnection();
        String sql = "SELECT * FROM title=" + name;
        List<Book> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getString("book_id"));
                book.setCategory(rs.getString("category"));
                book.setContent(rs.getString("content"));
                book.setPublisher(rs.getString("publisher"));
                book.setBrief(rs.getString("brief"));
                book.setAuthor(rs.getString("author"));
                book.setTitle(rs.getString("book_title"));
                list.add(book);
            }
            ps.close();
            cons.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //tìm bằng category
    public static List<Book> getBookByCategory(String category) {
        Connection cons = DBConnect.getConnection();
        String sql = "SELECT * FROM category=" + category;
        List<Book> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getString("book_id"));
                book.setCategory(rs.getString("category"));
                book.setContent(rs.getString("content"));
                book.setPublisher(rs.getString("publisher"));
                book.setBrief(rs.getString("brief"));
                book.setAuthor(rs.getString("author"));
                book.setTitle(rs.getString("book_title"));
                list.add(book);
            }
            ps.close();
            cons.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /*
     * Them sach vao database
     * return true neu save thanh cong
     * return false neu save khong thanh cong
     * */
    public static boolean save(Book book) {
        Connection cons = DBConnect.getConnection();
        String sql = "INSERT INTO Book(book_id,category,content,publisher,brief,author,book_title)" +
                " VALUES('" + book.getId() + "','" + book.getCategory() + "','" + book.getContent() + "','" + book.getPublisher() + "','" + book.getBrief() + "','" + book.getAuthor() + "','" + book.getTitle() + "')";
        try {
            Statement statement = cons.createStatement();
            int rs = statement.executeUpdate(sql);
            cons.close();
            if (rs == 1) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
     * Cap nhat thong tin sach theo id
     * */
    public static boolean update(Book book) {
        Connection cons = DBConnect.getConnection();
        String sql = "UPDATE Book SET book_title='" + book.getTitle() + "',author='" + book.getAuthor() + "',brief=" +
                "'" + book.getBrief() + "',publisher='" + book.getPublisher() + "',content='" + book.getContent() + "'" +
                ",category='" + book.getCategory() + "' where book_id='" + book.getId() + "'";
        try {
            Statement statement = cons.createStatement();
            int rs = statement.executeUpdate(sql);
            cons.close();
            if (rs == 1) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
     * Xoa sach trong bang Book va bang Contain
     * */
    public static boolean delete(Book book) {
        Connection cons = DBConnect.getConnection();
        String sql = "DELETE * FROM Book WHERE book_id = '" + book.getId() + "'";
        String sql1 = "DELETE * FROM Contain WHERE book_id = '" + book.getId() + "'";
        try {
            Statement statement = cons.createStatement();
            int rs = statement.executeUpdate(sql);
            int rs1 = statement.executeUpdate(sql1);
            cons.close();
            if (rs == 1 && rs1 == 1) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
