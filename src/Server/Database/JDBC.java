package Server.Database;

import Server.Shared.Order;
import Server.Shared.User;

import java.sql.*;
import java.util.Map;

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
        String sql="insert into sushi.userdetail(username,password)\n" + "values(?,?);";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());

        return preparedStatement.executeUpdate();
    }


    public ResultSet getAllUsers() throws SQLException {
        String sql="select username,password from sushi.userdetail";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        return preparedStatement.executeQuery();
    }

    public int addOrder(Order order) throws SQLException {
        String sql = "INSERT INTO sushi.orders (order_time, food_name, total_price) VALUES (CURRENT_TIMESTAMP, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // Convert the food map into a string
        StringBuilder orderStringBuilder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : order.getFood().entrySet()) {
            orderStringBuilder.append(entry.getKey());
            orderStringBuilder.append(" x");
            orderStringBuilder.append(entry.getValue());
            orderStringBuilder.append(", ");
        }
        // Remove the last comma and space
        orderStringBuilder.setLength(orderStringBuilder.length() - 2);

        preparedStatement.setString(1, orderStringBuilder.toString());
        preparedStatement.setInt(2, order.getTotalPrice());

        return preparedStatement.executeUpdate();
    }










}
