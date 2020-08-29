package test;

import abc.java05.service.Login;

public class TestLogin {

    //user ok
    public static void main(String[] args) {
        Login login = new Login();
        System.out.println(login.checkAccount("123","123"));
        System.out.println(login.checkAccount("NinhND1","nguyenduyninh").getRole());
        System.out.println(login.checkAccount("admin","11111111").getRole());
        System.out.println(login.checkAccount("RinhTT","trantherinh").getRole());
        System.out.println(login.checkAccount("ChienPV5","phamvanchien").getBookCaseID());

    }
}
