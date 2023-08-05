package Server.Database;

import Server.Shared.Order;
import Server.Shared.User;

import java.rmi.RemoteException;
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
        String sql = "INSERT INTO sushi.orders (order_time, food_name, total_price, order_status) VALUES (CURRENT_TIMESTAMP, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        StringBuilder orderStringBuilder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : order.getFoodName().entrySet()) {
            orderStringBuilder.append(entry.getKey());
            orderStringBuilder.append(" *");
            orderStringBuilder.append(entry.getValue());
            orderStringBuilder.append(", ");
        }
        orderStringBuilder.setLength(orderStringBuilder.length() - 2);

        preparedStatement.setString(1, orderStringBuilder.toString());
        preparedStatement.setInt(2, order.getTotalPrice());
        preparedStatement.setString(3, order.getOrderStatus());

        return preparedStatement.executeUpdate();
    }


    public ResultSet getAllOrdersResultSet() throws SQLException {
        String sql = "SELECT * FROM sushi.orders";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public int updateOrderStatus(int orderId, String newStatus) throws RemoteException {
        try {
            String sql = "UPDATE sushi.orders SET order_status = ? WHERE order_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newStatus);
            preparedStatement.setInt(2, orderId);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RemoteException("Database error", e);
        }
    }









}
