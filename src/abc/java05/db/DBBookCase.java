package abc.java05.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import abc.java05.model.Book;
import abc.java05.model.BookCase;

public class DBBookCase {

//    //ko dung, ko lam
//    public List<BookCase> getAll() {
//        return null;
//    }

	/*
	 * Lay bookcase theo id tra ve null neu ko co
	 */
	@SuppressWarnings("null")
	public BookCase get(String bookCaseID) {
		String sql = "SELECT b.book_id,b.book_title,b.author,b.brief,b.publisher,b.content,b.category FROM Book b join Contain c "
				+ "ON b.book_id=c.book_id WHERE c.book_case_id=" + bookCaseID;
		BookCase bookcase = new BookCase();
		List<Book> listBook = new ArrayList<>();
		//setBooks cho BookCase
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getString("book_id"));
				book.setTitle(rs.getString("book_title"));
				book.setAuthor(rs.getString("author"));
				book.setBrief(rs.getString("brief"));
				book.setPublisher(rs.getString("publisher"));
				book.setContent(rs.getString("content"));
				book.setCategory(rs.getString("category"));
				listBook.add(book);
			}
			bookcase.setBooks(listBook);
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}


		//setID va setName cho BookCase
		String sql_bookcase = "SELECT * FROM BookCase WHERE book_case_id=" + bookCaseID;
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql_bookcase);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bookcase.setId(rs.getString("book_case_id"));
				bookcase.setName(rs.getString("book_case_name"));
			}
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return bookcase;
	}

//    // ko dung, ko lam
//    public void save(BookCase bookCase) {
//
//    }

//    /*Ko dung ko lam*/
//    public void update(BookCase bookCase) {
//
//    }

//    // ko dung ko lam
//    public void delete(BookCase bookCase) {
//
//    }

	// ====== phan nay update Contain Table =======

	/*
	 * Them 1 gia tri vao bang contain Tu dong lay ngay hien tai
	 */
	public void addBook(String bookCaseID, String bookID) {
		String sql="INSERT INTO Contain(book_case_id,book_id) VALUES ("+bookCaseID+","+bookID+")";
		try {
			Connection conn = DBConnect.getConnection();
			Statement st=conn.createStatement();
			st.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Xoa 1 gia trri bang contain
	 */
	public void removeBook(String bookCaseID, String bookID) {
		String sql="DELETE FROM Contain WHERE book_case_id="+bookCaseID +" AND "+ "book_id="+bookID;
		try {
			Connection conn = DBConnect.getConnection();
			Statement st=conn.createStatement();
			st.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Xoa toan bo gia tri co gia tri = bookCaseID trong contain table
	 */
	public void clearBook(String bookCaseID) {
		String sql="DELETE FROM Contain WHERE book_case_id="+bookCaseID;
		try {
			Connection conn = DBConnect.getConnection();
			Statement st=conn.createStatement();
			st.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
