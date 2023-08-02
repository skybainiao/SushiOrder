package Server;

import Server.Shared.Order;
import Server.Shared.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Server extends Remote,PCS {
    String test() throws RemoteException, SQLException;
    void addUser(User user) throws RemoteException;
    ArrayList<User> getUsers() throws Exception;

    void addOrder(Order order) throws RemoteException;

}
