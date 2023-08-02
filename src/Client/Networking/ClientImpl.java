package Client.Networking;

import Server.Shared.Order;
import Server.Shared.User;
import Server.Server;

import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientImpl implements Client{

    private String name;
    private Server server;
    private User user;
    private PropertyChangeSupport support;


    public ClientImpl(String name) throws Exception {
        this.name=name;
        support=new PropertyChangeSupport(this);
        UnicastRemoteObject.exportObject(this, 0);
        Registry registry = LocateRegistry.getRegistry("localhost", 6666);
        server = (Server) registry.lookup("Server");
        user=new User("","");
        test();
        addUser(new User("asd","ewq"));
        System.out.println(getUsers());
    }

    public void test() throws SQLException, RemoteException {
        System.out.println("client:"+server.test());
    }


    @Override
    public void addUser(User user) throws RemoteException {
        server.addUser(user);
    }

    @Override
    public ArrayList<User> getUsers() throws Exception {
        return server.getUsers();
    }

    @Override
    public void setClientName(String name) throws RemoteException {
        this.name = name;
    }

    @Override
    public String getClientName() throws RemoteException {
        return name;
    }

    @Override
    public void addOrder(Order order) throws RemoteException {
        server.addOrder(order);
    }
}
