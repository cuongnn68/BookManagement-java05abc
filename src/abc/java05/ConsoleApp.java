package abc.java05;

import abc.java05.model.User;
import abc.java05.service.Login;
import abc.java05.util.Role;

import java.io.Console;

import static abc.java05.util.Constant.SCANNER;
/*
* Dung 1 SCANNER nay thoi cho don gian
* */

public class ConsoleApp {
    public static void run() {
        ConsoleApp app = new ConsoleApp();
        User user = null;
        do {
            user = app.loginFunction();
            if(user.getRole() == Role.ADMIN) {
                app.adminConsole(user);
            } else if(user.getRole() == Role.USER) {
                app.userConsole(user);
            } else {
                System.out.println("User khong co quyen truy cap!!!");
            }
        } while (true);
    }

    public User loginFunction () {
        Console console = System.console();
        Login loginService = new Login();
        User user;
        String username;
        String pass;
        System.out.println("Welcome to Read Book Application! Please enter your username and password!");
        do {
            /*
            *  Phan nay dung console.readPassword() chi doc duoc khi dung terminal
            *  java abc.java05.MainProgram
            *  readPassword() de doc password ma ko hien input
            * */
            if(console != null) {
                console.printf("User name: ");
                username = console.readLine();
                console.printf("Password: ");
                pass = new String(console.readPassword());
            } else {
                System.out.print("User name: ");
                username = SCANNER.nextLine();
                System.out.print("Password: ");
                pass = SCANNER.nextLine();
            }
            user = loginService.checkAccount(username, pass);
            if(user == null) {
                System.out.println("Username or Password is wrong!");
                System.out.println("Please enter again");
            }
        } while(user == null);
        return user;
    }

    private void userConsole(User user) {
        do {
            System.out.println("Hello User, Please select a function bellow by entering corresponding number.");
            System.out.println("1. View List Books");
            System.out.println("2. Search Book");
            System.out.println("3. Read Book");
            System.out.println("4. View your BookCase");
            System.out.println("5. Edit your BookCase");
            System.out.println("6. Logout");
            System.out.print("Option: ");
            switch (SCANNER.nextLine()) {

                //TODO: thuc hien chuc nang cua user
                case "1":

                    break;
                case "6":
                    return;
            }
        }while(true);
    }

    private void adminConsole(User admin) {
        do {
            System.out.println("==== Admin ====");
            System.out.println("1. Create book");
            System.out.println("2. Update Content");
            System.out.println("3. Delete book");
            System.out.println("4. Login");
            System.out.print("Option: ");
            switch (SCANNER.nextLine()) {

                //TODO: thuc hien chuc nang cua admin
                case "1":

                    break;
                case "2":

                    break;
                case "6":

                    return;
            }
        } while(true);
    }

}
