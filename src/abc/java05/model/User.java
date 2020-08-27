package abc.java05.model;

import abc.java05.util.Role;

public class User {
    private String id;
    private String userName;
    private String password;
    private String bookCaseID;
    private Role role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookCaseID() {
        return bookCaseID;
    }

    public void setBookCaseID(String bookCaseID) {
        this.bookCaseID = bookCaseID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
