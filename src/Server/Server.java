package Server;

import Client.Networking.Client;
import Server.Shared.Order;
import Server.Shared.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Server extends Remote,PCS {
    String test() throws RemoteException, SQLException;

    void addOrder(Order order) throws RemoteException;

    void addClient(Client client)throws RemoteException;

    void addClientCallBack(Client client) throws RemoteException;
    List<Client> getClients() throws RemoteException;

    ArrayList<Order> getOrders() throws Exception;

    int updateOrderStatus(int orderId, String newStatus) throws SQLException, RemoteException;
}
