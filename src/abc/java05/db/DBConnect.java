package abc.java05.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    final static private String url =   "jdbc:sqlserver://localhost:1433;" +
                                        "databaseName=book_management_db";
    final static private String usernameDB = "admin";
    final static private String passwordDB = "1";

    static Connection connection;

    // khoi tao ket noi
    public DBConnect () {

    }

    public static Connection getConnection()  {
        try {
//            Class.forName("com.sqlserver.jdbc.Driver");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // tuy phien ban nha, vao lib ma xem path
            return DriverManager.getConnection(url,usernameDB,passwordDB);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
