package test;

import abc.java05.db.DBBook;
import abc.java05.model.Book;
import abc.java05.service.AdminService;
import abc.java05.service.UserService;

public class TestDB {
    UserService us = new UserService();
    static AdminService as = new AdminService();
    public static void main(String[] args) {
        Book b = DBBook.getBookByID("33");
        b.setTitle("tested db book");
        System.out.println(DBBook.update(b));;
        b.setTitle("update book service ok");
        System.out.println(as.updateBook(b));
    }
}
