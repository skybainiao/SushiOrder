package Server.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseOperationBase {
    protected Connection connection;
    private static String driver = "org.postgresql.Driver";
    private static String url ="jdbc:postgresql://localhost:5432/postgres";
    private static String user = "postgres";
    private static String password = "cjj2468830035";

    public DatabaseOperationBase() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url,user,password);
        System.out.println("Connected to database : "+connection.getCatalog());
    }


}
