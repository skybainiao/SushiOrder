package Server.Database;

import Server.Model.User;

import java.sql.*;

public class JDBC {
    private static String driver = "org.postgresql.Driver";
    private static String url ="jdbc:postgresql://localhost:5432/postgres";
    private static String user = "postgres";
    private static String password = "cjj2468830035";
    private Connection connection;


    public JDBC()throws Exception{
        Class.forName(driver);
        connection = DriverManager.getConnection(url,user,password);
        System.out.println("Connected to database : "+connection.getCatalog());
    }

    public int addUser(User user) throws SQLException {
        String sql="insert into appusers.userdetail(username,password)\n" + "values(?,?);";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());

        return preparedStatement.executeUpdate();
    }


    public ResultSet getAllUsers() throws SQLException {
        String sql="select username,password from appusers.userdetail";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        return preparedStatement.executeQuery();
    }







}
