package Client.Networking;

import Server.Shared.Order;
import Server.Shared.User;
import Server.Server;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientImpl implements Client{

    private String name;
    private Server server;
    private User user;
    private PropertyChangeSupport support;


    public ClientImpl(String name) throws Exception {
        this.name=name;
        UnicastRemoteObject.exportObject(this, 0);
        Registry registry = LocateRegistry.getRegistry("localhost", 6666);
        server = (Server) registry.lookup("Server");
        support=new PropertyChangeSupport(this);
        server.addClientCallBack(this);
        server.addClient(this);

    }

    public void test() throws SQLException, RemoteException {
        System.out.println("client:"+server.test());
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

    @Override
    public List<Client> getClients() throws RemoteException {
        return server.getClients();
    }

    @Override
    public void update(Order order) throws RemoteException {
        support.firePropertyChange("order",null,order);
    }

    @Override
    public ArrayList<Order> getOrders() throws Exception {
        return server.getOrders();
    }

    @Override
    public int updateOrderStatus(int orderId, String newStatus) throws SQLException, RemoteException {
        return server.updateOrderStatus(orderId,newStatus);
    }


    @Override
    public void addPCL(String name, PropertyChangeListener listener) throws RemoteException {
        support.addPropertyChangeListener(name, listener);
    }

    @Override
    public void removePCL(String name, PropertyChangeListener listener) throws RemoteException {
        support.removePropertyChangeListener(name, listener);
    }


}
