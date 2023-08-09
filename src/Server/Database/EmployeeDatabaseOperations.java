package Server.Database;

import Server.Shared.Order;
import java.rmi.RemoteException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDatabaseOperations extends DatabaseOperationBase {

    public EmployeeDatabaseOperations() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public ResultSet getAllOrdersResultSet() throws SQLException {
        String sql = "SELECT * FROM sushi.orders";
        return executeQuery(sql);
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
