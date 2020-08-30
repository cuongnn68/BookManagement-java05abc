package test;

import abc.java05.model.Book;
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
        displayBook(userS.viewAllBooks());


        //ok
        System.out.println(userS.readBook("12"));


        // ok
        list.add(userS.getBookDetail("6"));
        displayBook(list);


//        // ok
        displayBook(userS.searchBookByName("hế Giới"));
//        //ok
        displayBook(userS.searchBookByAuthor("Nguyễn"));
//        // ok
        displayBook(userS.searchBookByCategory("Kỹ Năng Sống"));


        // ok
        displayBook(userS.viewBookCase("5").getBooks());

        userS.removeBookInBookCase("5","23");
        displayBook(userS.viewBookCase("5").getBooks());

        userS.addBookToBookCase("5", "30");
        displayBook(userS.viewBookCase("5").getBooks());

        userS.clearBookCase("5");
        displayBook(userS.viewBookCase("5").getBooks());

//=============== Admin Service ===================================================================
        //TODO: test again after tested service
        Book newBook = new Book();
        newBook.setTitle("test book ễ");
        newBook.setAuthor("cuongnn5 ễ");
        newBook.setBrief("Khong co ễ");
        newBook.setContent("bala alfsldfll alsdlfl asldflalsdl lasdl flasd ễ");
        newBook.setCategory("testing ễ");
        newBook.setPublisher("n/a ễ");

        adminS.createBook(newBook);
        System.out.println(adminS.deleteBook("20")); // not ok ko chay vi user service ko chay;
        System.out.println(adminS.updateBookContent("10", "ê ó ẫ ờ")); // not ok ko thay doi nd
        UserService.displayBook(userS.viewAllBooks());
        System.out.println(userS.readBook("10"));
    }

}
