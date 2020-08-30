package abc.java05.db;

import abc.java05.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBBook {

    private static List<Book> result2Books(ResultSet rs) {
        List<Book> list = new ArrayList<>();
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            list.clear();
            return list;
        }
        return list;
    }

    private static List<Book> getBookByQuery(String query) {
        Connection cons = DBConnect.getConnection();
        List<Book> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            list.addAll(result2Books(rs));
            ps.close();
            cons.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    /*
     * Lay toan bo sach trong database
     * */
    public static List<Book> getAll() {
        String sql = "SELECT * FROM Book";
        return getBookByQuery(sql);
    }



    /*
     * Lay 1 sach theo id
     * Neu khong tim thay return null
     * */
    public static Book getBookByID(String id) {
        Connection cons = DBConnect.getConnection();
        String sql = "SELECT * FROM Book WHERE book_id = " + id;
        Book book = new Book();
        try {
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Book> l = result2Books(rs);
            if(l.isEmpty()) {
                book = null;
            } else {
                book = l.get(0);
            }
            ps.close();
            cons.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return book;
    }


    // tìm bằng author
    public static List<Book> getBookByAuthor(String author) {
        String sql = "SELECT * FROM Book WHERE author=" + author;
        return getBookByQuery(sql);
    }
    // tìm bằng title
    public static List<Book> getBookByName(String name) {
        String sql = "SELECT * FROM Book WHERE title=" + name;
        return getBookByQuery(sql);
    }
    //tìm bằng category
    public static List<Book> getBookByCategory(String category) {
        String sql = "SELECT * FROM Book WHERE category=" + category;
        return getBookByQuery(sql);
    }

    /*
     * Them sach vao database
     * return true neu save thanh cong
     * return false neu save khong thanh cong
     * */
    public static boolean save(Book book) {
        Connection cons = DBConnect.getConnection();
        String sql = "INSERT INTO Book(category,content,publisher,brief,author,book_title)" +
                " VALUES(N'" + book.getCategory() + "',N'" + book.getContent() + "',N'" + book.getPublisher() + "',N'" + book.getBrief() + "',N'" + book.getAuthor() + "',N'" + book.getTitle() + "')";
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
        String sql = "UPDATE Book SET book_title=N'" + book.getTitle() + "',author=N'" + book.getAuthor() + "',brief=N" +
                "'" + book.getBrief() + "',publisher=N'" + book.getPublisher() + "',content=N'" + book.getContent() + "'" +
                ",category=N'" + book.getCategory() + "' where book_id='" + book.getId() + "'";
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
    public static boolean delete(String bookID) {
        Connection cons = DBConnect.getConnection();
        String sql = "DELETE FROM Book WHERE book_id = '" + bookID + "'";
        String sql1 = "DELETE FROM Contain WHERE book_id = '" + bookID + "'";
        try {
            Statement statement = cons.createStatement();
            int rs1 = statement.executeUpdate(sql1);
            int rs = statement.executeUpdate(sql);
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
