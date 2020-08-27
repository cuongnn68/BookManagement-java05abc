package abc.java05.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    final static private String url =   "jdbc:sqlserver://localhost:1433" +
                                        "databaseName=test";
    final static private String usernameDB = "";
    final static private String passwordDB = "";

    static Connection connection;

    // khoi tao ket noi
    public DBConnect () {

    }

    public static Connection getConnection()  {
        try {
            Class.forName("com.sqlserver.jdbc.Driver");
            return DriverManager.getConnection(url,usernameDB,passwordDB);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
