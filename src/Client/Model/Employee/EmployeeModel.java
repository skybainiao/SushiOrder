package Client.Model.Employee;

import Server.PCS;
import Server.Shared.Order;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface EmployeeModel extends PCS {

    ArrayList<Order> getOrders() throws Exception;

    int updateOrderStatus(int orderId, String newStatus) throws SQLException, RemoteException;




}
