package abc.java05.model;

public class User {
    private String userName;
    private String password;
    private String role;

    public String getBookCaseID() {
        return bookCaseID;
    }

    public void setBookCaseID(String bookCaseID) {
        this.bookCaseID = bookCaseID;
    }

    private String bookCaseID;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
