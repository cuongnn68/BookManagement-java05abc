package abc.java05.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import abc.java05.db.DBBook;
import abc.java05.db.DBBookCase;
import abc.java05.db.DBUser;
import abc.java05.model.Book;
import abc.java05.model.BookCase;
import abc.java05.model.User;

public class UserService {
	private DBBook daoBook = new DBBook();
	private DBUser daoUser = new DBUser();
	private DBBookCase daoBookCase = new DBBookCase();

	/*
	 * Tu id book lay Book tu data base xong tra ve content
	 */
	public String readBook(String bookID) {
		List<Book> books = daoBook.getAll();
		for (Book book : books) {
			if (book.getBookID().equalsIgnoreCase(bookID))
				return book.getContent();
		}
		return null;
	}

	/*
	 * Lay toan bo sach neu ko co van tra ve list co size = 0
	 */
	public List<Book> viewAllBooks() {
		List<Book> books = daoBook.getAll();
		if (books == null)
			books = new ArrayList<>();
		return books;
	}

	// tra ve 1 sach theo id
	public Book getBookDetail(String bookID) {
		return daoBook.get(bookID);
	}

	/*
	 * lay het book tu database ve r search ?? Neu ko co thi tra ve 1 list size = 0
	 */
	public List<Book> searchBookByName(String bookName) {
		List<Book> books = daoBook.getAll();
		List<Book> booksByName = new ArrayList<>();
		if (books != null) {
			for (Book book : books) {
				if (book.getTitle().equalsIgnoreCase(bookName))
					booksByName.add(book);
			}
		}
		return booksByName;
	}

	public List<Book> searchBookByAuthor(String author) {
		List<Book> books = daoBook.getAll();
		List<Book> booksByAuthor = new ArrayList<>();
		if (books != null) {
			for (Book book : books) {
				if (book.getAuthor().equalsIgnoreCase(author))
					booksByAuthor.add(book);
			}
		}
		return booksByAuthor;
	}

	public List<Book> searchBookByCategory(String category) {
		List<Book> books = daoBook.getAll();
		List<Book> booksByCategory = new ArrayList<>();
		if (books != null) {
			for (Book book : books) {
				if (book.getCategory().equalsIgnoreCase(category))
					booksByCategory.add(book);
			}
		}
		return booksByCategory;
	}

	// lay book case voi bookcase id cua user
	public BookCase viewBookCase(String userID) {
		List<User> users = daoUser.getAll();
		String bookCaseID = null;
		for (User user : users) {
			if (user.getId().equalsIgnoreCase(userID)) {
				bookCaseID = user.getBookCaseID();
			}
		}
		if (bookCaseID != null)
			return daoBookCase.get(bookCaseID);
		else
			return null;
	}

	/*
	 * Kiem tra: - co sach trong book case chua ko - Sach co ton tai ko Ok thi
	 * return true, va them sach vao bookcase Ko thi false
	 */
	public boolean addBookToBookCase(String bookCaseID, String bookID) {
		BookCase bookCase = daoBookCase.get(bookCaseID);
		List<Book> booksInBookCase = bookCase.getBooks();
		Book book1 = daoBook.get(bookID);
		boolean check1 = false; // check xem sach co ton tai ko
		boolean check2 = false; // check xem sach da co trong bookcase chua
		if (book1 != null) {
			check1 = true;
		}
		for (Book book : booksInBookCase) {
			if (book.getBookID().equalsIgnoreCase(bookID)) {
				check2 = true;
				break;
			}
		}
		if (check1 && !check2) {
			booksInBookCase.add(book1);
			bookCase.setBooks(booksInBookCase);
			daoBookCase.update(bookCase);
			return true;
		}
		return false;
	}

	/*
	 * Kiem tra xem sach co trong bookcase chua Co thi return false Ko co thi remove va
	 * return true
	 */
	public boolean removeBookInBookCase(String bookCaseID, String bookID) {
		BookCase bookCase = daoBookCase.get(bookCaseID);
		List<Book> books = bookCase.getBooks();
		Iterator<Book> iterator = books.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getBookID().equalsIgnoreCase(bookID)) {
				iterator.remove();
				bookCase.setBooks(books);
				daoBookCase.update(bookCase);
				return true;
			}
		}
		return false;
	}

	/*
	 * Hien thi book theo format tren console
	 */
	public void displayBook(List<Book> books) {
	if(books != null) {
		System.out.format("%-3d%-10s%-20s%-20s%-20s%-25s%-20s", "STT", "id",	"Name", 
				"Author", "Category", "Brief", "Publisher");
		for (int i = 0; i < books.size(); i++) {
			Book book = books.get(i);
			System.out.format("%-3d%-10s%-20s%-20s%-20s%-25s%-20s", (i+1), book.getBookID(), book.getTitle(), 
					book.getAuthor(), book.getCategory(), book.getBrief(), book.getPublisher());
		}
	} else System.out.println("There is not any book in application");
	}

}

