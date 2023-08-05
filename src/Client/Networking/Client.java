package Client.Networking;

import Server.Shared.Order;
import Server.Shared.User;
import Server.PCS;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Client  extends Remote, PCS {
    void addUser(User user) throws RemoteException;
    ArrayList<User> getUsers() throws Exception;
    void setClientName(String name) throws RemoteException;
    String getClientName() throws RemoteException;
    void addOrder(Order order) throws RemoteException;

    List<Client> getClients() throws RemoteException;
    void update(Order order) throws RemoteException;

    ArrayList<Order> getOrders() throws Exception;

    int updateOrderStatus(int orderId, String newStatus) throws SQLException, RemoteException;


}
