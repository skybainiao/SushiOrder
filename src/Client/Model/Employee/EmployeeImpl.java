package Client.Model.Employee;

import Client.Networking.Client;
import Server.Shared.Order;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class EmployeeImpl implements EmployeeModel{

    private Client client;
    private PropertyChangeSupport support;

    public EmployeeImpl(Client client) throws RemoteException, NotBoundException {
        this.client=client;
        support=new PropertyChangeSupport(this);
        client.addPCL("order",evt -> propertyChange(evt));
    }


    @Override
    public ArrayList<Order> getOrders() throws Exception {
        return client.getOrders();
    }

    @Override
    public int updateOrderStatus(int orderId, String newStatus) throws SQLException, RemoteException {
        return client.updateOrderStatus(orderId,newStatus);
    }


    @Override
    public void addPCL(String name, PropertyChangeListener listener) throws RemoteException {
        support.addPropertyChangeListener(name,listener);
    }

    @Override
    public void removePCL(String name, PropertyChangeListener listener) throws RemoteException {
        support.removePropertyChangeListener(name,listener);
    }

    private void propertyChange(PropertyChangeEvent evt)
    {
        support.firePropertyChange(evt);
        System.out.println("EmployeeModel");
    }
}
