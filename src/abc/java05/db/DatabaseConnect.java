package abc.java05.db;

import java.sql.Connection;

public class DatabaseConnect {
    final static private String url =   "jdbc:sqlserver://localhost:1433" +
                                        "databaseName=test";
    final static private String usernameDB = "";
    final static private String passwordDB = "";

    private static Connection connection;

    
}
