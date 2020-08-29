package abc.java05.Controller;

import abc.java05.model.Book;
import abc.java05.model.BookCase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class BookCaseController {
    public DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public void viewBookCase(BookCase bookCase){
        System.out.println("ID:"+bookCase.getId());
        System.out.println("Name:"+bookCase.getName());
        System.out.println("Date:"+dateFormat.format(bookCase.getCreateDate()));
    }
    public void editBookCase(BookCase bookCase){
        System.out.println("Choose ");
        System.out.println("1.Delete a book" +
                "\n2.Add new book" +
                "\n3.Clear book case");
        int choice=0;
        do{
            System.out.println("Your choice:");
            choice=new Scanner(System.in).nextInt();
        }while (!((choice==1)&&(choice==2)&&(choice==3)));
        switch (choice){
            case 1:
                System.out.println("Input id of book ");
                String id=new Scanner(System.in).nextLine();
                for (Book book:bookCase.getBooks()){
                    if (id.equals(book.getId())){
                        bookCase.getBooks().remove(book);
                    }
                }
                break;
            case 2:Book book=new Book();
                Scanner scanner=new Scanner(System.in);
                System.out.println("1.Enter the name");
                book.setId(scanner.nextLine());
                System.out.println("2.Enter the title:");
                book.setTitle(scanner.nextLine());
                System.out.println("Enter the author:");
                book.setAuthor(scanner.nextLine());
                System.out.println("Enter the brief:");
                book.setBrief(scanner.nextLine());
                System.out.println("Enter the category");
                book.setCategory(scanner.nextLine());
                System.out.println("Enter the content");
                book.setContent(scanner.nextLine());
                bookCase.getBooks().add(book);
                break;
            case 3:bookCase.getBooks().removeAll(bookCase.getBooks());
                break;
            default:break;
        }
    }

}
