package abc.java05.db;

import java.sql.Connection;

public class DBConnect {
    final static private String url =   "jdbc:sqlserver://localhost:1433" +
                                        "databaseName=test";
    final static private String usernameDB = "";
    final static private String passwordDB = "";

    Connection connection;

    // khoi tao ket noi
    public DBConnect () {

    }
}
