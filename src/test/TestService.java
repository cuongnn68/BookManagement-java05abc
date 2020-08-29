package test;

import abc.java05.model.Book;
import abc.java05.model.BookCase;
import abc.java05.service.AdminService;
import abc.java05.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static abc.java05.service.UserService.displayBook;

public class TestService {
    static UserService userS = new UserService();
    static AdminService adminS = new AdminService();
    static List<Book> list = new ArrayList<>();

    public static void main(String[] args) {
        //ok
//        UserS.displayBook(userS.viewAllBooks());



        //ok
//        System.out.println(userS.readBook("12"));


        // not ok
//        list.add(userS.getBookDetail("6"));
//        UserS.displayBook(list);


        // tim kiem phai tim dung ten???
        // ok
//        UserS.displayBook(userS.searchBookByName("Khám Phá Thế Giới Tâm Linh"));

        //ok
//        UserS.displayBook(userS.searchBookByAuthor("Nguyễn Du"));

        // ok
//        UserS.displayBook(userS.searchBookByCategory("Tâm Lý - Kỹ Năng Sống"));

        // not ok bookcase not work at all
//        BookCase bc = userS.viewBookCase("5");
//        displayBook(bc.getBooks());
//
//        userS.removeBookInBookCase("2","9");
//        displayBook(bc.getBooks());
//
//        userS.addBookToBookCase("2", "30");
//        displayBook(bc.getBooks());
//
//        userS.clearBookCase("2");
//        displayBook(bc.getBooks());

//=============== Admin Service ===================================================================
        Book newBook = new Book();
        newBook.setTitle("test book");
        newBook.setAuthor("cuongnn5");
        newBook.setBrief("Khong co");
        newBook.setContent("bala alfsldfll alsdlfl asldflalsdl lasdl flasd");
        newBook.setCategory("testing");
        newBook.setPublisher("n/a");

        adminS.createBook(newBook);
//        adminS.deleteBook("20"); // not ok ko chay vi user service ko chay;
        adminS.updateBook("10", "got updated"); // not ok ko thay doi nd
        UserService.displayBook(userS.viewAllBooks());
        System.out.println(userS.readBook("10"));

    }

}
