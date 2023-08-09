package Server.Database;

import Server.Shared.Order;
import Server.Shared.User;
import java.sql.*;
import java.util.Map;

public class CustomerDatabaseOperations extends DatabaseOperationBase {

    public CustomerDatabaseOperations() throws ClassNotFoundException, SQLException {
        super();
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
}
