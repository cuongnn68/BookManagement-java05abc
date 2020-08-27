package abc.java05.Controller;

import abc.java05.db.DBBook;
import abc.java05.model.Book;

import java.io.PrintStream;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;


public class BookController {


    public static Book InputData(){
        Scanner scanner=new Scanner(System.in);
        Book book =new Book();
        System.out.println("Nhập dữ liệu của cuốn sách");
        System.out.println("Nhập book_id:");
        book.setId(scanner.nextLine());
        System.out.println("Nhập title:");
        book.setTitle(scanner.nextLine());
        System.out.println("Nhập author:");
        book.setAuthor(scanner.nextLine());
        System.out.println("Nhập brief:");
        book.setBrief(scanner.nextLine());
        System.out.println("Nhập category");
        book.setCategory(scanner.nextLine());
        System.out.println("Nhập content");
        book.setContent(scanner.nextLine());
        return book;

    }
    public boolean update(Book book) {
        boolean rs = false;
        if (book != null) {
            rs =DBBook.update(book);
            if(rs==true){
                System.out.println("Cập nhật thành công.");
            }
        }
        return rs;
    }

   public static void ReadBook(){
       System.out.println("Please enter the book's id:");
       String bookID=new Scanner(System.in).nextLine();
       Book rs =DBBook.getBookByID(bookID);
       if(rs==null){
           System.out.println("This book is not exist");
       }else {
           System.out.println(rs.getTitle());
           System.out.println(rs.getContent());
       }
   }

    public static void Search() {
        List<Book> rs = null;
        int choose = 0;
        do {
            System.out.println("Please select search type");
            System.out.println("1.By name");
            System.out.println("2.By author");
            System.out.println("3.By category");
            try {
                choose=Integer.parseInt(new Scanner(System.in).nextLine());
            }catch (Exception e){
                System.out.println("choose is digit");
            }
            if(choose==1){
                System.out.println("Please enter book's name");
                rs=DBBook.getBookByName(new Scanner(System.in).nextLine());
                break;
            }
            if (choose==2){
                System.out.println("Please enter book's name");
                rs=DBBook.getBookByAuthor(new Scanner(System.in).nextLine());
                break;
            }
            if(choose==3){
                System.out.println("Please enter book's name");
                rs=DBBook.getBookByCategory(new Scanner(System.in).nextLine());
                break;
            }


        }while (true);
        if(rs==null){
            System.out.println("Không tìm thấy kết quả nào phù hợp với book_id trên.");
            return;
        }
        System.out.printf("About %d result%n", rs.size());
        listBookDisplay(rs);


    }
    public String insert(Book  book){
        String rs = null;
       if(DBBook.getBookByID(book.getId())!=null){
           return "Dữ liệu đã tồn tại";
       }

        if(book != null && !book.getId().isEmpty()){
            // thực hiện việc thêm mới
            // DAO
            boolean check = DBBook.save(book);
            if(check == true){
                rs ="Thêm mới thành công";
            }else{
                rs="Thêm mới thất bại!";
            }
        }
        return  rs;
    }
    public static void  ViewListBooks(){
        List<Book> bookList=DBBook.getAll();
        if(bookList==null){
            System.out.println("There are not any book in application");
            return;
        }
        System.out.println("You are have "+bookList.size()+" book");
        listBookDisplay(bookList);
    }
    public static void listBookDisplay(List<Book> list){
        if(list== null){
            System.out.println("There are not any book in application");
            return;
        }
        System.out.println("Danh sách các cuốn sách:");
        for (Book book:list){
            System.out.println("STT   id       Name          Author     Category     Brief     Title          Publisher" );
            book.display();
        }
    }
}
